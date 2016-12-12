package com.qweewp.API.actor.responsiveness;

import API.actor.impl.UntypedActor;

/**
 * Created by Andrey on 12.12.2016.
 */
public class TimeDeliverChecker extends UntypedActor {

    @Override
    public void receive(Object message) {
        Long receiveTime = System.nanoTime();
        getSender().tell(receiveTime);
    }
}
