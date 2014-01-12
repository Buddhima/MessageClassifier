/**
 * 
 */
package models.com.mc.configs;

/**
 * Store & Retrieve configurations relevant to creating Classifying Actors
 *
 */
public final class ClassifiersConfig {

	/** Important this should match with number of counters activated **/
	public static int CLASSIFIER_COUNT = 4;

	public static boolean DEPLOY_CONTEXT_CLASSIFIER = true;
	public static boolean DEPLOY_GENDER_CLASSIFIER = true;
	public static boolean DEPLOY_LANGUAGE_CLASSIFIER = true;
	public static boolean DEPLOY_SPAM_CLASSIFIER = true;

    public static String CONTEXT_SERVICE = "context";
    public static String GENDER_SERVICE = "gender";
    public static String LANGUAGE_SERVICE = "language";
    public static String SPAM_SERVICE = "spam";

	/** Waiting timeout for classifiers in seconds **/
	public static int CLASSIFIER_SERVICE_TIMEOUT = 5;
	
	
	/**
	 * 
	 */
	public ClassifiersConfig() {
		// TODO Auto-generated constructor stub
	}

    public static int getCLASSIFIER_COUNT() {
        return CLASSIFIER_COUNT;
    }

    public static void setCLASSIFIER_COUNT(int CLASSIFIER_COUNT) {
        ClassifiersConfig.CLASSIFIER_COUNT = CLASSIFIER_COUNT;
    }

    public static boolean isDEPLOY_CONTEXT_CLASSIFIER() {
        return DEPLOY_CONTEXT_CLASSIFIER;
    }

    public static void setDEPLOY_CONTEXT_CLASSIFIER(boolean DEPLOY_CONTEXT_CLASSIFIER) {
        ClassifiersConfig.DEPLOY_CONTEXT_CLASSIFIER = DEPLOY_CONTEXT_CLASSIFIER;
    }

    public static boolean isDEPLOY_GENDER_CLASSIFIER() {
        return DEPLOY_GENDER_CLASSIFIER;
    }

    public static void setDEPLOY_GENDER_CLASSIFIER(boolean DEPLOY_GENDER_CLASSIFIER) {
        ClassifiersConfig.DEPLOY_GENDER_CLASSIFIER = DEPLOY_GENDER_CLASSIFIER;
    }

    public static boolean isDEPLOY_LANGUAGE_CLASSIFIER() {
        return DEPLOY_LANGUAGE_CLASSIFIER;
    }

    public static void setDEPLOY_LANGUAGE_CLASSIFIER(boolean DEPLOY_LANGUAGE_CLASSIFIER) {
        ClassifiersConfig.DEPLOY_LANGUAGE_CLASSIFIER = DEPLOY_LANGUAGE_CLASSIFIER;
    }

    public static boolean isDEPLOY_SPAM_CLASSIFIER() {
        return DEPLOY_SPAM_CLASSIFIER;
    }

    public static void setDEPLOY_SPAM_CLASSIFIER(boolean DEPLOY_SPAM_CLASSIFIER) {
        ClassifiersConfig.DEPLOY_SPAM_CLASSIFIER = DEPLOY_SPAM_CLASSIFIER;
    }

    public static String getLANGUAGE_SERVICE() {
        return LANGUAGE_SERVICE;
    }

    public static void setLANGUAGE_SERVICE(String LANGUAGE_SERVICE) {
        ClassifiersConfig.LANGUAGE_SERVICE = LANGUAGE_SERVICE;
    }

    public static String getSPAM_SERVICE() {
        return SPAM_SERVICE;
    }

    public static void setSPAM_SERVICE(String SPAM_SERVICE) {
        ClassifiersConfig.SPAM_SERVICE = SPAM_SERVICE;
    }

    public static int getCLASSIFIER_SERVICE_TIMEOUT() {
        return CLASSIFIER_SERVICE_TIMEOUT;
    }

    public static void setCLASSIFIER_SERVICE_TIMEOUT(int CLASSIFIER_SERVICE_TIMEOUT) {
        ClassifiersConfig.CLASSIFIER_SERVICE_TIMEOUT = CLASSIFIER_SERVICE_TIMEOUT;
    }

    public static String getGENDER_SERVICE() {
        return GENDER_SERVICE;
    }

    public static void setGENDER_SERVICE(String GENDER_SERVICE) {
        ClassifiersConfig.GENDER_SERVICE = GENDER_SERVICE;
    }

    public static String getCONTEXT_SERVICE() {
        return CONTEXT_SERVICE;
    }

    public static void setCONTEXT_SERVICE(String CONTEXT_SERVICE) {
        ClassifiersConfig.CONTEXT_SERVICE = CONTEXT_SERVICE;
    }
}
