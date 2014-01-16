package models.com.mc.workers;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.contrib.pattern.DistributedPubSubExtension;
import akka.contrib.pattern.DistributedPubSubMediator;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import models.com.mc.messages.TextMessage;
import models.com.mc.messages.store.ObjectDBMsgStore;
import models.com.mc.workers.Master.WorkResult;

public class MessageCollector extends UntypedActor {

    ObjectDBMsgStore odbMsgStore;

    /* (non-Javadoc)
     * @see akka.actor.UntypedActor#preStart()
     */
    @Override
    public void preStart() throws Exception {
        super.preStart();

        // Set ObjectDBMsgStore instance
        odbMsgStore = new ObjectDBMsgStore();
    }

    private ActorRef mediator = DistributedPubSubExtension.get(getContext().system()).mediator();
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    {
        mediator.tell(new DistributedPubSubMediator.Subscribe(Master.ResultsTopic, getSelf()), getSelf());
    }




    @Override
    public void onReceive(Object message) {
        if (message instanceof DistributedPubSubMediator.SubscribeAck) {
            // do nothing
        }
        else if (message instanceof WorkResult) {
            WorkResult workResult = (WorkResult) message;
            log.info("Consumed result: {}", workResult.result);
            //save messages
            odbMsgStore.offer((TextMessage) workResult.getResult());
        }
        else {
            unhandled(message);
        }
    }
}
