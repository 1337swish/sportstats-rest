package com.example.sportstats.domain;

import com.example.sportstats.record.SeasonRecord;

/**
 * Domain model representing a season.
 *
 * @author Claes Andersson
 */
public class Season {

    private SeasonRecord theRecord;

    public Season() {

        this(new SeasonRecord());
    }

    /**
     * Constructor for inserting a record manually.
     *
     * @param record a seasonrecord.
     */
    public Season(SeasonRecord record) {

        theRecord = record;
    }

    /**
     * Get the id of this season.
     *
     * @return id of this season.
     */
    public Integer getId() {

        return theRecord.getInteger("id");
    }

    /**
     * Set the year this season is starting.
     *
     * @param year the year this season is started.
     */
    public void setStartYear(Integer year) {

        theRecord.setInteger("start_year", year);
    }

    /**
     * Get the year this season is starting.
     *
     * @return year this season is started.
     */
    public Integer getStartYear() {

        return theRecord.getInteger("start_year");
    }

    /**
     * Set the year this season is ending.
     *
     * @param year the year this season is ending.
     */
    public void setEndYear(Integer year) {

        theRecord.setInteger("end_year", year);
    }

    /**
     * Get the year this season is ending.
     *
     * @return year this season is ended.
     */
    public Integer getEndYear() {

        return theRecord.getInteger("end_year");
    }

    /**
     * Set the id of the league this season belongs to.
     *
     * @param id the league id.
     */
    public void setLeagueId(Integer id) {

        theRecord.setInteger("league_id", id);
    }

    /**
     * Get the id of the league this season belongs to.
     *
     * @return id of the league this season belongs to.
     */
    public Integer getLeagueId() {

        return theRecord.getInteger("league_id");
    }

    /**
     * Get the amount of rounds in this season.
     *
     * @return number of rounds in this season.
     */
    public Integer getNbrOfRounds() {

        return theRecord.getInteger("rounds");

    }

    /**
     * Set the amount of rounds in this season.
     *
     * @param nbrOfRounds the number of rounds.
     */
    public void setNbrOfRounds(Integer nbrOfRounds) {

        theRecord.setInteger("rounds", nbrOfRounds);

    }
    
    /**
     * Saves this season to the database.
     */
    public void save() {

        theRecord.saveIt();
    }

    /**
     * Deletes this season from the database.
     */
    public void delete() {

        theRecord.delete();
    }
}
