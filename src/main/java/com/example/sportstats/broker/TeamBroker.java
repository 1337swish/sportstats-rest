package com.example.sportstats.broker;

import com.example.sportstats.domain.Team;
import com.example.sportstats.record.ArenaRecord;
import com.example.sportstats.record.LeagueRecord;
import com.example.sportstats.record.SeasonRecord;
import com.example.sportstats.record.TeamRecord;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Broker class for Team.
 *
 * @author Claes Andersson
 */
public class TeamBroker {

    /**
     * Find all teams.
     *
     * @return list containing all teams.
     */
    public List<Team> findAll() {

        List<TeamRecord> teamRecords = TeamRecord.findAll().orderBy("name asc");
        return teamRecords.stream()
                .map(record -> new Team(record))
                .collect(Collectors.toList());
    }

    /**
     * Find all teams that belongs to a sport.
     *
     * @param sportId id of the sport.
     * @return list containing the teams.
     */
    public List<Team> findBySportId(Integer sportId) {

        List<TeamRecord> teamRecords = TeamRecord.where("sport_id = ?", sportId).orderBy("name asc");
        List<Team> result = teamRecords.stream()
                .map(record -> new Team(record))
                .collect(Collectors.toList());

        return result;
    }

    /**
     * Find all teams that belongs to a league.
     *
     * @param leagueId id of the league.
     * @return list containing the teams.
     */
    public List<Team> findByLeagueId(Integer leagueId) {

        LeagueRecord leagueRecord = LeagueRecord.findById(leagueId);
        List<TeamRecord> teamRecords = leagueRecord.getAll(LeagueRecord.class).orderBy("name asc");
        List<Team> result = teamRecords.stream()
                .map(record -> new Team(record))
                .collect(Collectors.toList());

        return result;
    }

    /**
     * Find all teams that belongs to an arena.
     *
     * @param arenaId id of the arena.
     * @return list containing the teams.
     */
    public List<Team> findByArenaId(Integer arenaId) {

        ArenaRecord arenaRecord = ArenaRecord.findById(arenaId);
        List<TeamRecord> teamRecords = arenaRecord.getAll(ArenaRecord.class).orderBy("name asc");
        List<Team> result = teamRecords.stream()
                .map(record -> new Team(record))
                .collect(Collectors.toList());

        return result;
    }

    /**
     * Find all teams belonging to a season.
     *
     * @param seasonId id of the season.
     * @return list containing the teams.
     */
    public List<Team> findBySeasonId(Integer seasonId) {

        if (SeasonRecord.exists(seasonId)) {
            SeasonRecord seasonRecord = SeasonRecord.findById(seasonId);
            List<TeamRecord> teamRecords = seasonRecord.getAll(TeamRecord.class).orderBy("name asc");
            return teamRecords.stream()
                    .map(record -> new Team(record))
                    .collect(Collectors.toList());
        } else {
            return null;
        }

    }

    /**
     * Find the team with given id.
     *
     * @param id id of the team.
     * @return a team.
     */
    public Team findById(Integer id) {

        if (TeamRecord.exists(id)) {
            return new Team(TeamRecord.findById(id));
        } else {
            return null;
        }
    }

    /**
     * Checks if the team with given name already exists.
     *
     * @param name the teams name.
     * @return true if it exists, false otherwise.
     */
    public boolean exists(String name) {

        List<TeamRecord> teamRecords = TeamRecord.where("name = ?", name);
        return !teamRecords.isEmpty();
    }

    /**
     * Creates a new team.
     *
     * @return the new team.
     */
    public Team create() {

        return new Team();
    }
}
