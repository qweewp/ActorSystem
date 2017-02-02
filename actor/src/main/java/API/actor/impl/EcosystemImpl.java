package API.actor.impl;

import API.actor.abstaract.Actor;
import API.actor.abstaract.ActorContext;
import API.actor.abstaract.ActorRefId;
import API.actor.abstaract.Ecosystem;

import java.util.Objects;

/**
 * Actors system. From this class everything starts.
 */
public class EcosystemImpl implements Ecosystem {

    private static Ecosystem ecosystem;

    private EcosystemImpl() {
    }

    /**
     * Creating ecosystem in which {@link Actor}s are exist.
     *
     * @return Ecosystem object
     * @throws IllegalStateException in case where create is called several times on one instance.
     */
    public static Ecosystem create() {
        if (ecosystem != null) {
            throw new IllegalStateException("Already initialized.");
        }
        ecosystem = new EcosystemImpl();
        return ecosystem;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ActorRefId actorOf(Class<? extends Actor> actorClass) {
        Objects.requireNonNull(actorClass);
        return ActorRefIdFactory.createActor(actorClass, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shutdown() {
        ActorRefIdFactory.getContext().forEach(ActorContext::shutdown);
        ecosystem = null;
    }

    @Override
    public boolean isAlive() {
        return ecosystem != null;
    }
}
