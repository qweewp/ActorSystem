package com.qweewp.API.actor.responsiveness;

import API.actor.impl.ActorRefId;
import API.actor.impl.Ecosystem;
import org.junit.Test;

public class ResponsivenessActorTest {

    @Test
    public void shouldCheckHowMuchTimeItTakesToDeliverMessageToTheActor() throws InterruptedException {
        Ecosystem ecosystem = (Ecosystem) Ecosystem.create();
        ActorRefId actorRefId = ecosystem.actorOf(TimeDeliverDispatcher.class);

        actorRefId.tell("Check");

        Thread.sleep(1000);
    }
}
