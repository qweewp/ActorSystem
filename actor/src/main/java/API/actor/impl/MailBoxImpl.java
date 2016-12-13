package API.actor.impl;

import API.actor.abstaract.Actor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Mail box is the main class that distributes messages between the {@link Actor}.
 * It creates or wake up receiver Actor thread.
 */
class MailBoxImpl extends MailBox {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailBox.class);

    private Thread ownThread;
    private final Lock lock;
    private final Condition messageCondition;

    MailBoxImpl() {
        lock = new ReentrantLock(true);
        messageCondition = lock.newCondition();
    }

    private List<Object> actorMessages = new CopyOnWriteArrayList<>();

    @Override
    void addMessage(Object message) {
        actorMessages.add(message);
    }

    @Override
    boolean isThereMessage() {
        return !actorMessages.isEmpty();
    }

    @Override
    Object getNextMessage() {
        return actorMessages.remove(0);
    }

    @Override
    void receiveMail(ActorRefId receiver, Object message) {
        addMessage(message);
        if (ownThread == null) {
            setOwnThread(createReceiverThread(receiver, message));
            ownThread.start();
            return;
        }
        wakeUpThread();
    }

    private Thread createReceiverThread(ActorRefId receiver, Object message) {
        Thread thread = new Thread(() -> ((ActorImpl) receiver.getInstance()).receiveEmailFrom(receiver, message));
        thread.setUncaughtExceptionHandler(new ThreadExceptionHandler(receiver));
        return thread;
    }

    private Thread reviveReceiverThread(ActorRefId receiver) {
        return createReceiverThread(receiver, null);
    }

    /**
     * Notifies {@link MailBoxImpl#ownThread} that MailBox has unread messages.
     */
    private void wakeUpThread() {
        lock.lock();
        messageCondition.signal();
        lock.unlock();
    }

    @Override
    void receiveMail(ActorRefId receiver, Object message, ActorRefId sender) {
        ((ActorImpl) receiver.getInstance()).setSender(sender);
        receiveMail(receiver, message);
    }

    @Override
    List getMessages() {
        return actorMessages;
    }

    @Override
    void setOwnThread(Thread thread) {
        this.ownThread = thread;
    }

    @Override
    Thread getOwnThread() {
        return ownThread;
    }

    public Lock getLock() {
        return lock;
    }

    public Condition getMessageCondition() {
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
