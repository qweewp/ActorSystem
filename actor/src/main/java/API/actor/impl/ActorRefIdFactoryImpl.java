package API.actor.impl;

import API.actor.abstaract.Actor;

import java.util.HashSet;
import java.util.Set;

/**
 * Creates ActorRefId for given {@link Actor} type class.
 */
class ActorRefIdFactoryImpl {

    private static final Set<ActorContext> contexts = new HashSet<>();

    /**
     * Create instance of {@link Actor}.
     *
     * @param actorClass class that implement {@link Actor}
     * @return reference for initialized {@link Actor} entity
     */
    synchronized static ActorRefId createActor(Class<? extends Actor> actorClass, Ecosystem ecosystems) {
        return createActorInstance(actorClass, ecosystems);
    }

    private static ActorRefId createActorInstance(Class<? extends Actor> actorClass, Ecosystem ecosystem) {
        ActorContext actorContext = contexts.stream()
                .filter(contextElem -> contextElem.getType().equals(actorClass))
                .findAny()
                .orElse(createNewContext(actorClass, ecosystem));

        MailBoxImpl mailBox = new MailBoxImpl();
        ActorImpl newActor = createActorInstance(actorClass);
        ActorRefId newActorRefId = createActorRefId(newActor, actorContext, mailBox);

        newActor.setContext(actorContext);
        newActor.setMailBox(mailBox);
        newActor.setSelf(newActorRefId);

        actorContext.addActorRefId(newActorRefId);

        mailBox.setActor(newActor);

        contexts.add(actorContext);

        return newActorRefId;
    }

    private static ActorContext createNewContext(Class<? extends Actor> actorClass, Ecosystem ecosystem) {
        return new ActorContext(actorClass, ecosystem);
    }

    private static ActorRefId createActorRefId(Actor actor, ActorContext context, MailBoxImpl mailBox) {
        return new ActorRefId(actor, context, mailBox);
    }

    private static ActorImpl createActorInstance(Class<? extends Actor> actorClass) {
        Actor actor = null;
        try {
            actor = actorClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("Failed while initialization Actor" + e);
        }
        return (ActorImpl) actor;
    }

    static Set<ActorContext> getContext() {
        return contexts;
    }
}
