package com.example.sportstats.domain;

import com.example.sportstats.record.TeamRecord;

/**
 * Domain model representing a team.
 *
 * @author Claes Andersson
 */
public class Team {

    private TeamRecord theRecord;

    public Team() {

        this(new TeamRecord());
    }

    /**
     * Constructor for inserting a record manually.
     *
     * @param record a teamrecord.
     */
    public Team(TeamRecord record) {

        theRecord = record;
    }

    /**
     * Get the id of this team.
     *
     * @return id of this team.
     */
    public Integer getId() {

        return theRecord.getInteger("id");
    }

    /**
     * Set the name of this team.
     *
     * @param name the name of this team.
     */
    public void setName(String name) {

        theRecord.setString("name", name);
    }

    /**
     * Get the name of this team.
     *
     * @return name of this team.
     */
    public String getName() {

        return theRecord.getString("name");
    }

    /**
     * Set the id of the arena this team belongs to.
     *
     * @param id the id of the arena.
     */
    public void setArenaId(Integer id) {

        theRecord.setInteger("arena_id", id);
    }

    /**
     * Get the id of the arena this team belongs to.
     *
     * @return id of the arena this team belongs to.
     */
    public Integer getArenaId() {

        return theRecord.getInteger("arena_id");
    }

    /**
     * Set the id of the sport this team belongs to.
     *
     * @param id the id of the sport.
     */
    public void setSportId(Integer id) {

        theRecord.setInteger("sport_id", id);
    }

    /**
     * Get the id of the sport this team belongs to.
     *
     * @return id of the sport this team belongs to.
     */
    public Integer getSportId() {

        return theRecord.getInteger("sport_id");
    }

    /**
     * Get the year when this team was created.
     *
     * @return year when this team was created.
     */
    public Integer getCreatedYear() {

        return theRecord.getInteger("created_year");
    }

    /**
     * Set the year this team was created.
     *
     * @param year the year when this team was created.
     */
    public void setCreatedYear(Integer year) {

        theRecord.setInteger("created_year", year);
    }
    
    /**
     * Saves this team to the database.
     */
    public void save() {

        theRecord.saveIt();
    }

    /**
     * Deletes this team from the database.
     */
    public void delete() {

        theRecord.delete();
    }
}
