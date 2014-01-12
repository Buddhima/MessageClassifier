package worker;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com_messages.Work;

public class WorkExecutor extends UntypedActor {

  private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

  @Override
  public void onReceive(Object message) {
    if (message instanceof Work) {
      String msg = (String) ( ((Work) message).getJob());
      msg = msg+" is processed";
      int temp = 1000000;
      while(temp>0){
        temp--;
      }


      log.debug("Produced result {}", msg);
      getSender().tell(new Worker.WorkComplete(msg), getSelf());
    }
  }
}
