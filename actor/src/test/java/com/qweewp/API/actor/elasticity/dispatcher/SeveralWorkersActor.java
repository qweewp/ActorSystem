package com.qweewp.API.actor.elasticity.dispatcher;

import API.actor.impl.ActorRefId;
import API.actor.impl.UntypedActor;
import com.qweewp.API.actor.elasticity.worker.FileSizeCounter;

import java.util.ArrayList;
import java.util.List;

/**
 * General class for testing.
 */
public abstract class SeveralWorkersActor extends UntypedActor {
    private String dispatcherName;
    private long coutOfWorkers;

    private List<ActorRefId> workers = new ArrayList<>();
    private long sizeByte = 0;
    private long pending;

    private long startTime = System.nanoTime();

    @Override
    public void preReceive() {
        for (int i = 0; i < getCountOfWorkers(); i++) {
            workers.add(getActorContext().getEcosystem().actorOf(FileSizeCounter.class));
        }
    }

    @Override
    public void receive(Object message) {
        if (message instanceof String) {
            pending++;
            ActorRefId worker = workers.remove(0);
            worker.tell(message, getSelf());
            workers.add(worker);
        } else if (message instanceof Long) {
            sizeByte += (Long) message;
            pending--;
            if (pending == 0) {
                System.out.println("Files size in bytes: " + sizeByte);
                System.out.println(getDispatcherName() + " - time taken: " + (System.nanoTime() - startTime) / 1.0e9);
                getActorContext().getEcosystem().shutdown();
            }
        }
    }

    public abstract long getCountOfWorkers();

    public abstract String getDispatcherName();

    public void setDispatcherName(String dispatcherName) {
        this.dispatcherName = dispatcherName;
    }
}
