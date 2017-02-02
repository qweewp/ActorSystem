package API.actor.abstaract;

/**
 * Actor instance.
 */
public interface Actor {

    /**
     * Receive message and performs some actions with it.
     *
     * @param message message object
     */
    void receive(Object message);

    /**
     * Perform some actions before receive the message.
     */
    void preReceive();

    /**
     * Perform some actions after receive the message.
     */
    void postReceive();

    /**
     * Get {@link ActorRefId} corresponding for this Actor.
     *
     * @return ActorRefId for sender Actor
     */
    ActorRefId getSelf();

    /**
     * Get {@link ActorRefId} corresponding for Actor which sent a message.
     *
     * @return ActorRefId for sender Actor
     */
    ActorRefId getSender();

    /**
     * Returns context corresponding to this Actor.
     *
     * @return ActorContext object
     */
    ActorContext getActorContext();

}
