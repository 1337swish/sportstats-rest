package com.example.sportstats.service;

import com.example.sportstats.domain.Arena;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;

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

        if (arenas.isEmpty()) {
            throw new SportstatsServiceException("There are no arenas", HttpStatus.NOT_FOUND);
        }
        return arenas;
    }
}
