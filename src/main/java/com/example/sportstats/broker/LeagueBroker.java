package com.example.sportstats.broker;

import com.example.sportstats.domain.League;
import com.example.sportstats.record.LeagueRecord;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Broker class for League.
 *
 * @author Claes Andersson
 */
public class LeagueBroker {

    /**
     * Find all leagues.
     *
     * @return list containing all leagues.
     */
    public List<League> findAll() {

        List<LeagueRecord> leagueRecords = LeagueRecord.findAll().orderBy("name asc");
        return leagueRecords.stream()
                .map(record -> new League(record))
                .collect(Collectors.toList());
    }

    /**
     * Find the league with given id.
     *
     * @param id id of the league.
     * @return a league.
     */
    public League findById(Integer id) {

        if (LeagueRecord.exists(id)) {
            return new League(LeagueRecord.findById(id));
        } else {
            return null;
        }
    }

    /**
     * Find all leagues belonging to a sport.
     *
     * @param sportId id of the sport.
     * @return list containing the leagues.
     */
    public List<League> findBySportId(Integer sportId) {

        List<LeagueRecord> leagueRecords = LeagueRecord.where("sport_id = ?", sportId).orderBy("name asc");
        return leagueRecords.stream()
                .map(record -> new League(record))
                .collect(Collectors.toList());
    }

    /**
     * Checks if the league with given name already exists.
     *
     * @param name the league name.
     * @return true if it exists, false otherwise.
     */
    public boolean exists(String name) {

        List<LeagueRecord> leagueRecords = LeagueRecord.where("name = ?", name);
        return !leagueRecords.isEmpty();
    }

    /**
     * Creates a new league.
     *
     * @return the new league.
     */
    public League create() {

        return new League();
    }

}
