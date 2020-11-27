package com.example.sportstats.broker;

import com.example.sportstats.domain.Arena;
import com.example.sportstats.record.ArenaRecord;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Broker class for Arena.
 *
 * @author Claes Andersson
 */
public class ArenaBroker {

    /**
     * Find all arenas.
     *
     * @return list containing all arenas.
     */
    public List<Arena> findAll() {

        List<ArenaRecord> arenaRecords = ArenaRecord.findAll().orderBy("capacity desc");
        return arenaRecords.stream()
                .map(record -> new Arena(record))
                .collect(Collectors.toList());
    }

    /**
     * Find the arena with given id.
     *
     * @param id the id of the arena.
     * @return an arena.
     */
    public Arena findById(Integer id) {

        if (ArenaRecord.exists(id)) {
            return new Arena(ArenaRecord.findById(id));
        } else {
            return null;
        }
    }

    /**
     * Checks if the arena with given name already exists.
     *
     * @param name the arenas name.
     * @return true if it exists, false otherwise.
     */
    public boolean exists(String name) {

        List<ArenaRecord> arenaRecords = ArenaRecord.where("name = ?", name);
        return !arenaRecords.isEmpty();
    }

    /**
     * Creates a new arena.
     *
     * @return the new arena.
     */
    public Arena create() {

        return new Arena();
    }
}
