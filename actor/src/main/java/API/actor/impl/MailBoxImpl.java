package API.actor.impl;

import API.actor.abstaract.Actor;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Mail box is the main class that distributes messages between the {@link Actor}.
 */
class MailBoxImpl extends MailBox {

    private Thread ownThreadForActor;
    private ActorImpl actor;

    private List<Object> actorMessages = new CopyOnWriteArrayList<>();

    @Override
    void addMessage(Object message) {
        actorMessages.add(message);
    }

    boolean isThereMessages() {
        return !actorMessages.isEmpty();
    }

    @Override
    Object getNextMessage() {
        return actorMessages.remove(0);
    }

    @Override
    void receiveMail(ActorRefId actorRefId, Object message) {
        addMessage(message);
        if (ownThreadForActor != null) {
            wakeUpThread();
            return;
        }
        ownThreadForActor = new Thread(() -> ((ActorImpl) actorRefId.getInstance()).receiveEmailFrom(actorRefId, message));
        ownThreadForActor.start();
    }

    /**
     * Notifies {@link MailBoxImpl#ownThreadForActor} that MailBox has unread messages.
     */
    private void wakeUpThread() {
        synchronized (ownThreadForActor) {
            ownThreadForActor.notifyAll();
        }
    }

    @Override
    void receiveMail(ActorRefId actorRefId, Object message, ActorRefId sender) {
        actor.setSender(sender);
        receiveMail(actorRefId, message);
    }

    @Override
    Thread getOwnThread() {
        return ownThreadForActor;
    }

    @Override
    void setActor(ActorImpl newActor) {
        this.actor = newActor;
    }

    @Override
    List getMessages() {
        return actorMessages;
    }

    @Override
    void setOwnThread(Thread thread) {
        this.ownThreadForActor = thread;
    }
}
