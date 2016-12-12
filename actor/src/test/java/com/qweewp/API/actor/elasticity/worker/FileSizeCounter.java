package com.qweewp.API.actor.elasticity.worker;

import API.actor.impl.UntypedActor;

import java.io.File;

public class FileSizeCounter extends UntypedActor {

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
