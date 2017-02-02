package com.qweewp.API.actor.resiliency;

import API.actor.impl.AbstractUntypedActor;

/**
 * Adds an author in given message and returns it to sender.
 */
public class AddAuthorActor extends AbstractUntypedActor {

    @Override
    public void receive(Object message) {
        System.out.println("Long processing..." + Thread.currentThread());
        if (true) throw new RuntimeException("Oops.. Incorrect input message: " + message);
        System.out.println("Finish long processing.");
    }
}
