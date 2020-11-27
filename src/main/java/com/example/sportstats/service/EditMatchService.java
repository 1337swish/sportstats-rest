package com.example.sportstats.service;

import com.example.sportstats.domain.Arena;
import com.example.sportstats.domain.Match;
import com.example.sportstats.domain.Round;
import com.example.sportstats.domain.Season;
import com.example.sportstats.domain.Sport;
import com.example.sportstats.domain.Team;
import java.time.LocalDate;

/**
 * Service that edits an existing match.
 *
 * @author Claes Andersson
 */
public class EditMatchService extends BaseService<Match> {

    private final Integer matchId;
    private final Integer homeTeamId;
    private final Integer awayTeamId;
    private final Integer arenaId;
    private final Integer roundId;
    private final Integer seasonId;
    private final Integer sportId;
    private final Integer homeTeamScore;
    private final Integer awayTeamScore;
    private final LocalDate date;
    private final Integer attendance;
    private final Boolean overtime;

    public EditMatchService(Integer matchId, Integer homeTeamId, Integer awayTeamId, Integer arenaId, Integer roundId, Integer seasonId, Integer sportId, Integer homeTeamScore, Integer awayTeamScore, LocalDate date, Integer attendance, Boolean overtime) throws SportstatsServiceException {

        if (homeTeamScore != null && homeTeamScore < 0) {
            throw new SportstatsServiceException("Home score can't be less than 0");
        } else if (awayTeamScore != null && awayTeamScore < 0) {
            throw new SportstatsServiceException("Away score can't be less than 0");
        } else if (attendance != null && attendance < 0) {
            throw new SportstatsServiceException("Attendance can't be less than 0");
        } else if (homeTeamId.equals(awayTeamId)) {
            throw new SportstatsServiceException("Home and away teams can't be the same");
        }

        this.matchId = matchId;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.arenaId = arenaId;
        this.roundId = roundId;
        this.seasonId = seasonId;
        this.sportId = sportId;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.date = date;
        this.attendance = attendance;
        this.overtime = overtime;
    }

    @Override
    public Match execute() {

        Match match = getBrokerFactory().getMatchBroker().findById(matchId);
        Sport sport = getBrokerFactory().getSportBroker().findById(sportId);
        Team homeTeam = getBrokerFactory().getTeamBroker().findById(homeTeamId);
        Team awayTeam = getBrokerFactory().getTeamBroker().findById(awayTeamId);
        Arena arena = getBrokerFactory().getArenaBroker().findById(arenaId);
        Round round = getBrokerFactory().getRoundBroker().findById(roundId);
        Season season = getBrokerFactory().getSeasonBroker().findById(seasonId);

        if (match == null) {
            throw new SportstatsServiceException("There are no match with id: " + matchId);
        }
        if (sport != null) {
            match.setSportId(sportId);
        } else {
            throw new SportstatsServiceException("There are no sport with id: " + sportId);
        }
        if (homeTeam != null) {
            match.setHomeTeamId(homeTeamId);
            match.setHomeTeamName(homeTeam.getName());
        } else {
            throw new SportstatsServiceException("There are no team with id: " + homeTeamId);
        }
        if (awayTeam != null) {
            match.setAwayTeamId(awayTeamId);
            match.setAwayTeamName(awayTeam.getName());
        } else {
            throw new SportstatsServiceException("There are no team with id: " + awayTeamId);
        }
        if (arena != null) {
            match.setArenaId(arenaId);
            match.setArenaName(getBrokerFactory().getArenaBroker().findById(arenaId).getName());
        } else {
            throw new SportstatsServiceException("There are no arena with id: " + arenaId);
        }
        if (round != null) {
            match.setRoundId(roundId);
        } else {
            throw new SportstatsServiceException("There are no round with id: " + roundId);
        }
        if (season != null) {
            match.setSeasonId(seasonId);
        } else {
            throw new SportstatsServiceException("There are no season with id: " + seasonId);
        }
        if (homeTeamScore != null) {
            match.setHomeTeamScore(homeTeamScore);
        }
        if (awayTeamScore != null) {
            match.setAwayTeamScore(awayTeamScore);
        }
        if (date != null) {
            match.setDate(date);
        }
        if (attendance != null) {
            match.setAttendance(attendance);
        }
        if (overtime != null) {
            match.setOvertime(overtime);
        }
        match.save();

        return match;
    }
}
