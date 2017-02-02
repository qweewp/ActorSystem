package com.qweewp.API.actor.responsiveness;

import API.actor.impl.AbstractUntypedActor;

public class TimeDeliverChecker extends AbstractUntypedActor {

    @Override
    public void receive(Object message) {
        Long receiveTime = System.nanoTime();
        getSender().tell(receiveTime);
    }
}
