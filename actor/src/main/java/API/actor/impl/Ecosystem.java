package API.actor.impl;

import API.actor.abstaract.Actor;
import API.actor.abstaract.ActorRefIdFactory;
import API.actor.abstaract.IEcosystem;

/**
 * Actors system. From this class everything starts.
 */
public class Ecosystem implements IEcosystem {

    private static IEcosystem ecosystem;

    private Ecosystem() {
    }

    /**
     * Creating ecosystem in which {@link Actor}s are exist.
     *
     * @return new ecosystem
     * @throws IllegalStateException in case where create is called several times on one instance.
     */
    public static IEcosystem create() {
        if (ecosystem != null) {
            throw new IllegalStateException("Already initialized.");
        }
        ecosystem = new Ecosystem();
        return ecosystem;
    }


    /**
     * @see ActorRefIdFactory#actorOf(Class)
     */
    @Override
    public ActorRefId actorOf(Class<? extends Actor> actorClass) {
        return ActorRefIdFactoryImpl.createActor(actorClass, this);
    }

    /**
     * @see IEcosystem#shutdown()
     */
    @Override
    public void shutdown() {
        ActorRefIdFactoryImpl.getContext().forEach(ActorContext::shutdown);
        ecosystem = null;
    }

    @Override
    public boolean isAlive() {
        return ecosystem != null;
    }
}
