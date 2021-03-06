package models.com.mc.classifiers.uclassify;

import models.com.mc.classifiers.Classifier;
import models.com.mc.configs.ClassifiersConfig;


public class UClassifyClassifierTest {

	Classifier classifier;
	String message;

	public UClassifyClassifierTest() {
		// TODO Auto-generated constructor stub
		message = "A new survey has been launched in the United Kingdom to unearth the true nature of cyber stalking in the country."
				+ "\n"
				+ "The Network for Surviving Stalking has issued an \"Electronic Communication Harassment Observation\" or ECHO questionnaire in collaboration with the scientists at the University of Bedfordshire."
				+ "\n"
				+ "The survey has been commissioned to classify those who have been stalked on web and how according to a number of criteria."
				+ "\n"
				+ "The questionnaire will ask respondents if they were harassed or threatened on a social networking site such as Facebook, Twitter and LinkedIn, email service or Instant Messaging."
				+ "\n"
				+ "\"At the moment there are very few widely agreed guidelines or rules about how to behave online - we hope Echo will define behaviours that are generally experienced as anti-social or likely to cause distress in online communication.\" said Dr. Emma Short, head of the project ECHO."
				+ "\n"
				+ "The survey has been launched after Crown Prosecution Service (CPS) of the UK revealed a set of new guidelines for law enforcers tough on stalkers on web."
				+ "\n"
				+ "Read more: http://www.itproportal.com/security/news/article/2010/9/25/study-reveal-nature-cyberstalking-uk/#ixzz10YckSmCr";

	}

	public void testRun() {

        UClassifierFactory uClassifierFactory = new UClassifierFactory();
        // test Context
        classifier = uClassifierFactory.getClassifier(ClassifiersConfig.CONTEXT_SERVICE);
		System.out.println("Context is " + classifier.classify(message));

		// test Gender
        classifier = uClassifierFactory.getClassifier(ClassifiersConfig.GENDER_SERVICE);
		System.out.println("Gender is " + classifier.classify(message));

		// test Language
        classifier = uClassifierFactory.getClassifier(ClassifiersConfig.LANGUAGE_SERVICE);
		System.out.println("Language is " + classifier.classify(message));

		// test Spam
        classifier = uClassifierFactory.getClassifier(ClassifiersConfig.SPAM_SERVICE);
		System.out.println("Legitimate " + classifier.classify(message));

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new UClassifyClassifierTest().testRun();
	}

}
