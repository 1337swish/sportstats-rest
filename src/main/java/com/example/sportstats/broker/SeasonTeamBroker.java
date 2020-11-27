package com.example.sportstats.broker;

import com.example.sportstats.domain.SeasonTeam;
import com.example.sportstats.record.SeasonTeamRecord;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Broker class for SeasonTeam.
 *
 * @author Claes Andersson
 */
public class SeasonTeamBroker {

    /**
     * Find all seasonTeams.
     *
     * @return list containing all seasonTeams.
     */
    public List<SeasonTeam> findAll() {

        List<SeasonTeamRecord> seasonTeamRecords = SeasonTeamRecord.findAll();
        return seasonTeamRecords.stream()
                .map(record -> new SeasonTeam(record))
                .collect(Collectors.toList());
    }
    
    /**
     * Find the seasonTeam with given id.
     *
     * @param id id of the seasonTeam.
     * @return a seasonTeam.
     */
    public SeasonTeam findById(Integer id) {

        if (SeasonTeamRecord.exists(id)) {
            return new SeasonTeam(SeasonTeamRecord.findById(id));
        } else {
            return null;
        }
    }

    /**
     * Find all teams belonging to a season.
     *
     * @param seasonId id of the season.
     * @return list containing the teams and seasons.
     */
    public List<SeasonTeam> findBySeasonId(Integer seasonId) {

        List<SeasonTeamRecord> seasonTeamRecords = SeasonTeamRecord.where("season_id = ?", seasonId);
        return seasonTeamRecords.stream()
                .map(record -> new SeasonTeam(record))
                .collect(Collectors.toList());
    }

    /**
     * Creates a new seasonteam.
     *
     * @return the new seasonteam.
     */
    public SeasonTeam create() {

        return new SeasonTeam();
    }
}
