package com.example.sportstats.domain;

import com.example.sportstats.record.RoundRecord;

/**
 * Domain model representing a round.
 *
 * @author Claes Andersson
 */
public class Round {

    private RoundRecord theRecord;

    public Round() {

        this(new RoundRecord());
    }

    /**
     * Constructor for inserting a record manually.
     *
     * @param record a roundrecord.
     */
    public Round(RoundRecord record) {

        this.theRecord = record;
    }

    /**
     * Get the id of this round.
     *
     * @return id of this round.
     */
    public Integer getId() {

        return theRecord.getInteger("id");
    }

    /**
     * Set the id of the season this round belongs to.
     *
     * @param id the id of the season.
     */
    public void setSeasonId(Integer id) {

        theRecord.setInteger("season_id", id);
    }

    /**
     * Get the id of the season this round belongs to.
     *
     * @return id of the season this round belongs to.
     */
    public Integer getSeasonId() {

        return theRecord.getInteger("season_id");
    }

    /**
     * Set the number of this round.
     *
     * @param roundNumber the number of the round.
     */
    public void setRound(Integer roundNumber) {

        theRecord.setInteger("number", roundNumber);
    }

    /**
     * Get the number of this round.
     *
     * @return number of this round.
     */
    public Integer getRound() {

        return theRecord.getInteger("number");
    }
    
    /**
     * Saves this round to the database.
     */
    public void save() {

        theRecord.saveIt();
    }

    /**
     * Deletes this round from the database.
     */
    public void delete() {

        theRecord.delete();
    }
}
