package API.actor.abstaract;

import API.actor.impl.ActorRefId;

/**
 * Interface for creating {@link Actor}'s.
 * If class implements this interface it allows him to create {@link Actor}'s.
 */
public interface ActorRefIdFactory {

    /**
     * Create {@link Actor} and add him in to the context.
     *
     * @param actorClass class which will be initialized.
     * @return unique reference for this actor.
     */
    ActorRefId actorOf(Class<? extends Actor> actorClass);
}
