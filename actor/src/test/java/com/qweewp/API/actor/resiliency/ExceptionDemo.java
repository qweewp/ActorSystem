package com.qweewp.API.actor.resiliency;

import API.actor.abstaract.Actor;
import API.actor.abstaract.ActorRefId;
import API.actor.abstaract.Ecosystem;
import API.actor.impl.EcosystemImpl;
import org.junit.Test;

/**
 * Demonstrates how long it takes to revive the {@link Actor}.
 */
public class ExceptionDemo {

    @Test
    public void shouldCountReviveTheActorThread() throws InterruptedException {
        Ecosystem ecosystem = EcosystemImpl.create();
        ActorRefId printMessageActorRefId = ecosystem.actorOf(PrintMessageActor.class);

        printMessageActorRefId.tell("This program written by: ");

        Thread.sleep(1000);
        ecosystem.shutdown();
    }
}

