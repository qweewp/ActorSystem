package API.actor.impl;

import API.actor.abstaract.Actor;

import java.util.List;

/**
 * Created by Andrey on 10.12.2016.
 */
abstract class MailBox {

    /**
     * Checks if {@link Actor} have unread messages.
     *
     * @return true in case if at least one message in message container
     */
    abstract boolean isThereMessages();

    /**
     * Adds message to FIFO container.
     *
     * @param message message
     */
    abstract void addMessage(Object message);

    /**
     * Receives message.
     *
     * @param actorRefId reference to recipient
     * @param message    message data
     */
    abstract void receiveMail(ActorRefId actorRefId, Object message);

    /**
     * Receives message with sender.
     *
     * @param actorRefId reference to recipient
     * @param message    message data
     * @param sender     {@link ActorRefId} to sender
     */
    abstract void receiveMail(ActorRefId actorRefId, Object message, ActorRefId sender);

    abstract Object getNextMessage();

    abstract Thread getOwnThread();

    abstract void setActor(ActorImpl newActor);

    abstract List getMessages();

    abstract void setOwnThread(Thread thread);
}
