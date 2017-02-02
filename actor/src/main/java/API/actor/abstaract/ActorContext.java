package API.actor.abstaract;

/**
 * Context which holds {@link ActorRefId}.
 */
public interface ActorContext {

    /**
     * Return type of {@link Actor} which are stored in current context.
     *
     * @return Class type
     */
    Class<? extends Actor> getType();

    /**
     * Shutdown this context.
     * <p>
     * It mean that all actors who belong to this context will be invalidated.
     */
    void shutdown();

    /**
     * Return {@link Ecosystem} to which this context refers.
     *
     * @return Ecosystem object
     */
    Ecosystem getEcosystem();
}
