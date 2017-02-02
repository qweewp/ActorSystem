package demo;

import API.actor.abstaract.ActorRefId;
import API.actor.abstaract.Ecosystem;
import API.actor.impl.EcosystemImpl;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Class demonstrates working of Actor system.
 */
public class Demo {

    public static void main(String[] args) throws InterruptedException, IOException {
        ResourceBundle testCase = ResourceBundle.getBundle("demo");
        String filePath = testCase.getString("demoDirectoryPath");

        Ecosystem ecosystem = EcosystemImpl.create();

        ActorRefId fileSizeCounterDispatcher = ecosystem.actorOf(FileSizeCounterDispatcher.class);

        fileSizeCounterDispatcher.tell(filePath);
    }
}
