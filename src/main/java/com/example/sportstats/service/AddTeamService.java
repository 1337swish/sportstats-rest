package com.example.sportstats.service;

import com.example.sportstats.domain.Team;

/**
 * Service that adds a new team.
 *
 * @author Claes Andersson
 */
public class AddTeamService extends BaseService<Team> {

    private final String name;
    private final Integer sportId;
    private final Integer arenaId;
    private final Integer createdYear;

    public AddTeamService(String name, Integer sportId, Integer arenaId, Integer createdYear) throws SportstatsServiceException {

        if (name.isEmpty() || name.isBlank()) {
            throw new SportstatsServiceException("Team name can't be empty");
        }
        this.name = name;
        this.sportId = sportId;
        this.arenaId = arenaId;
        this.createdYear = createdYear;
    }

    @Override
    public Team execute() {

        if (getBrokerFactory().getSportBroker().findById(sportId) == null) {
            throw new SportstatsServiceException("There are no sport with id: " + sportId);
        } else if (getBrokerFactory().getArenaBroker().findById(arenaId) == null) {
            throw new SportstatsServiceException("There are no arena with id: " + arenaId);
        }
        Team team = getBrokerFactory().getTeamBroker().create();
        team.setName(name);
        team.setSportId(sportId);
        team.setArenaId(arenaId);
        team.setCreatedYear(createdYear);
        team.save();

        return team;
    }
}
