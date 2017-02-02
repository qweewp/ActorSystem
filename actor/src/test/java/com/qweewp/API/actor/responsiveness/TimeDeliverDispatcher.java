package com.qweewp.API.actor.responsiveness;

import API.actor.abstaract.ActorRefId;
import API.actor.impl.AbstractUntypedActor;

public class TimeDeliverDispatcher extends AbstractUntypedActor {

    private Long sendTime;

    @Override
    public void receive(Object message) {
        if (message instanceof String) {
            ActorRefId checker = getActorContext().getEcosystem().actorOf(TimeDeliverChecker.class);
            sendTime = System.nanoTime();
            checker.tell(sendTime, getSelf());
        } else if (message instanceof Long) {
            Long responseTime = System.nanoTime();
            Long deliverTime = (Long) message;

            long deliverSummary = deliverTime - sendTime;
            long responseSummary = responseTime - deliverTime;
            System.out.println("Deliver time in nano is: " + deliverSummary + ", in seconds: " + deliverSummary / 1.0e9);
            System.out.println("Response time in nano is: " + responseSummary + ", in seconds: " + responseSummary / 1.0e9);
        }
    }
}
