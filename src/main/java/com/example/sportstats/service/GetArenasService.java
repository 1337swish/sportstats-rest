package com.example.sportstats.service;

import com.example.sportstats.domain.Arena;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Claes Andersson
 */
public class GetArenasService extends BaseService<List<Arena>> {

    private final Integer id;

    public GetArenasService(Integer id) {

        this.id = id;
    }

    @Override
    public List<Arena> execute() {

        List<Arena> arenas = getBrokerFactory().getArenaBroker().findAll();

        if (id != null) {
            arenas = arenas.stream()
                    .filter(arena -> arena.getId().equals(id))
                    .collect(Collectors.toList());
        }

        return arenas;
    }
}
