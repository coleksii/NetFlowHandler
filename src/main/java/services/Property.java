package services;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Property {
    private static Properties prop = new Properties();

    static {
        try {
            InputStream input = new FileInputStream("src/main/resources/config.properties");
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String property){
        return prop.getProperty(property);
    }
    public static int getPropertyInteger(String property){
        return Integer.parseInt(prop.getProperty(property));
    }
}
