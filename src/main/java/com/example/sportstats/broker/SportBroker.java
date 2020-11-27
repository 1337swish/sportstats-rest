package com.example.sportstats.broker;

import com.example.sportstats.domain.Sport;
import com.example.sportstats.record.SportRecord;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Broker class for Sport.
 *
 * @author Claes Andersson
 */
public class SportBroker {

    /**
     * Find all sports.
     *
     * @return list containing all sports.
     */
    public List<Sport> findAll() {

        List<SportRecord> sportRecords = SportRecord.findAll().orderBy("name asc");
        return sportRecords.stream()
                .map(record -> new Sport(record))
                .collect(Collectors.toList());
    }
    
    /**
     * Find the sport with given id.
     *
     * @param id id of the sport.
     * @return a sport.
     */
    public Sport findById(Integer id) {

        if (SportRecord.exists(id)) {
            return new Sport(SportRecord.findById(id));
        } else {
            return null;
        }
    }

    /**
     * Checks if the sport with given name already exists.
     *
     * @param name the sports name.
     * @return true if it exists, false otherwise.
     */
    public boolean exists(String name) {

        List<SportRecord> sportRecords = SportRecord.where("name = ?", name);
        return !sportRecords.isEmpty();
    }

    /**
     * Creates a new sport.
     *
     * @return the new sport.
     */
    public Sport create() {

        return new Sport();
    }
}
