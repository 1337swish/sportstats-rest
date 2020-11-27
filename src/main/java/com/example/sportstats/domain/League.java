package com.example.sportstats.domain;

import com.example.sportstats.record.LeagueRecord;

/**
 * Domain model representing a league.
 *
 * @author Claes Andersson
 */
public class League {

    private LeagueRecord theRecord;

    public League() {

        this(new LeagueRecord());
    }

    /**
     * Constructor for inserting a record manually.
     *
     * @param record a leaguerecord.
     */
    public League(LeagueRecord record) {

        theRecord = record;
    }

    /**
     * Get the id of this league.
     *
     * @return id of this league.
     */
    public Integer getId() {

        return theRecord.getInteger("id");
    }

    /**
     * Set the name of this league.
     *
     * @param name the name of this league.
     */
    public void setName(String name) {

        theRecord.setString("name", name);
    }

    /**
     * Get the name of this league.
     *
     * @return name of this league.
     */
    public String getName() {

        return theRecord.getString("name");
    }

    /**
     * Set the id of the sport this league belongs to.
     *
     * @param id the sport id.
     */
    public void setSportId(Integer id) {

        theRecord.setInteger("sport_id", id);
    }

    /**
     * Get the id of the sport this league belongs to.
     *
     * @return the sport id.
     */
    public Integer getSportId() {

        return theRecord.getInteger("sport_id");
    }

    /**
     * Set the country this league is played in.
     *
     * @param country the name of the country.
     */
    public void setCountry(String country) {

        theRecord.setString("country", country);
    }

    /**
     * Get the country this league is played in.
     *
     * @return the name of the country.
     */
    public String getCountry() {

        return theRecord.getString("country");
    }
    
    /**
     * Saves this league to the database.
     */
    public void save() {

        theRecord.saveIt();
    }

    /**
     * Deletes this league from the database.
     */
    public void delete() {

        theRecord.delete();
    }
}
