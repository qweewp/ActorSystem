package API.actor.impl;

import API.actor.abstaract.Actor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Actor with untyped message handle
 */
public abstract class UntypedActor extends ActorImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(UntypedActor.class);

    private ActorContext actorContext;
    private MailBoxImpl mailBox;
    private ActorRefId selfRef;
    private ActorRefId sender;

    public UntypedActor() {
    }

    /**
     * @see Actor#preReceive()
     */
    @Override
    public void preReceive() {

    }

    /**
     * @see Actor#postReceive()
     */
    @Override
    public void postReceive() {

    }

    @Override
    void receiveEmailFrom(ActorRefId actorRefId, Object message) {
        preReceive();
        try {
            while (true) {
                while (getSelf().getMailBox().isThereMessages()) {
                    receive(getSelf().getMailBox().getNextMessage());
                }
                sleep();
            }
        } catch (InterruptedException e) {
            LOGGER.debug("Interrupted while waiting: " + Thread.currentThread());
        } finally {
            postReceive();
        }
    }

    /**
     * Drives current thread into sleep.
     */
    private void sleep() throws InterruptedException {
        synchronized (Thread.currentThread()) {
            Thread.currentThread().wait();
        }
    }

    @Override
    public ActorContext getActorContext() {
        return actorContext;
    }

    @Override
    public ActorRefId getSender() {
        return sender;
    }

    @Override
    void setSender(ActorRefId actorRefId) {
        this.sender = actorRefId;
    }

    @Override
    void setContext(ActorContext context) {
        this.actorContext = context;
    }

    @Override
    void setMailBox(MailBoxImpl mailBox) {
        this.mailBox = mailBox;
    }

    @Override
    void setSelf(ActorRefId actorRefId) {
        this.selfRef = actorRefId;
    }

    @Override
    public ActorRefId getSelf() {
        return selfRef;
    }
}
