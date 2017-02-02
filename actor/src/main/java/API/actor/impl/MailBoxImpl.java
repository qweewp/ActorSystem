package API.actor.impl;

import API.actor.abstaract.Actor;
import API.actor.abstaract.ActorRefId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Mail box is the main class that distributes messages between the {@link Actor}.
 */
class MailBoxImpl extends AbstractMailBox {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMailBox.class);

    private Thread ownThread;
    private final Lock lock;
    private final Condition messageCondition;

    MailBoxImpl() {
        lock = new ReentrantLock(true);
        messageCondition = lock.newCondition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void receiveMail(ActorRefId receiver, Object message, ActorRefId sender) {
        ((AbstractActor) receiver.getInstance()).setSender(sender);
        receiveMail(receiver, message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void receiveMail(ActorRefId receiver, Object message) {
        addMessage(message);
        if (ownThread == null) {
            setOwnThread(createReceiverThread(receiver, message));
            ownThread.start();
            return;
        }
        wakeUpThread();
    }

    /**
     * Notifies {@link MailBoxImpl#ownThread} that AbstractMailBox has unread messages.
     */
    private void wakeUpThread() {
        lock.lock();
        messageCondition.signal();
        lock.unlock();
    }

    private Thread createReceiverThread(ActorRefId receiver, Object message) {
        ActorRefIdImpl actorRefId = (ActorRefIdImpl) receiver;
        Thread thread = new Thread(() -> ((AbstractActor) actorRefId.getInstance()).receiveEmailFrom(actorRefId, message));
        thread.setUncaughtExceptionHandler(new ThreadExceptionHandler(actorRefId));
        return thread;
    }

    private Thread reviveReceiverThread(ActorRefId receiver) {
        return createReceiverThread(receiver, null);
    }

    /**
     * Drives the current thread into sleep for a certain time.
     *
     * @param milliseconds after this time thread wake up
     */
    void waiting(int milliseconds) throws InterruptedException {
        getLock().lock();
        getMessageCondition().await(milliseconds, TimeUnit.MILLISECONDS);
        getLock().unlock();
    }

    private void setOwnThread(Thread thread) {
        this.ownThread = thread;
    }

    Thread getOwnThread() {
        return ownThread;
    }

    private Lock getLock() {
        return lock;
    }

    private Condition getMessageCondition() {
        return messageCondition;
    }

    private class ThreadExceptionHandler implements Thread.UncaughtExceptionHandler {

        private ActorRefId receiver;

        ThreadExceptionHandler(ActorRefId receiver) {
            this.receiver = receiver;
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            LOGGER.info("Exception: '" + e + "', in thread: " + t);
            LOGGER.info("Actor reference id: " + receiver);
            LOGGER.info("To see the stacktrace use DEBUG logging level");
            Arrays.stream(t.getStackTrace()).forEach(stackTraceElement -> LOGGER.debug(stackTraceElement.toString()));

            setOwnThread(reviveReceiverThread(receiver));
            ownThread.start();
        }
    }
}
