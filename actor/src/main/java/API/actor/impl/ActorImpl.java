package API.actor.impl;

import API.actor.abstaract.Actor;

/**
 * Actor inner implementation, exist only for defined in API methods.
 */
public abstract class ActorImpl implements Actor {

    abstract void receiveEmailFrom(ActorRefId sender, Object message);

    abstract void setSender(ActorRefId actorRefId);

    abstract void setContext(ActorContext context);

    abstract void setSelf(ActorRefId actorRefId);

    abstract void setMailBox(MailBoxImpl mailBox);


}

