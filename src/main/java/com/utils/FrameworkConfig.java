package com.utils;

import org.aeonbits.owner.Config;

@Config.Sources(value = "file:${user.dir}/src/test/resources/config.properties")
public interface FrameworkConfig extends Config {
    String baseurl();
    @DefaultValue("yes")
    String overridereports();
}
