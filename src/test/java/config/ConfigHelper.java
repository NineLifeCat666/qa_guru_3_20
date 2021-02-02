package config;

import org.aeonbits.owner.ConfigFactory;
import utils.Platform;

public class ConfigHelper {

    public static String getUsername() {
        return getConfig().username();
    }

    public static String getPassword() {
        return getConfig().password();
    }

    public static String getApp() {
        return getConfig().app();
    }
    public static Platform getPlatform() {
        return getConfig().platform();
    }

    public static String getBrowserstackURL() {
        return getConfig().browserstackURL();
    }

    private static MobileConfig getConfig() {
        return ConfigFactory.newInstance().create(MobileConfig.class, System.getProperties());
    }
}
