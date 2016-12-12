package com.qweewp.API.actor.elasticity.dispatcher;

public class ThreeWorkerDispatcher extends SeveralWorkersActor {

    @Override
    public long getCountOfWorkers() {
        return 3;
    }


    @Override
    public String getDispatcherName() {
        return "Three Workers Dispatcher";
    }
}
