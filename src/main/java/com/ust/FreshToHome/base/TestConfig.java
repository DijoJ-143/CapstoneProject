package com.ust.FreshToHome.base;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:develop.properties",
        "system:properties",
        "system:env"
})
public interface TestConfig extends Config {

    @Key("BASE_URL")
    @DefaultValue("")
    String baseUrl();

    @Key("USE_HEADLESS_BROWSER")
    @DefaultValue("false")
    boolean useHeadlessBrowser();

    @Key("KEEP_BROWSER_OPEN")
    @DefaultValue("false")
    boolean keepBrowserOpen();

    @Key("CHROME_DRIVER_PATH")
    @DefaultValue("")
    String chromeDriverPath();

}
