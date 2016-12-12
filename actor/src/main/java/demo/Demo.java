package demo;

import API.actor.abstaract.IActorRefId;
import API.actor.abstaract.IEcosystem;
import API.actor.impl.Ecosystem;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Class demonstrates working of Actor system.
 */
public class Demo {

    public static void main(String[] args) throws InterruptedException, IOException {
        ResourceBundle testCase = ResourceBundle.getBundle("demo");
        String filePath = testCase.getString("demoDirectoryPath");

        IEcosystem ecosystem = Ecosystem.create();

        IActorRefId fileSizeCounterDispatcher = ecosystem.actorOf(FileSizeCounterDispatcher.class);

        fileSizeCounterDispatcher.tell(filePath);
    }
}
