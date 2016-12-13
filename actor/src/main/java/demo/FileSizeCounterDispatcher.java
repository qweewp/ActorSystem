package demo;

import API.actor.impl.ActorRefId;
import API.actor.impl.UntypedActor;

import java.util.ArrayList;
import java.util.List;

/**
 * Dispatcher actor that splits the tasks between {@link FileSizeCounter}.
 */
public class FileSizeCounterDispatcher extends UntypedActor {
    private static final int WORKER_COUNT = 1;

    private Long sizeByte = 0L;
    private long pending = 0L;

    private long startTime = System.nanoTime();

    private List<ActorRefId> workers = new ArrayList<>();

    @Override
    public void preReceive() {
        for (int i = 0; i < WORKER_COUNT; i++) {
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
                System.out.println("Time taken: " + (System.nanoTime() - startTime) / 1.0e9);
                getActorContext().getEcosystem().shutdown();
            }
        }
    }
}
