package com.example.netcracker_lab.utility;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;

@Getter
@Setter
public class Properties {
    private static final String propertiesLocation = "src/main/resources/properties.json";
    private static Properties instance;

    static {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            instance = objectMapper.readValue(new File(propertiesLocation), Properties.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DAOProperties genreDAOProperties;
    private DAOProperties trackDAOProperties;

    private Properties(DAOProperties genreDAOProperties, DAOProperties trackDAOProperties) {
        this.genreDAOProperties = genreDAOProperties;
        this.trackDAOProperties = trackDAOProperties;
    }

    @JsonCreator
    public static Properties initWithProperties(@JsonProperty("genreDAOProperties") DAOProperties genreDAOProperties, @JsonProperty("trackDAOProperties") DAOProperties trackDAOProperties) {
        return new Properties(genreDAOProperties, trackDAOProperties);
    }

    public static Properties getInstance() {
        return instance;
    }
}
