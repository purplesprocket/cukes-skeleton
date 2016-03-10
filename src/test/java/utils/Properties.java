package utils;

import java.io.IOException;

public class Properties {

    public static String getAppKey(){
        return getValue(AppSettings.API_KEY_PROPERTY_NAME);
    }

    public static String getAPIUrlScheme(){
        return getValue(AppSettings.API_URL_SCHEME_PROPERTY_NAME);
    }

    public static String getAPIHost(){
        return getValue(AppSettings.API_URL_HOST_PROPERTY_NAME);
    }

    public static String getAPIUrlPath(){
        return getValue(AppSettings.API_URL_PATH_PROPERTY_NAME);
    }

    public static String getValue(String key){
        String keyFromSystemProperty = System.getProperty(key);
        if (keyFromSystemProperty != null && !keyFromSystemProperty.isEmpty()) return keyFromSystemProperty;

        String keyFromFileProperty = getValueFromPropertyFile(key);
        if (keyFromFileProperty != null && !keyFromFileProperty.isEmpty()) return keyFromFileProperty;

        throw new RuntimeException("cannot get property by key=" + key);
    }

    private static String getValueFromPropertyFile(String key){
        java.util.Properties prop = new java.util.Properties();
        try {
            //load a properties file from class path, inside static method
            prop.load(Properties.class.getClassLoader().getResourceAsStream(AppSettings.PROPERTY_FILE_NAME));

            return prop.getProperty(key);
        }
        catch (IOException ex) {
            ex.printStackTrace();

        }
        return null;
    }
}
