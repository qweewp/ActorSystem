package com.qweewp.API.actor.resiliency;

import API.actor.impl.UntypedActor;

/**
 * Adds an author in given message and returns it to sender.
 */
public class AddAuthorActor extends UntypedActor {

    @Override
    public void receive(Object message) {
        System.out.println("Long calculations..." + Thread.currentThread());
        if (true) throw new RuntimeException("Oops.. Incorrect input message: " + message);
        System.out.println("Finish long calculations.");
    }
}
