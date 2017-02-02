package API.actor.impl;

import API.actor.abstaract.Actor;
import API.actor.abstaract.ActorContext;
import API.actor.abstaract.ActorRefId;
import API.actor.abstaract.Ecosystem;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class which holds {@link ActorRefId} with corresponding {@link Actor} implementation.
 */
public class ActorContextImpl implements ActorContext {

    private Ecosystem ecosystem;
    private Class<? extends Actor> actorClassType;

    private List<ActorRefIdImpl> actorRefIds = new CopyOnWriteArrayList<>();

    ActorContextImpl(Class<? extends Actor> actorClassType, Ecosystem ecosystem) {
        this.ecosystem = ecosystem;
        this.actorClassType = actorClassType;
    }

    /**
     * Shutdown this context.
     * It mean that all actors who belong to this context will be invalidated.
     */
    public void shutdown() {
        actorRefIds.forEach(ActorRefIdImpl::shutdown);
    }

    /**
     * {@inheritDoc}
     */
    void addActorRefId(ActorRefIdImpl actorRefId) {
        actorRefIds.add(actorRefId);
    }

    /**
     * {@inheritDoc}
     */
    public Ecosystem getEcosystem() {
        return ecosystem;
    }

    /**
     * {@inheritDoc}
     */
    public Class<? extends Actor> getType() {
        return actorClassType;
    }
}
