package API.actor.impl;

import API.actor.abstaract.Actor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

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
    void receiveEmailFrom(ActorRefId receiver, Object message) {
        preReceive();
        try {
            while (true) {
                while (receiver.getMailBox().isThereMessage()) {
                    receive(receiver.getMailBox().getNextMessage());
                }
                waiting(1000);
            }
        } catch (InterruptedException e) {
            LOGGER.debug("Interrupted while waiting: " + Thread.currentThread());
        } finally {
            postReceive();
        }
    }

    /**
     * Drives the current thread to sleep for a certain time.
     *
     * @param milliseconds after this time thread wake up
     */
    private void waiting(long milliseconds) throws InterruptedException {
        mailBox.getLock().lock();
        mailBox.getMessageCondition().await(milliseconds, TimeUnit.MILLISECONDS);
        mailBox.getLock().unlock();
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
    void setSender(ActorRefId sender) {
        this.sender = sender;
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
    void setSelf(ActorRefId receiver) {
        this.selfRef = receiver;
    }

    @Override
    public ActorRefId getSelf() {
        return selfRef;
    }
}
