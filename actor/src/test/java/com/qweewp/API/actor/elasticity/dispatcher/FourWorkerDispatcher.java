package com.qweewp.API.actor.elasticity.dispatcher;

public class FourWorkerDispatcher extends SeveralWorkersActor {
    @Override
    public long getCountOfWorkers() {
        return 4;
    }

    @Override
    public String getDispatcherName() {
        return "Four workers dispatcher";
    }
}
