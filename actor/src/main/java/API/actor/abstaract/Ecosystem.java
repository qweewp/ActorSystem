package API.actor.abstaract;

/**
 * Asynchronous client for Actors.
 */
public interface Ecosystem{

    /**
     * Shutdown actors ecosystem.
     */
    void shutdown();

    /**
     * Check whether the Ecosystem is alive.
     *
     * @return <code>true</code> in case if Ecosystem is alive otherwise <code>false</code>
     */
    boolean isAlive();

    /**
     * Create {@link Actor} and add him in to the corresponding context.
     *
     * @param actorClass class which will be initialized.
     * @return unique reference for this actor.
     */
    ActorRefId actorOf(Class<? extends Actor> actorClass);
}
