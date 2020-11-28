package com.example.sportstats.service;

import com.example.sportstats.domain.Match;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;

/**
 * Service that deletes matches.
 *
 * @author Claes Andersson
 */
public class DeleteMatchService extends BaseService<Map> {

    private final Integer id;

    public DeleteMatchService(Integer id) {

        this.id = id;
    }

    @Override
    public Map execute() {

        Match match = getBrokerFactory().getMatchBroker().findById(id);
        if (match == null) {
            throw new SportstatsServiceException("There are no match with id: " + id, HttpStatus.NOT_FOUND);
        }
        Map deletedMatch = new HashMap();
        deletedMatch.put("id", match.getId());
        deletedMatch.put("homeTeamId", match.getHomeTeamId());
        deletedMatch.put("awayTeamId", match.getAwayTeamId());
        deletedMatch.put("arenaId", match.getArenaId());
        deletedMatch.put("roundId", match.getRoundId());
        deletedMatch.put("seasonId", match.getSeasonId());
        deletedMatch.put("sportId", match.getSportId());
        deletedMatch.put("homeTeamScore", match.getHomeTeamScore());
        deletedMatch.put("awayTeamScore", match.getAwayTeamScore());
        deletedMatch.put("date", match.getDate());
        deletedMatch.put("attendance", match.getAttendance());
        deletedMatch.put("overTime", match.getOverTime());
        match.delete();

        return deletedMatch;
    }
}
