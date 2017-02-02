package API.actor.impl;

import API.actor.abstaract.ActorContext;
import API.actor.abstaract.ActorRefId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Actor with untyped message handle
 */
public abstract class AbstractUntypedActor extends AbstractActor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractUntypedActor.class);

    private ActorContext actorContext;
    private MailBoxImpl mailBox;
    private ActorRefId selfRef;
    private ActorRefId sender;

    /**
     * {@inheritDoc}
     */
    @Override
    public void preReceive() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void postReceive() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    void receiveEmailFrom(ActorRefIdImpl receiver, Object message) {
        preReceive();
        try {
            while (true) {
                Object nextMessage;
                while ((nextMessage = receiver.getNextMessage()) != null) {
                    receive(nextMessage);
                }
                mailBox.waiting(1000);
            }
        } catch (InterruptedException e) {
            LOGGER.debug("Interrupted while waiting: " + Thread.currentThread());
        } finally {
            postReceive();
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
