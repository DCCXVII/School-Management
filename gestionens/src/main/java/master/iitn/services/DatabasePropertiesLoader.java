package master.iitn.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabasePropertiesLoader {
    public static Properties load() {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/master/config/database.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}