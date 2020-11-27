package com.example.sportstats.domain;

import com.example.sportstats.record.MatchRecord;
import java.time.LocalDate;

/**
 * Domain model representing a match.
 *
 * @author Claes Andersson
 */
public class Match {

    private MatchRecord theRecord;
    private String homeTeamName;
    private String awayTeamName;
    private String arenaName;

    public Match() {

        this(new MatchRecord());
    }

    /**
     * Constructor for inserting a record manually.
     *
     * @param record a matchrecord.
     */
    public Match(MatchRecord record) {

        theRecord = record;
    }

    /**
     * Get the id of this match.
     *
     * @return id of this match.
     */
    public Integer getId() {

        return theRecord.getInteger("id");
    }

    /**
     * Set the date when this match is played.
     *
     * @param date the date of this match.
     */
    public void setDate(LocalDate date) {

        theRecord.set("date", date);
    }

    /**
     * Get the date when this match is played.
     *
     * @return date when this match is played.
     */
    public LocalDate getDate() {

        return theRecord.getDate("date").toLocalDate();
    }

    /**
     * Set the amount of spectators on this match.
     *
     * @param spectators amount of spectators.
     */
    public void setAttendance(Integer spectators) {

        theRecord.setInteger("attendance", spectators);
    }

    /**
     * Get the amount of spectators on this match.
     *
     * @return amount of spectators on this match.
     */
    public Integer getAttendance() {

        return theRecord.getInteger("attendance");
    }

    /**
     * Set the id of the home team.
     *
     * @param id home team id.
     */
    public void setHomeTeamId(Integer id) {

        theRecord.setInteger("home_team_id", id);
    }

    /**
     * Get the id of the home team.
     *
     * @return id of the home team.
     */
    public Integer getHomeTeamId() {

        return theRecord.getInteger("home_team_id");
    }

    /**
     * Set the id of the away team.
     *
     * @param id away team id.
     */
    public void setAwayTeamId(Integer id) {

        theRecord.setInteger("away_team_id", id);
    }

    /**
     * Get the id of the away team.
     *
     * @return id of the away team.
     */
    public Integer getAwayTeamId() {

        return theRecord.getInteger("away_team_id");
    }

    /**
     * Set the score of the home team.
     *
     * @param score home team score.
     */
    public void setHomeTeamScore(Integer score) {

        theRecord.setInteger("home_team_score", score);
    }

    /**
     * Get the score of the home team.
     *
     * @return score of the home team.
     */
    public Integer getHomeTeamScore() {

        return theRecord.getInteger("home_team_score");
    }

    /**
     * Set the score of the away team.
     *
     * @param score away team score.
     */
    public void setAwayTeamScore(Integer score) {

        theRecord.setInteger("away_team_score", score);
    }

    /**
     * Get the score of the away team.
     *
     * @return score of the away team.
     */
    public Integer getAwayTeamScore() {

        return theRecord.getInteger("away_team_score");
    }

    /**
     * Set the id of the arena this match was played in.
     *
     * @param id the id of the arena.
     */
    public void setArenaId(Integer id) {

        theRecord.setInteger("arena_id", id);
    }

    /**
     * Get the id of the arena this match was played in.
     *
     * @return id of the arena this match was played in.
     */
    public Integer getArenaId() {

        return theRecord.getInteger("arena_id");
    }

    /**
     * Set the id of the season this match belongs to.
     *
     * @param id the id of the season.
     */
    public void setSeasonId(Integer id) {

        theRecord.setInteger("season_id", id);
    }

    /**
     * Get the id of the season this match belongs to.
     *
     * @return id of the season this match belongs to.
     */
    public Integer getSeasonId() {

        return theRecord.getInteger("season_id");
    }
    
    /**
     * Set the id of the sport this match belongs to.
     *
     * @param id the id of the sport.
     */
    public void setSportId(Integer id) {

        theRecord.setInteger("sport_id", id);
    }

    /**
     * Get the id of the sport this match belongs to.
     *
     * @return id of the sport this match belongs to.
     */
    public Integer getSportId() {

        return theRecord.getInteger("sport_id");
    }

    /**
     * Set the id of the round this match belongs to.
     *
     * @param roundId the id of the round.
     */
    public void setRoundId(Integer roundId) {

        theRecord.setInteger("round_id", roundId);
    }

    /**
     * Get the id of the round this match belongs to.
     *
     * @return id of the round this match belongs to.
     */
    public Integer getRoundId() {

        return theRecord.getInteger("round_id");
    }

    /**
     * Set overtime for this match.
     *
     * @param overtime true if overtime was played, false otherwise.
     */
    public void setOvertime(Boolean overtime) {

        theRecord.setBoolean("overtime", overtime);
    }

    /**
     * Checks if overtime was played.
     *
     * @return true if overtime was played, false otherwise.
     */
    public Boolean getOverTime() {

        return theRecord.getBoolean("overtime");
    }

    /**
     * Set the name of the home team.
     *
     * @param name the name of the home team playing.
     */
    public void setHomeTeamName(String name) {

        this.homeTeamName = name;
    }

    /**
     * Get the name of the home team.
     *
     * @return the name of the home team playing.
     */
    public String getHomeTeamName() {

        return this.homeTeamName;
    }

    /**
     * Set the name of the away team.
     *
     * @param name the name of the away team playing.
     */
    public void setAwayTeamName(String name) {

        this.awayTeamName = name;
    }

    /**
     * Get the name of the away team.
     *
     * @return the name of the away team playing.
     */
    public String getAwayTeamName() {

        return this.awayTeamName;
    }

    /**
     * Set the name of the arena.
     *
     * @param name the name of the arena.
     */
    public void setArenaName(String name) {

        this.arenaName = name;
    }

    /**
     * Get the name of the arena.
     *
     * @return the name of the arena.
     */
    public String getArenaName() {

        return this.arenaName;
    }
    
    /**
     * Saves this match to the database.
     */
    public void save() {

        theRecord.saveIt();
    }

    /**
     * Deletes this match from the database.
     */
    public void delete() {

        theRecord.delete();
    }
}
