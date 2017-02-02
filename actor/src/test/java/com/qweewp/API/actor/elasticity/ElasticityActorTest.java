package com.qweewp.API.actor.elasticity;

import API.actor.abstaract.ActorRefId;
import API.actor.abstaract.Ecosystem;
import API.actor.impl.EcosystemImpl;
import com.qweewp.API.actor.elasticity.dispatcher.FiveWorkersDispatcher;
import com.qweewp.API.actor.elasticity.dispatcher.FourWorkerDispatcher;
import com.qweewp.API.actor.elasticity.dispatcher.OneWorkerDispatcher;
import com.qweewp.API.actor.elasticity.dispatcher.ThreeWorkerDispatcher;
import com.qweewp.API.actor.elasticity.dispatcher.TwoWorkerDispatcher;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ResourceBundle;

public class ElasticityActorTest {

    private static final int MILLIS_TIME_WAITING_BEFORE_ASK_AGAIN = 500;
    private static String FILE_PATH;
    private Ecosystem ecosystem;

    @BeforeClass
    public static void initResourceBundle() {
        ResourceBundle testcase = ResourceBundle.getBundle("testcase");
        FILE_PATH = testcase.getString("testDirectoryPath");
    }

    @Before
    public void initEcosystem() {
        ecosystem = EcosystemImpl.create();
    }

    @Test
    public void shouldCountSizeOfFilesUsingOneActor() throws InterruptedException {
        ActorRefId oneWorker = ecosystem.actorOf(OneWorkerDispatcher.class);
        oneWorker.tell(FILE_PATH);

        waitingForFinish();
    }

    @Test
    public void shouldCountSizeOfFilesUsingTwoActors() throws InterruptedException {
        ActorRefId twoWorker = ecosystem.actorOf(TwoWorkerDispatcher.class);
        twoWorker.tell(FILE_PATH);

        waitingForFinish();
    }

    @Test
    public void shouldCountSizeOfFilesUsingThreeActors() throws InterruptedException {
        ActorRefId threeWorkers = ecosystem.actorOf(ThreeWorkerDispatcher.class);
        threeWorkers.tell(FILE_PATH);

        waitingForFinish();
    }

    @Test
    public void shouldCountSizeOfFilesUsingFourActors() throws InterruptedException {
        ActorRefId fourWorkers = ecosystem.actorOf(FourWorkerDispatcher.class);
        fourWorkers.tell(FILE_PATH);

        waitingForFinish();
    }

    @Test
    public void shouldCountSizeOfFilesUsingFiveActors() throws InterruptedException {
        ActorRefId fiveWorkers = ecosystem.actorOf(FiveWorkersDispatcher.class);
        fiveWorkers.tell(FILE_PATH);

        waitingForFinish();
    }

    private void waitingForFinish() throws InterruptedException {
        while (ecosystem.isAlive()) {
            Thread.sleep(MILLIS_TIME_WAITING_BEFORE_ASK_AGAIN);
        }
    }
}
