package com.qweewp.API.actor.resiliency;

import API.actor.abstaract.Actor;
import API.actor.abstaract.IEcosystem;
import API.actor.impl.ActorRefId;
import API.actor.impl.Ecosystem;
import org.junit.Test;

/**
 * Demonstrates how long it takes to revive the {@link Actor}.
 */
public class ExceptionDemo {

    @Test
    public void shouldCountReviveTheActorThread() throws InterruptedException {
        IEcosystem ecosystem = Ecosystem.create();
        ActorRefId printMessageActorRefId = ecosystem.actorOf(PrintMessageActor.class);

        printMessageActorRefId.tell("This program written by: ");

        Thread.sleep(1000);
        ecosystem.shutdown();
    }
}

