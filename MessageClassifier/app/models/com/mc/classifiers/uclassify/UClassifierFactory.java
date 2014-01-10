/**
 *
 */
package models.com.mc.classifiers.uclassify;


import models.com.mc.classifiers.Classifier;
import models.com.mc.classifiers.ClassifierFactory;
import models.com.mc.classifiers.uclassify.ContextClassifier;
import models.com.mc.classifiers.uclassify.GenderClassifier;
import models.com.mc.classifiers.uclassify.LanguageClassifier;
import models.com.mc.classifiers.uclassify.SpamClassifier;
import models.com.mc.configs.ClassifiersConfig;
import models.org.onesun.textmining.uclassify.ServiceType;



/**
 * Factory for generating Classifiers
 */
public class UClassifierFactory extends ClassifierFactory {

    /**
     *
     */
    public UClassifierFactory() {
        // TODO Auto-generated constructor stub
    }


    @Override
    public Classifier getClassifier(String service) {
        if (service.equalsIgnoreCase(ClassifiersConfig.CONTEXT_SERVICE) && ClassifiersConfig.DEPLOY_CONTEXT_CLASSIFIER)
            return new ContextClassifier();
        else if (service.equalsIgnoreCase(ClassifiersConfig.GENDER_SERVICE) && ClassifiersConfig.DEPLOY_GENDER_CLASSIFIER)
            return new GenderClassifier();
        else if (service.equalsIgnoreCase(ClassifiersConfig.LANGUAGE_SERVICE) && ClassifiersConfig.DEPLOY_LANGUAGE_CLASSIFIER)
            return new LanguageClassifier();
        else if (service.equalsIgnoreCase(ClassifiersConfig.SPAM_SERVICE) && ClassifiersConfig.DEPLOY_SPAM_CLASSIFIER)
            return new SpamClassifier();
        else return null;
    }
}
