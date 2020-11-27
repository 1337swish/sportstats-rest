package com.example.sportstats.broker;

import com.example.sportstats.domain.Match;
import com.example.sportstats.record.MatchRecord;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Broker class for Match.
 *
 * @author Claes Andersson
 */
public class MatchBroker {

    /**
     * Find all matches.
     *
     * @return list containing all matches.
     */
    public List<Match> findAll() {

        List<MatchRecord> matchRecords = MatchRecord.findAll().orderBy("date desc");
        return matchRecords.stream()
                .map(record -> new Match(record))
                .collect(Collectors.toList());
    }

    /**
     * Find the match with given id.
     *
     * @param id id of the match.
     * @return a match.
     */
    public Match findById(Integer id) {

        if (MatchRecord.exists(id)) {
            return new Match(MatchRecord.findById(id));
        } else {
            return null;
        }
    }

    /**
     * Find all matches belonging to a round.
     *
     * @param roundId id of the round.
     * @return list containing the matches.
     */
    public List<Match> findByRoundId(Integer roundId) {

        List<MatchRecord> matchRecords = MatchRecord.where("round_id = ?", roundId).orderBy("date desc");
        return matchRecords.stream()
                .map(record -> new Match(record))
                .collect(Collectors.toList());
    }

    /**
     * Find all matches belonging to a home team.
     *
     * @param homeTeamId id of the home team.
     * @return list containing the matches.
     */
    public List<Match> findByHomeTeamId(Integer homeTeamId) {

        List<MatchRecord> matchRecords = MatchRecord.where("home_team_id = ?", homeTeamId).orderBy("date desc");
        return matchRecords.stream()
                .map(record -> new Match(record))
                .collect(Collectors.toList());
    }

    /**
     * Find all matches belonging to an away team.
     *
     * @param awayTeamId id of the away team.
     * @return list containing the matches.
     */
    public List<Match> findByAwayTeamId(Integer awayTeamId) {

        List<MatchRecord> matchRecords = MatchRecord.where("away_team_id = ?", awayTeamId).orderBy("date desc");
        return matchRecords.stream()
                .map(record -> new Match(record))
                .collect(Collectors.toList());
    }

    /**
     * Find all matches belonging to a team.
     *
     * @param teamId id of the team.
     * @return list containing the matches.
     */
    public List<Match> findByTeamId(Integer teamId) {

        List<MatchRecord> matchRecords = MatchRecord.where("home_team_id = ? OR away_team_id = ?", teamId, teamId).orderBy("date desc");
        return matchRecords.stream()
                .map(record -> new Match(record))
                .collect(Collectors.toList());
    }

    /**
     * Find all matches belonging to a season.
     *
     * @param seasonId id of the season.
     * @return list containing the matches.
     */
    public List<Match> findBySeasonId(Integer seasonId) {

        List<MatchRecord> matchRecords = MatchRecord.where("season_id = ?", seasonId).orderBy("date desc");
        return matchRecords.stream()
                .map(record -> new Match(record))
                .collect(Collectors.toList());
    }

    /**
     * Creates a new match.
     *
     * @return the new match.
     */
    public Match create() {

        return new Match();
    }
}
