package com.example.sportstats.domain;

/**
 *
 * @author Claes Andersson
 */
public class StandingsPost {

    private String teamName;
    private Integer teamId, totalScore, gamesPlayed, wins, winsOvertime, losses, lossesOvertime, ties, goalsFor, goalsAgainst;

    public StandingsPost() {

        teamId = totalScore = gamesPlayed = wins = ties = losses = goalsFor = goalsAgainst = lossesOvertime = winsOvertime = 0;
    }

    /**
     * Get the id of the team this post belongs to.
     *
     * @return id of the team.
     */
    public Integer getTeamId() {

        return teamId;
    }

    /**
     * Set the id of the team this post belongs to.
     *
     * @param teamId id of the team.
     */
    public void setTeamId(Integer teamId) {

        this.teamId = teamId;
    }

    /**
     * Get the amount of matches this team has played.
     *
     * @return amount of played matches.
     */
    public Integer getAmountOfMatches() {

        return gamesPlayed;
    }

    /**
     * Increase matches played by 1.
     *
     */
    public void increaseAmountOfMatches() {

        gamesPlayed++;
    }

    /**
     * Get the amount of matches this team has won.
     *
     * @return amount of won matches.
     */
    public Integer getAmountOfWins() {

        return wins;
    }

    /**
     * Increase wins by 1.
     *
     */
    public void increaseAmountOfWins() {

        wins++;
    }

    /**
     * Get the amount of matches this team has won on overtime.
     *
     * @return amount of won matches on overtime.
     */
    public Integer getAmountOfWinsOvertime() {

        return winsOvertime;
    }

    /**
     * Increase wins on overtime by 1.
     *
     */
    public void increaseAmountOfWinsOvertime() {

        winsOvertime++;
    }

    /**
     * Get the amount of matches this team has tied.
     *
     * @return amount of tied matches.
     */
    public Integer getAmountOfTies() {

        return ties;
    }

    /**
     * Increase ties by 1.
     *
     */
    public void increaseAmountOfTies() {

        ties++;
    }

    /**
     * Get the amount of matches this team has lost.
     *
     * @return amount of lost matches.
     */
    public Integer getAmountOfLosses() {

        return losses;
    }

    /**
     * Increase losses by 1.
     *
     */
    public void increaseAmountOfLosses() {

        losses++;
    }

    /**
     * Get the amount of matches this team has lost on overtime.
     *
     * @return amount of lost matches on overtime.
     */
    public Integer getAmountOfLossesOvertime() {

        return lossesOvertime;
    }

    /**
     * Increase losses on overtime by 1.
     *
     */
    public void increaseAmountOfLossesOvertime() {

        lossesOvertime++;
    }

    /**
     * Get the amount of goals this team has scored.
     *
     * @return amount of goals scored.
     */
    public Integer getAmountOfGoalsFor() {

        return goalsFor;
    }

    /**
     * Increase goals for by an amount.
     *
     * @param goals amount of goals scored.
     */
    public void increaseAmountOfGoalsFor(Integer goals) {

        goalsFor += goals;
    }

    /**
     * Get the amount of goals this team has allowed.
     *
     * @return amount of goals allowed.
     */
    public Integer getAmountOfGoalsAgainst() {

        return goalsAgainst;
    }

    /**
     * Increase goals against by an amount.
     *
     * @param goals amount of goals allowed.
     */
    public void increaseAmountOfGoalsAgainst(Integer goals) {

        goalsAgainst += goals;
    }

    /**
     * Get the goal difference.
     *
     * @return the goal difference.
     */
    public Integer getGoalDifference() {

        return goalsFor - goalsAgainst;
    }

    /**
     * Get the amount of points this team has.
     *
     * @return amount of points.
     */
    public Integer getTotalScore() {

        return totalScore;
    }

    /**
     * Increase the amount of points this team has.
     *
     * @param score amount of points to increase.
     */
    public void increaseTotalScore(Integer score) {

        totalScore += score;
    }

    /**
     * Get the name of the team of this post.
     *
     * @return name of team.
     */
    public String getTeamName() {

        return teamName;
    }

    /**
     * Set the name of the team of this post.
     *
     * @param teamName name of team.
     */
    public void setTeamName(String teamName) {

        this.teamName = teamName;
    }
}
