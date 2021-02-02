package config;

import org.aeonbits.owner.Config;
import utils.Platform;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:local.properties",
        "classpath:remote.properties"
})
public interface MobileConfig extends Config {
    @Key("browserstack.username")
    String username();

    @Key("browserstack.password")
    String password();

    @Key("browserstack.platform")
    Platform platform();

    @Key("browserstack.url")
    String browserstackURL();

    @Key("browserstack.app")
    String app();
}
