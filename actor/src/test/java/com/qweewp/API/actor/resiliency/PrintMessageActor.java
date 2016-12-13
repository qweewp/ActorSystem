package com.qweewp.API.actor.resiliency;

import API.actor.impl.ActorRefId;
import API.actor.impl.UntypedActor;

/**
 * Print message Actor.
 */
public class PrintMessageActor extends UntypedActor {

    @Override
    public void receive(Object message) {
        if (message instanceof String && ((String) message).contains(" ")) {
            ActorRefId addAuthorActorRefId = getActorContext().getEcosystem().actorOf(AddAuthorActor.class);

            String[] split = ((String) message).split(" ");
            for (String part : split) {
                addAuthorActorRefId.tell(part, getSelf());
            }
        }
    }
}
