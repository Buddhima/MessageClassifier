/**
 *
 */
package com.mc.classifiers.uclassify;

import com.mc.classifiers.Classifier;
import com.mc.classifiers.ClassifierFactory;
import com.mc.configs.ClassifiersConfig;
import org.onesun.textmining.uclassify.ServiceType;

import com.mc.classifiers.uclassify.ContextClassifier;
import com.mc.classifiers.uclassify.GenderClassifier;
import com.mc.classifiers.uclassify.LanguageClassifier;
import com.mc.classifiers.uclassify.SpamClassifier;

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
