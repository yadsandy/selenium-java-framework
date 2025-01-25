package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static utils.PropertyReader.getConfigValue;

public class Constants {
    public static final String CHROME = "CHROME";
    public static final String EDGE = "EDGE";
    public static final String FIREFOX = "FIREFOX";
    public static final boolean HEADLESS = Boolean.parseBoolean(getConfigValue("headless"));
    public static final String WAIT = getConfigValue("wait");
    public static final String URL = getConfigValue("url");
    public static final int WAIT_IMPLICIT = Integer.parseInt(getConfigValue("WAIT_IMPLICIT"));
    public static final int WAIT_EXPLICIT = Integer.parseInt(getConfigValue("WAIT_EXPLICIT"));
    private final static Logger logger = LogManager.getLogger();
    public static final String BROWSER = getBrowserName();

    private static String getBrowserName() {
        String platformNameFromPomXml = System.getProperty("platform");
        String platformName;
        if (platformNameFromPomXml != null)
            platformName = platformNameFromPomXml;
        else {
            logger.warn("The Maven Profile is missing the platform configuration.");
            logger.warn("The default platform '{}' will be enabled for this run.", CHROME);
            platformName = CHROME;
        }

        return platformName.toLowerCase();
    }
}
