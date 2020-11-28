package com.example.sportstats.service;

import com.example.sportstats.domain.League;
import org.springframework.http.HttpStatus;

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
            throw new SportstatsServiceException("League name can't be empty", HttpStatus.BAD_REQUEST);
        } else if (country != null && (country.isEmpty() || country.isBlank() || country.chars().anyMatch(Character::isDigit))) {
            throw new SportstatsServiceException("Country cant be empty or contain digits", HttpStatus.BAD_REQUEST);
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
            throw new SportstatsServiceException("There are no league with id: " + id, HttpStatus.NOT_FOUND);
        }
        if (name != null) {
            league.setName(name);
        }
        if (sportId != null) {
            if (getBrokerFactory().getSportBroker().findById(sportId) == null) {
                throw new SportstatsServiceException("There are no sport with id: " + sportId, HttpStatus.NOT_FOUND);
            } else {
                league.setSportId(sportId);
            }
        }
        if (country != null) {
            league.setCountry(country);
        }
        league.save();

        return league;
    }
}
