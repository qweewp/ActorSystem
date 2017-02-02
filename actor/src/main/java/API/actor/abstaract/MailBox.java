package API.actor.abstaract;

/**
 * Message receiver for {@link Actor}.
 */
public interface MailBox {

    /**
     * Receives message.
     *
     * @param receiver reference to recipient
     * @param message  message data
     */
    void receiveMail(ActorRefId receiver, Object message);

    /**
     * Receive message with sender.
     *
     * @param receiver reference to recipient
     * @param message  message data
     * @param sender   {@link ActorRefId} to sender
     */
    void receiveMail(ActorRefId receiver, Object message, ActorRefId sender);
}
