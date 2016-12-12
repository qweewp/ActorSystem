package API.actor.impl;

import API.actor.abstaract.Actor;
import API.actor.abstaract.IActorRefId;

import java.util.UUID;

/**
 * Unique reference for {@link Actor} instance.
 */
public class ActorRefId implements IActorRefId, Comparable<ActorRefId> {

    private final UUID uuid = UUID.randomUUID();

    private ActorContext context;
    private Actor instance;

    private MailBoxImpl mailBox;

    ActorRefId(Actor instance, ActorContext context, MailBoxImpl mailBox) {
        this.instance = instance;
        this.context = context;
        this.mailBox = mailBox;
    }

    @Override
    public void tell(Object message) {
        mailBox.receiveMail(this, message);
    }

    @Override
    public void tell(Object message, ActorRefId sender) {
        mailBox.receiveMail(this, message, sender);
    }

    @Override
    public int compareTo(ActorRefId other) {
        return this.getUuid().compareTo(other.getUuid());
    }

    public Actor getInstance() {
        return instance;
    }

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
