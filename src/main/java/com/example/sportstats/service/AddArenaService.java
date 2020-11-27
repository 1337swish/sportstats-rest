package com.example.sportstats.service;

import com.example.sportstats.domain.Arena;

/**
 * Service that adds a new arena.
 *
 * @author Claes Andersson
 */
public class AddArenaService extends BaseService<Arena> {

    private final String name;
    private final String address;
    private final Integer capacity;

    public AddArenaService(String name, String address, Integer capacity) throws SportstatsServiceException {

        if (name.isEmpty() || name.isBlank()) {
            throw new SportstatsServiceException("Arena name can't be empty");
        } else if (address.isEmpty() || address.isBlank()) {
            throw new SportstatsServiceException("Address can't be empty");
        } else if (capacity < 0) {
            throw new SportstatsServiceException("Capacity can't be less than 0");
        }
        this.name = name;
        this.address = address;
        this.capacity = capacity;
    }

    @Override
    public Arena execute() {

        Arena arena = getBrokerFactory().getArenaBroker().create();
        arena.setName(name);
        arena.setAddress(address);
        arena.setCapacity(capacity);
        arena.save();

        return arena;
    }
}
