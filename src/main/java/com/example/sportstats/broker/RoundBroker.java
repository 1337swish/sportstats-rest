package com.example.sportstats.broker;

import com.example.sportstats.domain.Round;
import com.example.sportstats.record.RoundRecord;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Broker class for Round.
 *
 * @author Claes Andersson
 */
public class RoundBroker {

    /**
     * Find all rounds.
     *
     * @return list containing all rounds.
     */
    public List<Round> findAll() {

        List<RoundRecord> roundRecords = RoundRecord.findAll().orderBy("number asc");
        return roundRecords.stream()
                .map(record -> new Round(record))
                .collect(Collectors.toList());
    }

    /**
     * Find the round with given id.
     *
     * @param id id of the round.
     * @return a round.
     */
    public Round findById(Integer id) {

        if (RoundRecord.exists(id)) {
            return new Round(RoundRecord.findById(id));
        } else {
            return null;
        }
    }

    /**
     * Find all rounds belonging to a season.
     *
     * @param seasonId id of the season.
     * @return list containing the rounds.
     */
    public List<Round> findBySeasonId(Integer seasonId) {

        List<RoundRecord> roundRecords = RoundRecord.find("season_id = ?", seasonId).orderBy("number asc");
        return roundRecords.stream()
                .map(record -> new Round(record))
                .collect(Collectors.toList());
    }

    /**
     * Creates a new round.
     *
     * @return the new round.
     */
    public Round create() {

        return new Round();
    }
}
