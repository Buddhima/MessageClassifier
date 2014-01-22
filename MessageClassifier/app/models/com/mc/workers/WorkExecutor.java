package models.com.mc.workers;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Function;
import akka.pattern.Patterns;
import akka.util.Timeout;
import mc.messages.TextMessage;
import models.com.mc.actors.BroadcastingActor;
import models.com.mc.configs.ClassifiersConfig;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import java.util.concurrent.TimeoutException;

import static akka.actor.SupervisorStrategy.stop;

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


    /*
    Supervisor Strategy to monitor child Actors and re-start the failed actors.
     */
    @Override
    public SupervisorStrategy supervisorStrategy() {
        return new OneForOneStrategy(-1, Duration.Inf(),
            new Function<Throwable, SupervisorStrategy.Directive>() {
                @Override
                public SupervisorStrategy.Directive apply(Throwable t) {
                    if (t instanceof ActorInitializationException)
                        return stop();
                    else if (t instanceof DeathPactException)
                        return stop();
                    else if (t instanceof Exception)
                        return stop();
                    else
                        return stop();
                }
            }
        );
    }

  }

