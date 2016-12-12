package API.actor.abstaract;

import API.actor.impl.ActorContext;
import API.actor.impl.ActorRefId;

/**
 * Actor instance.
 */
public interface Actor {

    /**
     * Receives message and performs some actions with it.
     */
    public abstract void receive(Object message);

    /**
     * Performs some actions before receive the message.
     */
    public abstract void preReceive();

    /**
     * Performs some actions after receive the message.
     */
    public abstract void postReceive();

    /**
     * Gets {@link ActorRefId} corresponding for this Actor.
     *
     * @return unique reference for Actor
     */
    ActorRefId getSelf();

    /**
     * Gets {@link ActorRefId} corresponding for Actor which sent a message.
     *
     * @return unique reference for sender Actor
     */
    ActorRefId getSender();

    /**
     * Returns context correspinding to this Actor.
     *
     * @return context
     */
    ActorContext getActorContext();

}
