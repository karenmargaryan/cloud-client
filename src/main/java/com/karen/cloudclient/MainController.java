package com.karen.cloudclient;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RefreshScope
public class MainController {

    @Value("${config.date: No Date}")
    private String date;

    @Autowired
    private Properties properties;

    @GetMapping("/info")
    public String getChannleInfo(){
        JsonObject jsonpObject = new JsonObject();
        jsonpObject.addProperty("config.type", properties.getType());
        jsonpObject.addProperty("config.name", properties.getName());
        jsonpObject.addProperty("config.author", properties.getAuthor());
        jsonpObject.addProperty("config.date", date);
        return jsonpObject.toString();
    }
}
