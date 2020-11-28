package com.example.sportstats.service;

import com.example.sportstats.domain.Arena;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;

/**
 * Service that deletes arenas.
 *
 * @author Claes Andersson
 */
public class DeleteArenaService extends BaseService<Map> {

    private final Integer id;

    public DeleteArenaService(Integer id) {

        this.id = id;
    }

    @Override
    public Map execute() {

        Arena arena = getBrokerFactory().getArenaBroker().findById(id);
        if (arena == null) {
            throw new SportstatsServiceException("There are no arena with id: " + id, HttpStatus.NOT_FOUND);
        }
        Map deletedArena = new HashMap();
        deletedArena.put("id", arena.getId());
        deletedArena.put("name", arena.getName());
        deletedArena.put("address", arena.getAddress());
        deletedArena.put("capacity", arena.getCapacity());
        arena.delete();

        return deletedArena;
    }
}
