package com.qweewp.API.actor.elasticity.dispatcher;

public class OneWorkerDispatcher extends SeveralWorkersActor {


    @Override
    public long getCountOfWorkers() {
        return 1;
    }


    @Override
    public String getDispatcherName() {
        return "One Worker Dispatcher";
    }
}
