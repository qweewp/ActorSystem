package com.qweewp.API.actor.elasticity.dispatcher;

public class FiveWorkersDispatcher extends SeveralWorkersActor {
    @Override
    public long getCountOfWorkers() {
        return 5;
    }

    @Override
    public String getDispatcherName() {
        return "Five workers dispatcher";
    }
}
