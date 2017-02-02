package demo;

import API.actor.impl.AbstractUntypedActor;

import java.io.File;

/**
 * Actor that calculate size of all files in given directory
 * and if given directory contains sub directory it sends it to sender.
 */
public class FileSizeCounter extends AbstractUntypedActor {

    private Long sizeKb = 0L;

    @Override
    public void receive(Object message) {
        if (message instanceof String) {
            String path = (String) message;
            File files = new File(path);
            if (files.listFiles() != null) {
                for (File file : files.listFiles()) {
                    if (file.isFile()) {
                        sizeKb += file.length();
                    } else {
                        getSender().tell(file.getPath());
                    }
                }
            }
            getSender().tell(sizeKb);
            sizeKb = 0L;
        }
    }
}
