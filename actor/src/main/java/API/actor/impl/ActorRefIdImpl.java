package API.actor.impl;

import API.actor.abstaract.Actor;
import API.actor.abstaract.ActorRefId;
import API.actor.abstaract.MailBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Unique reference for {@link Actor} instance.
 * It also provides the functionality to send messages to itself.
 */
class ActorRefIdImpl implements ActorRefId, Comparable<ActorRefIdImpl> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActorRefIdImpl.class);
    private UUID UNIQUE_ID = UUID.randomUUID();

    private Actor instance;
    private MailBoxImpl mailBox;

    ActorRefIdImpl(Actor instance, MailBoxImpl mailBox) {
        this.instance = instance;
        this.mailBox = mailBox;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tell(Object message) {
        mailBox.receiveMail(this, message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tell(Object message, ActorRefId sender) {
        mailBox.receiveMail(this, message, sender);
    }

    Object getNextMessage() {
        if (mailBox.hasUnreadMessage()) {
            return mailBox.getNextMessage();
        }
        return null;
    }

    @Override
    public void shutdown() {
        Thread ownThread = mailBox.getOwnThread();
        if (ownThread != null) {
            LOGGER.debug("To be interrupted: " + ownThread + ", " + getInstance());
            ownThread.interrupt();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(ActorRefIdImpl other) {
        return this.getUuid().compareTo(other.getUuid());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Actor getInstance() {
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UUID getUuid() {
        return UNIQUE_ID;
    }

    /**
     * {@inheritDoc}
     */
    MailBox getMailBox() {
        return mailBox;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ActorRefIdImpl: " + UNIQUE_ID.toString();
    }

}
