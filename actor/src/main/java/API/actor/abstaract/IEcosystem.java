package API.actor.abstaract;

/**
 * Asynchronous client for Actors.
 */
public interface IEcosystem extends ActorRefIdFactory {

    /**
     * Shutdown actors ecosystem.
     */
    void shutdown();

    /**
     * Return is this Ecosystem is alive.
     */
    boolean isAlive();

}
