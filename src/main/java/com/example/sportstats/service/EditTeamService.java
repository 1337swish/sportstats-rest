package com.example.sportstats.service;

import com.example.sportstats.domain.Team;

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
            throw new SportstatsServiceException("Team name can't be empty");
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
            throw new SportstatsServiceException("There are no team with id: " + id);
        }
        if (name != null) {
            team.setName(name);
        }
        if (arenaId != null && getBrokerFactory().getArenaBroker().findById(arenaId) != null) {
            team.setArenaId(arenaId);
        }
        if (sportId != null && getBrokerFactory().getSportBroker().findById(sportId) != null) {
            team.setSportId(sportId);
        }
        if (createdYear != null) {
            team.setCreatedYear(createdYear);
        }
        team.save();

        return team;
    }
}
