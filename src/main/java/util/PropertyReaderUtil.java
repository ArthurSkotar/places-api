package util;

import sun.applet.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by arthur on 23.04.17.
 */
public final class PropertyReaderUtil {

    private final Properties properties = new Properties();

    private PropertyReaderUtil() {
        final InputStream inputStream = PropertyReaderUtil.class.getClassLoader().getResourceAsStream("app-resources.properties");
        try {
            this.properties.load(inputStream);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static PropertyReaderUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public String getGoogleApiKey() {
        return properties.getProperty("api-key");
    }


    private static class SingletonHolder {
        private static final PropertyReaderUtil INSTANCE = new PropertyReaderUtil();
    }
}

