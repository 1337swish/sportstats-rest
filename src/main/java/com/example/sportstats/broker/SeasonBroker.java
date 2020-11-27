package com.example.sportstats.broker;

import com.example.sportstats.domain.Season;
import com.example.sportstats.record.SeasonRecord;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Broker class for Season.
 *
 * @author Claes Andersson
 */
public class SeasonBroker {

    /**
     * Find all seasons.
     *
     * @return list containing all seasons.
     */
    public List<Season> findAll() {

        List<SeasonRecord> seasonRecords = SeasonRecord.findAll().orderBy("start_year desc");
        return seasonRecords.stream()
                .map(record -> new Season(record))
                .collect(Collectors.toList());
    }

    /**
     * Find the season with given id.
     *
     * @param id id of the season.
     * @return a season.
     */
    public Season findById(Integer id) {

        if (SeasonRecord.exists(id)) {
            return new Season(SeasonRecord.findById(id));
        } else {
            return null;
        }
    }

    /**
     * Find all seasons that belongs to a league.
     *
     * @param leagueId id of the league.
     * @return list containing the seasons.
     */
    public List<Season> findByLeagueId(Integer leagueId) {

        List<SeasonRecord> seasonRecords = SeasonRecord.where("league_id = ?", leagueId).orderBy("start_year desc");
        return seasonRecords.stream()
                .map(record -> new Season(record))
                .collect(Collectors.toList());
    }

    /**
     * Creates a new season.
     *
     * @return the new season.
     */
    public Season create() {

        return new Season();
    }
}
