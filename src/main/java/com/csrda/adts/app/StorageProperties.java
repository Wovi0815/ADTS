package com.csrda.adts.app;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zsh
 * @company wlgzs
 * @create 2018-12-15 16:20
 * @Describe
 */
@ConfigurationProperties("storage")
public class StorageProperties {
    private String location = "src/main/resources/upload-files";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
