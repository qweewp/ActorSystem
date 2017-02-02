package API.actor.impl;

import API.actor.abstaract.Actor;
import API.actor.abstaract.ActorContext;
import API.actor.abstaract.ActorRefId;

/**
 * Actor inner implementation, exist only for defined in API methods.
 */
abstract class AbstractActor implements Actor {

    /**
     * Receive message from.
     *
     * @param receiver actor unique reference
     * @param message  message data
     */
    abstract void receiveEmailFrom(ActorRefIdImpl receiver, Object message);

    abstract void setSender(ActorRefId sender);

    abstract void setContext(ActorContext context);

    abstract void setSelf(ActorRefId receiver);

    abstract void setMailBox(MailBoxImpl mailBox);
}

