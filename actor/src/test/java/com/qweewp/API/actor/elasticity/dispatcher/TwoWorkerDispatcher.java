package com.qweewp.API.actor.elasticity.dispatcher;

public class TwoWorkerDispatcher extends SeveralWorkersActor {

    @Override
    public long getCountOfWorkers() {
        return 2;
    }

    @Override
    public String getDispatcherName() {
        return "Two Worker Dispatcher";
    }
}
