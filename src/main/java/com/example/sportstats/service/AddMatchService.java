package com.example.sportstats.service;

import com.example.sportstats.domain.Arena;
import com.example.sportstats.domain.Match;
import com.example.sportstats.domain.Team;
import java.time.LocalDate;

/**
 * Service that adds a new match.
 *
 * @author Claes Andersson
 */
public class AddMatchService extends BaseService<Match> {
    
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
    
    public AddMatchService(Integer homeTeamId, Integer awayTeamId, Integer arenaId, Integer roundId, Integer seasonId, Integer sportId, Integer homeTeamScore, Integer awayTeamScore, LocalDate date, Integer attendance, Boolean overtime) throws SportstatsServiceException {
        
        if (homeTeamScore != null && homeTeamScore < 0) {
            throw new SportstatsServiceException("Home score can't be less than 0");
        } else if (awayTeamScore != null && awayTeamScore < 0) {
            throw new SportstatsServiceException("Away score can't be less than 0");
        } else if (attendance != null && attendance < 0) {
            throw new SportstatsServiceException("Attendance can't be less than 0");
        } else if (homeTeamId.equals(awayTeamId)) {
            throw new SportstatsServiceException("Home and away teams can't be the same");
        }
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
        
        Team homeTeam = getBrokerFactory().getTeamBroker().findById(homeTeamId);
        Team awayTeam = getBrokerFactory().getTeamBroker().findById(awayTeamId);
        Arena arena = getBrokerFactory().getArenaBroker().findById(arenaId);
        
        if (arena == null) {
            throw new SportstatsServiceException("There are no arena with id: " + arenaId);
        } else if (homeTeam == null) {
            throw new SportstatsServiceException("There are no team with id: " + homeTeamId);
        } else if (awayTeam == null) {
            throw new SportstatsServiceException("There are no team with id: " + awayTeamId);
        } else if (getBrokerFactory().getRoundBroker().findById(roundId) == null) {
            throw new SportstatsServiceException("There are no round with id: " + roundId);
        } else if (getBrokerFactory().getSeasonBroker().findById(seasonId) == null) {
            throw new SportstatsServiceException("There are no season with id: " + seasonId);
        } else if (getBrokerFactory().getSportBroker().findById(sportId) == null) {
            throw new SportstatsServiceException("There are no sport with id: " + sportId);
        }
        
        Match match = getBrokerFactory().getMatchBroker().create();
        match.setHomeTeamId(homeTeamId);
        match.setAwayTeamId(awayTeamId);
        match.setArenaId(arenaId);
        match.setRoundId(roundId);
        match.setSeasonId(seasonId);
        match.setSportId(sportId);
        match.setHomeTeamScore(homeTeamScore);
        match.setAwayTeamScore(awayTeamScore);
        match.setDate(date);
        match.setAttendance(attendance);
        match.setOvertime(overtime);
        match.setHomeTeamName(homeTeam.getName());
        match.setAwayTeamName(awayTeam.getName());
        match.setArenaName(arena.getName());
        match.save();
        
        return match;
    }
}
