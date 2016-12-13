package API.actor.impl;

import API.actor.abstaract.Actor;

/**
 * Actor inner implementation, exist only for defined in API methods.
 */
abstract class ActorImpl implements Actor {

    /**
     * Receive message from.
     *
     * @param receiver actor unique reference
     * @param message  message data
     */
    abstract void receiveEmailFrom(ActorRefId receiver, Object message);

    abstract void setSender(ActorRefId sender);

    abstract void setContext(ActorContext context);

    abstract void setSelf(ActorRefId receiver);

    abstract void setMailBox(MailBoxImpl mailBox);


}

