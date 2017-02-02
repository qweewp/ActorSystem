package API.actor.abstaract;

import java.util.UUID;

/**
 * Implicit unique id for Actor.
 * <p>
 * This class allows you to send messages to another {@link Actor}.
 */
public interface ActorRefId {

    /**
     * Send message to {@link Actor} without waiting answer from it.
     *
     * @param message message object
     */
    void tell(Object message);

    /**
     * Send message to {@link Actor} with specify sender actor, without waiting answer from it.
     *
     * @param message message object
     * @param sender  sender ActorRefId
     **/
    void tell(Object message, ActorRefId sender);

    /**
     * Return instance of {@link Actor} which belongs for this {@link ActorRefId}
     *
     * @return Actor object
     */
    Actor getInstance();

    /**
     * @return unique id.
     */
    UUID getUuid();

    /**
     * Shutdown this ActorRefId.
     */
    void shutdown();
}
