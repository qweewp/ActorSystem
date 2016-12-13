package API.actor.impl;

import API.actor.abstaract.Actor;

import java.util.List;

/**
 * Container for {@link Actor} messages.
 */
abstract class MailBox {

    /**
     * Checks if {@link Actor} have unread messages.
     *
     * @return true in case if at least one message in message container
     */
    abstract boolean isThereMessage();

    /**
     * Adds message to FIFO container.
     *
     * @param message message
     */
    abstract void addMessage(Object message);

    /**
     * Receives message.
     *
     * @param receiver reference to recipient
     * @param message  message data
     */
    abstract void receiveMail(ActorRefId receiver, Object message);

    /**
     * Receives message with sender.
     *
     * @param receiver reference to recipient
     * @param message  message data
     * @param sender   {@link ActorRefId} to sender
     */
    abstract void receiveMail(ActorRefId receiver, Object message, ActorRefId sender);

    abstract Object getNextMessage();

    abstract List getMessages();

    abstract void setOwnThread(Thread thread);

    abstract Thread getOwnThread();
}
