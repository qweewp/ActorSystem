package com.qweewp.API.actor.responsiveness;

import API.actor.abstaract.ActorRefId;
import API.actor.impl.EcosystemImpl;
import org.junit.Test;

public class ResponsivenessActorTest {

    @Test
    public void shouldCheckHowMuchTimeItTakesToDeliverMessageToTheActor() throws InterruptedException {
        EcosystemImpl ecosystem = (EcosystemImpl) EcosystemImpl.create();
        ActorRefId actorRefId = ecosystem.actorOf(TimeDeliverDispatcher.class);

        actorRefId.tell("Check");

        Thread.sleep(1000);
    }
}
