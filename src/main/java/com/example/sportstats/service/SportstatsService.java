package com.example.sportstats.service;

import com.example.sportstats.broker.BrokerFactory;

/**
 * Interface for all services.
 *
 * @author Claes Andersson
 */
public interface SportstatsService<T> {

    /**
     * Initiates this service with a broker factory.
     *
     * @param brokerFactory a broker factory.
     */
    void init(BrokerFactory brokerFactory);

    /**
     * Executes the code for this service.
     *
     * @return generic type T.
     */
    T execute();
}
