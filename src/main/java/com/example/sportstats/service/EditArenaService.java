package com.example.sportstats.service;

import com.example.sportstats.domain.Arena;

/**
 * Service that edits an existing arena.
 *
 * @author Claes Andersson
 */
public class EditArenaService extends BaseService<Arena> {

    private final Integer id;
    private final String name;
    private final String address;
    private final Integer capacity;

    public EditArenaService(Integer id, String name, String address, Integer capacity) {

        if (name != null && (name.isEmpty() || name.isBlank())) {
            throw new SportstatsServiceException("Arena name can't be empty");
        } else if (address != null && (address.isEmpty() || address.isBlank())) {
            throw new SportstatsServiceException("Address cant be empty");
        } else if (capacity != null && capacity < 0) {
            throw new SportstatsServiceException("Arena capacity can't be less than 0");
        }
        this.id = id;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
    }

    @Override
    public Arena execute() {

        Arena arena = getBrokerFactory().getArenaBroker().findById(id);
        if (arena == null) {
            throw new SportstatsServiceException("There are no arena with id: " + id);
        }
        if (name != null) {
            arena.setName(name);
        }
        if (address != null) {
            arena.setAddress(address);
        }
        if (capacity != null) {
            arena.setCapacity(capacity);
        }
        arena.save();

        return arena;
    }
}
