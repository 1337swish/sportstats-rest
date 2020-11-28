package com.example.sportstats.service;

import com.example.sportstats.domain.Arena;
import com.example.sportstats.domain.Match;
import com.example.sportstats.domain.Round;
import com.example.sportstats.domain.Season;
import com.example.sportstats.domain.Team;
import java.time.LocalDate;
import org.springframework.http.HttpStatus;

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

    public EditMatchService(Integer matchId, Integer homeTeamId, Integer awayTeamId, Integer arenaId, Integer roundId, Integer seasonId, Integer sportId, Integer homeTeamScore, Integer awayTeamScore, LocalDate date, Integer attendance, Boolean overtime) {

        if (homeTeamScore != null && homeTeamScore < 0) {
            throw new SportstatsServiceException("Home score can't be less than 0", HttpStatus.BAD_REQUEST);
        } else if (awayTeamScore != null && awayTeamScore < 0) {
            throw new SportstatsServiceException("Away score can't be less than 0", HttpStatus.BAD_REQUEST);
        } else if (attendance != null && attendance < 0) {
            throw new SportstatsServiceException("Attendance can't be less than 0", HttpStatus.BAD_REQUEST);
        } else if (homeTeamId.equals(awayTeamId)) {
            throw new SportstatsServiceException("Home and away teams can't be the same", HttpStatus.BAD_REQUEST);
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

        if (match == null) {
            throw new SportstatsServiceException("There are no match with id: " + matchId, HttpStatus.NOT_FOUND);
        }
        if (sportId != null) {
            if (getBrokerFactory().getSportBroker().findById(sportId) == null) {
                throw new SportstatsServiceException("There are no sport with id: " + sportId, HttpStatus.NOT_FOUND);
            } else {
                match.setSportId(sportId);
            }
        }
        if (homeTeamId != null) {
            Team homeTeam = getBrokerFactory().getTeamBroker().findById(homeTeamId);
            if (homeTeam == null) {
                throw new SportstatsServiceException("There are no team with id: " + homeTeamId, HttpStatus.NOT_FOUND);
            } else {
                match.setHomeTeamId(homeTeamId);
                match.setHomeTeamName(homeTeam.getName());
            }
        }
        if (awayTeamId != null) {
            Team awayTeam = getBrokerFactory().getTeamBroker().findById(awayTeamId);
            if (awayTeam == null) {
                throw new SportstatsServiceException("There are no team with id: " + awayTeamId, HttpStatus.NOT_FOUND);
            } else {
                match.setAwayTeamId(awayTeamId);
                match.setAwayTeamName(awayTeam.getName());
            }
        }
        if (arenaId != null) {
            Arena arena = getBrokerFactory().getArenaBroker().findById(arenaId);
            if (arena == null) {
                throw new SportstatsServiceException("There are no arena with id: " + arenaId, HttpStatus.NOT_FOUND);
            } else {
                match.setArenaId(arenaId);
                match.setArenaName(getBrokerFactory().getArenaBroker().findById(arenaId).getName());
            }
        }
        if (roundId != null) {
            if (getBrokerFactory().getRoundBroker().findById(roundId) == null) {
                throw new SportstatsServiceException("There are no round with id: " + roundId, HttpStatus.NOT_FOUND);
            } else {
                match.setRoundId(roundId);
            }
        }
        if (seasonId != null) {
            if (getBrokerFactory().getSeasonBroker().findById(seasonId) == null) {
                throw new SportstatsServiceException("There are no season with id: " + seasonId, HttpStatus.NOT_FOUND);
            } else {
                match.setSeasonId(seasonId);
            }
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
