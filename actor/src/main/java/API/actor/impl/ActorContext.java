package API.actor.impl;

import API.actor.abstaract.Actor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class which holds {@link ActorRefId} with corresponding {@link Actor} implementation.
 */
public class ActorContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActorContext.class);
    private Ecosystem ecosystem;
    private Class actorClassType;

    private List<ActorRefId> actorRefIds = new CopyOnWriteArrayList<>();

    ActorContext(Class<? extends Actor> actorClassType, Ecosystem ecosystem) {
        this.ecosystem = ecosystem;
        this.actorClassType = actorClassType;
    }


    /**
     * Shutdown this context.
     * It mean that all actor who belong to this context will invalidate.
     */
    public void shutdown() {
        for (ActorRefId refId : actorRefIds) {
            Thread ownThread = refId.getMailBox().getOwnThread();
            if (ownThread != null) {
                LOGGER.debug("To be interrupted: " + ownThread + ", " + refId.getInstance());
                ownThread.interrupt();
            }
        }
    }

    /**
     * Adds {@link ActorRefId} to this context.
     */
    void addActorRefId(ActorRefId actorRefId) {
        actorRefIds.add(actorRefId);
    }


    /**
     * Returns all {@link Actor} belonging to this context.
     */
    public List<ActorRefId> getActorRefIds() {
        return actorRefIds;
    }

    public Ecosystem getEcosystem() {
        return ecosystem;
    }


    public Class getType() {
        return actorClassType;
    }
}
