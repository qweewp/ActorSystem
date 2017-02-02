package API.actor.impl;

import API.actor.abstaract.Actor;
import API.actor.abstaract.ActorContext;
import API.actor.abstaract.ActorRefId;
import API.actor.abstaract.Ecosystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * Creates ActorRefIdImpl for given {@link Actor} type class.
 */
class ActorRefIdFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActorRefIdFactory.class);

    private static final Set<ActorContextImpl> CONTEXTS = new HashSet<>();

    synchronized static ActorRefId createActor(Class<? extends Actor> actorClass, Ecosystem ecosystems) {
        return createActorInstance(actorClass, ecosystems);
    }

    private static ActorRefId createActorInstance(Class<? extends Actor> actorClass, Ecosystem ecosystem) {
        ActorContextImpl actorContext = CONTEXTS.stream()
                .filter(contextElem -> contextElem.getType().equals(actorClass))
                .findAny()
                .orElse(createNewContext(actorClass, ecosystem));

        MailBoxImpl mailBox = new MailBoxImpl();

        AbstractActor newActor = createActorInstance(actorClass, actorContext, mailBox);
        ActorRefIdImpl newActorRefId = createActorRefId(newActor, mailBox);

        newActor.setSelf(newActorRefId);
        actorContext.addActorRefId(newActorRefId);

        CONTEXTS.add(actorContext);
        return newActorRefId;
    }

    private static ActorContextImpl createNewContext(Class<? extends Actor> actorClass, Ecosystem ecosystem) {
        return new ActorContextImpl(actorClass, ecosystem);
    }

    private static ActorRefIdImpl createActorRefId(Actor actor, MailBoxImpl mailBox) {
        return new ActorRefIdImpl(actor, mailBox);
    }

    private static AbstractActor createActorInstance(Class<? extends Actor> actorClass, ActorContext context, MailBoxImpl mailBox) {
        AbstractActor actor = null;
        try {
            actor = (AbstractActor) actorClass.newInstance();
            actor.setContext(context);
            actor.setMailBox(mailBox);
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error("Failed while initialization Actor" + e);
        }
        return actor;
    }

    static Set<ActorContextImpl> getContext() {
        return CONTEXTS;
    }
}
