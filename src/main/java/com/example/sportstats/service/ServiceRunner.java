package com.example.sportstats.service;

import com.example.sportstats.broker.BrokerFactory;
import com.example.sportstats.db.DbConn;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * This class runs a service and converts the result to json.
 *
 * @author Claes Andersson
 * @param <T> a service class extending BaseService.
 */
public class ServiceRunner<T> {

    private final SportstatsService<T> service;

    public ServiceRunner(SportstatsService<T> service) {

        this.service = service;
    }

    public String execute() {

        DbConn dbConn = DbConn.getInstance();
        service.init(new BrokerFactory());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        T result = null;
        String jsonResult = null;
        try {
            dbConn.open();
            result = service.execute();
            jsonResult = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
        } catch (JsonProcessingException e) {
            return e.getMessage();
        } finally {
            dbConn.close();
        }
        return jsonResult;
    }
}
