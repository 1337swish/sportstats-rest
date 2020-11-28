package com.example.sportstats.service;

import com.example.sportstats.domain.Team;
import org.springframework.http.HttpStatus;

/**
 * Service that edits an existing team.
 *
 * @author Claes Andersson
 */
public class EditTeamService extends BaseService<Team> {

    private final Integer id;
    private final String name;
    private final Integer arenaId;
    private final Integer sportId;
    private final Integer createdYear;

    public EditTeamService(Integer id, String name, Integer arenaId, Integer sportId, Integer createdYear) {

        if (name != null && (name.isEmpty() || name.isBlank())) {
            throw new SportstatsServiceException("Team name can't be empty", HttpStatus.BAD_REQUEST);
        }
        this.id = id;
        this.name = name;
        this.sportId = sportId;
        this.arenaId = arenaId;
        this.createdYear = createdYear;
    }

    @Override
    public Team execute() {

        Team team = getBrokerFactory().getTeamBroker().findById(id);
        if (team == null) {
            throw new SportstatsServiceException("There are no team with id: " + id, HttpStatus.NOT_FOUND);
        }
        if (name != null) {
            team.setName(name);
        }
        if (arenaId != null) {
            if (getBrokerFactory().getArenaBroker().findById(arenaId) == null) {
                throw new SportstatsServiceException("There are no arena with id: " + arenaId, HttpStatus.NOT_FOUND);
            } else {
                team.setArenaId(arenaId);
            }
        }
        if (sportId != null && getBrokerFactory().getSportBroker().findById(sportId) != null) {
            if (getBrokerFactory().getSportBroker().findById(sportId) == null) {
                throw new SportstatsServiceException("There are no sport with id: " + sportId, HttpStatus.NOT_FOUND);
            } else {
                team.setSportId(sportId);
            }
        }
        if (createdYear != null) {
            team.setCreatedYear(createdYear);
        }
        team.save();

        return team;
    }
}
