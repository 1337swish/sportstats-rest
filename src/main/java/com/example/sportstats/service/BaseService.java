package com.example.sportstats.service;

import com.example.sportstats.broker.BrokerFactory;

/**
 * Base class for all services.
 *
 * @author Claes Andersson
 */
public abstract class BaseService<T> implements SportstatsService<T> {

    private BrokerFactory brokerFactory;

    /**
     * Get the broker factory.
     *
     * @return a broker factory.
     */
    protected BrokerFactory getBrokerFactory() {

        return brokerFactory;
    }

    /**
     * Initiates the service with a broker factory.
     *
     * @param brokerFactory a brokerfactory.
     */
    @Override
    public final void init(BrokerFactory brokerFactory) {

        this.brokerFactory = brokerFactory;
    }

    /**
     * Execute method that runs the code in a service.
     *
     * @return generic type T.
     */
    @Override
    public abstract T execute();
}
