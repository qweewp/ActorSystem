package API.actor.abstaract;

import API.actor.impl.ActorRefId;

/**
 * Implicit unique id for Actor.
 * From this class you can sent messages for another this actor.
 */
public interface IActorRefId {

    /**
     * Send message to {@link Actor} without waiting answer from it.
     */
    void tell(Object message);

    /**
     * Send message to {@link Actor} with specify sender actor, without waiting answer from it.
     **/
    void tell(Object message, ActorRefId sender);

    /**
     * Return instance of {@link Actor} which belongs for this {@link IActorRefId}
     */
    Actor getInstance();

}
