package com.example.sportstats.service;

import com.example.sportstats.domain.League;
import org.springframework.http.HttpStatus;

/**
 * Service that adds a new league.
 *
 * @author Claes Andersson
 */
public class AddLeagueService extends BaseService<League> {

    private final String leagueName;
    private final Integer sportId;
    private final String country;

    public AddLeagueService(String leagueName, Integer sportId, String country) {

        if (leagueName.isEmpty() || leagueName.isBlank()) {
            throw new SportstatsServiceException("League name can't be empty", HttpStatus.BAD_REQUEST);
        } else if (country.isEmpty() || country.isBlank() || country.chars().anyMatch(Character::isDigit)) {
            throw new SportstatsServiceException("Country cant be empty or contain digits", HttpStatus.BAD_REQUEST);
        }

        this.leagueName = leagueName;
        this.sportId = sportId;
        this.country = country;
    }

    @Override
    public League execute() {

        if (getBrokerFactory().getLeagueBroker().exists(leagueName)) {
            throw new SportstatsServiceException("League already exists", HttpStatus.BAD_REQUEST);
        } else if (getBrokerFactory().getSportBroker().findById(sportId) == null) {
            throw new SportstatsServiceException("There are no sport with id: " + sportId, HttpStatus.NOT_FOUND);
        }
        League league = getBrokerFactory().getLeagueBroker().create();
        league.setName(leagueName);
        league.setSportId(sportId);
        league.setCountry(country);
        league.save();

        return league;
    }
}
