package models.com.mc.workers;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.contrib.pattern.DistributedPubSubExtension;
import akka.contrib.pattern.DistributedPubSubMediator;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import mc.messages.TextMessage;
import mc.messages.publisher.MessagePublisher;
import mc.messages.store.ObjectDBMsgStore;

public class ThriftMessageCollector extends UntypedActor {
    MessagePublisher messagePublisher;

    @Override
    public void preStart() throws Exception {
        super.preStart();
        messagePublisher = new MessagePublisher();
        messagePublisher.initialize(null,null);  //initialize with default configs
    }

    private ActorRef mediator = DistributedPubSubExtension.get(getContext().system()).mediator();
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    {
        mediator.tell(new DistributedPubSubMediator.Subscribe(Master.ResultsTopic, getSelf()), getSelf());
    }


    /**
     * This method will invoke thrift message publisher
     * upon receiving classified messages.
     * @param message classified message
     */
    @Override
    public void onReceive(Object message) {
        if (message instanceof DistributedPubSubMediator.SubscribeAck) {
            // do nothing
        }
        else if (message instanceof Master.WorkResult) {
            Master.WorkResult workResult = (Master.WorkResult) message;
            log.info("published result: {}", workResult.result);
            messagePublisher.publish((TextMessage) message);
        }
        else {
            unhandled(message);
        }
    }
}
