package API.actor.impl;

import API.actor.abstaract.Actor;
import API.actor.abstaract.IActorRefId;

import java.util.UUID;

/**
 * Unique reference for {@link Actor} instance.
 * It also provides the functionality to send messages to itself.
 */
public class ActorRefId implements IActorRefId, Comparable<ActorRefId> {

    private final UUID uuid = UUID.randomUUID();

    private Actor instance;
    private MailBoxImpl mailBox;

    ActorRefId(Actor instance, MailBoxImpl mailBox) {
        this.instance = instance;
        this.mailBox = mailBox;
    }

    /**
     * @see IActorRefId#tell(Object)
     */
    @Override
    public void tell(Object message) {
        mailBox.receiveMail(this, message);
    }

    /**
     * @see IActorRefId#tell(Object, ActorRefId)
     */
    @Override
    public void tell(Object message, ActorRefId sender) {
        mailBox.receiveMail(this, message, sender);
    }

    @Override
    public int compareTo(ActorRefId other) {
        return this.getUuid().compareTo(other.getUuid());
    }

    @Override
    public Actor getInstance() {
        return instance;
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    public MailBox getMailBox() {
        return mailBox;
    }

    @Override
    public String toString() {
        return "ActorRefId: " + uuid.toString();
    }

}
