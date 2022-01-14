package com.example.simple_mvc_project.utility;

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
    private ConnectorProperties connectorProperties;

    private Properties(DAOProperties genreDAOProperties,
                       DAOProperties trackDAOProperties,
                       ConnectorProperties connectorProperties) {
        this.genreDAOProperties = genreDAOProperties;
        this.trackDAOProperties = trackDAOProperties;
        this.connectorProperties = connectorProperties;
    }

    @JsonCreator
    public static Properties initWithProperties(@JsonProperty("genreDAOProperties") DAOProperties genreDAOProperties,
                                                @JsonProperty("trackDAOProperties") DAOProperties trackDAOProperties,
                                                @JsonProperty("connectorProperties") ConnectorProperties connectorProperties) {
        return new Properties(genreDAOProperties, trackDAOProperties, connectorProperties);
    }

    public static Properties getInstance() {
        return instance;
    }
}
