package com.mc.classifiers;

/**
 * Created by Lasindu on 1/5/14.
 */
public abstract class ClassifierFactory {
    public abstract  Classifier getClassifier(String service);
}
