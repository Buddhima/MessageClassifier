package models.com.mc.workers;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.pattern.Patterns;
import akka.util.Timeout;
import models.com.mc.actors.BroadcastingActor;
import models.com.mc.configs.ClassifiersConfig;
import models.com.mc.messages.TextMessage;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import java.util.concurrent.TimeoutException;

public class WorkExecutor extends UntypedActor {
    ActorRef broadcastingActor;
    ActorSelection messageCollectingActor;
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);


    @Override
    public void preStart() throws Exception {

        // Set Broadcasting Actor to the context of IntermediateActor
        broadcastingActor = getContext().actorOf(Props.create(BroadcastingActor.class), "broadcastingActor");
        //get the message collecting actor
        messageCollectingActor = getContext().actorSelection("/user/mcActor*");
    }


  @Override
  public void onReceive(Object message) throws TimeoutException {
    if (message instanceof TextMessage) {
//      String msg = (String) ( ((Work) message).getJob());
//      msg = msg+" is processed";
//      int temp = 1000000;
//      while(temp>0){
//        temp--;
//      }

        try {

            Timeout timeout = new Timeout(Duration.create(ClassifiersConfig.CLASSIFIER_SERVICE_TIMEOUT, "seconds"));
            Future<Object> future = Patterns.ask(broadcastingActor, message, timeout);
            TextMessage result = (TextMessage) Await.result(future, timeout.duration());
            System.out.println("RETURNED: "+result.getMessage());
            // Send to message store
            messageCollectingActor.tell(result, getSelf());

        } catch (TimeoutException te) {
            // TODO: handle exception
            System.out.println("Classifier Service Unavailable");
            throw te;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

      log.debug("Produced result {}", message);
      getSender().tell(new Worker.WorkComplete(message), getSelf());
    }
  }

