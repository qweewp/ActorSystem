package com.qweewp.API.actor.elasticity;

import API.actor.impl.ActorRefId;
import API.actor.impl.Ecosystem;
import com.qweewp.API.actor.elasticity.dispatcher.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ResourceBundle;

public class ElasticityActorTest {

    private static final int MILLIS_TIME_WAITING_BEFORE_ASK_AGAIN = 500;
    private static ResourceBundle testcase;
    private static String FILE_PATH;

    @BeforeClass
    public static void initResourceBundle() {
        testcase = ResourceBundle.getBundle("testcase");
        FILE_PATH = testcase.getString("testDirectoryPath");
    }

    @Test
    public void shouldCountSizeOfFilesUsingOneActor() throws InterruptedException {
        Ecosystem ecosystem = (Ecosystem) Ecosystem.create();

        ActorRefId oneWorker = ecosystem.actorOf(OneWorkerDispatcher.class);
        oneWorker.tell(FILE_PATH);

        while (ecosystem.isAlive()) {
            Thread.sleep(MILLIS_TIME_WAITING_BEFORE_ASK_AGAIN);
        }

    }

    @Test
    public void shouldCountSizeOfFilesUsingTwoActors() throws InterruptedException {
        Ecosystem ecosystem = (Ecosystem) Ecosystem.create();
        ActorRefId twoWorker = ecosystem.actorOf(TwoWorkerDispatcher.class);
        twoWorker.tell(FILE_PATH);
        while (ecosystem.isAlive()) {
            Thread.sleep(MILLIS_TIME_WAITING_BEFORE_ASK_AGAIN);
        }
    }

    @Test
    public void shouldCountSizeOfFilesUsingThreeActors() throws InterruptedException {
        Ecosystem ecosystem = (Ecosystem) Ecosystem.create();

        ActorRefId threeWorkers = ecosystem.actorOf(ThreeWorkerDispatcher.class);
        threeWorkers.tell(FILE_PATH);

        while (ecosystem.isAlive()) {
            Thread.sleep(MILLIS_TIME_WAITING_BEFORE_ASK_AGAIN);
        }
    }

    @Test
    public void shouldCountSizeOfFilesUsingFourActors() throws InterruptedException {
        Ecosystem ecosystem = (Ecosystem) Ecosystem.create();
        ActorRefId fourWorkers = ecosystem.actorOf(FourWorkerDispatcher.class);
        fourWorkers.tell(FILE_PATH);

        while (ecosystem.isAlive()) {
            Thread.sleep(MILLIS_TIME_WAITING_BEFORE_ASK_AGAIN);
        }
    }

    @Test
    public void shouldCountSizeOfFilesUsingFiveActors() throws InterruptedException {
        Ecosystem ecosystem = (Ecosystem) Ecosystem.create();
        ActorRefId fiveWorkers = ecosystem.actorOf(FiveWorkersDispatcher.class);
        fiveWorkers.tell(FILE_PATH);

        while (ecosystem.isAlive()) {
            Thread.sleep(MILLIS_TIME_WAITING_BEFORE_ASK_AGAIN);
        }
    }
}
