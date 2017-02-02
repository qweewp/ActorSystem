package API.actor.impl;

import API.actor.abstaract.Actor;
import API.actor.abstaract.MailBox;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Container for {@link Actor} messages.
 */
abstract class AbstractMailBox implements MailBox {

    private List<Object> MESSAGES = new CopyOnWriteArrayList<>();

    void addMessage(Object message) {
        MESSAGES.add(message);
    }

    boolean hasUnreadMessage() {
        return !MESSAGES.isEmpty();
    }

    Object getNextMessage() {
        return MESSAGES.remove(0);
    }

    List getMessages() {
        return MESSAGES;
    }
}
