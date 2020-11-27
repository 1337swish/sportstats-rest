package com.example.sportstats.service;

import com.example.sportstats.domain.League;

/**
 * Service that edits an existing league.
 *
 * @author Claes Andersson
 */
public class EditLeagueService extends BaseService<League> {

    private final Integer id;
    private final String name;
    private final Integer sportId;
    private final String country;

    public EditLeagueService(Integer id, String name, Integer sportId, String country) {

        if (name != null && (name.isEmpty() || name.isBlank())) {
            throw new SportstatsServiceException("League name can't be empty");
        } else if (country != null && (country.isEmpty() || country.isBlank() || country.chars().anyMatch(Character::isDigit))) {
            throw new SportstatsServiceException("Country cant be empty or contain digits");
        }
        this.id = id;
        this.name = name;
        this.sportId = sportId;
        this.country = country;
    }

    @Override
    public League execute() {

        League league = getBrokerFactory().getLeagueBroker().findById(id);
        if (league == null) {
            throw new SportstatsServiceException("There are no league with id: " + id);
        }
        if (name != null) {
            league.setName(name);
        }
        if (sportId != null) {
            league.setSportId(sportId);
        }
        if (country != null) {
            league.setCountry(country);
        }
        league.save();

        return league;
    }
}
