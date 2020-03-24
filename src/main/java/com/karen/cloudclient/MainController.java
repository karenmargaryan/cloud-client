package com.karen.cloudclient;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RefreshScope
public class MainController {

    @Value("${config.date: No Date}")
    private String date;

    @Value("${spring.profiles.active:}")
    private String activeProfiles;
    @Autowired
    private Environment env;

    private Properties properties;

    @Autowired
    public void setProperties(Properties properties) {
        System.out.println("ACTIVE PROFILE IS " + activeProfiles);
        System.out.println("ENV.x = " + env.getProperty("x"));
        System.out.println("ENV.server.port = " + env.getProperty("server.port"));
        System.out.println("ENV.spring.cloud.config.uri = " + env.getProperty("spring.cloud.config.uri"));
        System.out.println("ENV.spring.cloud.config.name = " + env.getProperty("spring.cloud.config.name"));
        System.out.println("ENV.spring.cloud.config.profile = " + env.getProperty("spring.cloud.config.profile"));
        System.out.println("ENV.spring.profiles.active = " + env.getProperty("spring.profiles.active"));

        System.out.println("Initializing from configuration file");
        this.properties = properties;
        System.out.println(getLoadedData());
    }

    @GetMapping("/info")
    public String getChannleInfo() {
        return getLoadedData();
    }

    private String getLoadedData() {
        JsonObject jsonpObject = new JsonObject();
        jsonpObject.addProperty("config.type", properties.getType());
        jsonpObject.addProperty("config.name", properties.getName());
        jsonpObject.addProperty("config.author", properties.getAuthor());
        jsonpObject.addProperty("config.date", date);
        return jsonpObject.toString();
    }
}
