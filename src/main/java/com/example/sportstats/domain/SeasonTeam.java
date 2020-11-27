package com.example.sportstats.domain;

import com.example.sportstats.record.SeasonTeamRecord;

/**
 * Domain model representing a season and team connection.
 *
 * @author Claes Andersson
 */
public class SeasonTeam {

    private SeasonTeamRecord theRecord;
    private String teamName;
    private String season;

    public SeasonTeam() {

        this(new SeasonTeamRecord());
    }

    /**
     * Constructor for inserting a record manually.
     *
     * @param record a seasonteamrecord.
     */
    public SeasonTeam(SeasonTeamRecord record) {

        theRecord = record;
    }

    /**
     * Get the id of this seasonTeam.
     *
     * @return the id of this seasonTeam.
     */
    public Integer getId() {

        return theRecord.getInteger("id");
    }

    /**
     * Set the name of the team belonging to this season.
     *
     * @param name the name of the team.
     */
    public void setTeamName(String name) {

        this.teamName = name;
    }

    /**
     * Get the name of the team belonging to this season.
     *
     * @return the name of the team.
     */
    public String getTeamName() {

        return this.teamName;
    }

    /**
     * Set the id of the team belonging to this season.
     *
     * @param teamId id of this seasonteam.
     */
    public void setTeamId(Integer teamId) {

        theRecord.setInteger("team_id", teamId);
    }

    /**
     * Get the id of the team belonging to this season.
     *
     * @return the id of the team.
     */
    public Integer getTeamId() {

        return theRecord.getInteger("team_id");
    }

    /**
     * Set the name of the season this team belongs to.
     *
     * @param name the name of the season.
     */
    public void setSeasonName(String name) {

        this.season = name;
    }

    /**
     * Get the name of the season this team belongs to.
     *
     * @return name of the season.
     */
    public String getSeasonName() {

        return this.season;
    }

    /**
     * Set the id of the season this team belongs to.
     *
     * @param seasonId the id of the season.
     */
    public void setSeasonId(Integer seasonId) {

        theRecord.setInteger("season_id", seasonId);
    }

    /**
     * Get the id of the season this team belongs to.
     *
     * @return id of the season this team belongs to.
     */
    public Integer getSeasonId() {

        return theRecord.getInteger("season_id");
    }

    /**
     * Saves this seasonTeam to the database.
     */
    public void save() {

        theRecord.saveIt();
    }

    /**
     * Deletes this seasonTeam from the database.
     */
    public void delete() {

        theRecord.delete();
    }
}
