/**
 *
 */
package models.com.mc.actors;

import mc.messages.ResultMessage;
import models.com.mc.classifiers.Classifier;
import models.com.mc.classifiers.ClassifierFactory;
import models.com.mc.classifiers.uclassify.UClassifierFactory;
import models.com.mc.configs.ClassifiersConfig;


import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * Classifying Actor who classify messages according to configured classifier
 */
public class ClassifyingActor extends UntypedActor {

    /**
     * Message Classifier
     */
    Classifier classifier;

    String service;

    /**
     * Constructor
     */
    public ClassifyingActor() {
        // TODO Auto-generated constructor stub
    }

    public ClassifyingActor(String service) {

        ClassifierFactory uClassifierFactory = new UClassifierFactory();
        classifier = uClassifierFactory.getClassifier(service);
        this.service = service;
    }


    public static Props props(String serivce) {
        return Props.create(ClassifyingActor.class, serivce);
    }

    /*
     * (non-Javadoc)
     *
     * @see akka.actor.UntypedActor#onReceive(java.lang.Object)
     */
    @Override
    public void onReceive(Object arg0) throws Exception {

        if (arg0 instanceof String) {

            try {

                String result = classifier.classify((String) arg0).toLowerCase();

                getSender().tell(new ResultMessage(service, result), getSelf());
            } catch (Exception e) {
                getSender().tell(new ResultMessage(service), getSelf());
            }
        } else
            unhandled(arg0);


    }

    //request the message again
    @Override
    public void postRestart(Throwable reason) {
        getContext().parent().tell(this.service, getSelf());
    }
}
