package com.example.sportstats.service;

import com.example.sportstats.domain.Match;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Claes Andersson
 */
public class GetMatchesService extends BaseService<List<Match>> {

    private final Integer id;
    private final Integer roundId;
    private final Integer seasonId;
    private final Integer sportId;
    private final Boolean overtime;
    private final Integer arenaId;
    private final Integer teamId;
    private final LocalDate date;
    private final LocalDate datefrom;
    private final LocalDate dateto;

    public GetMatchesService(Integer id, Integer roundId, Integer seasonId, Integer sportId, Boolean overtime, Integer arenaId, Integer teamId, LocalDate date, LocalDate datefrom, LocalDate dateto) {

        this.id = id;
        this.roundId = roundId;
        this.seasonId = seasonId;
        this.sportId = sportId;
        this.overtime = overtime;
        this.arenaId = arenaId;
        this.teamId = teamId;
        this.date = date;
        this.datefrom = datefrom;
        this.dateto = dateto;
    }

    @Override
    public List<Match> execute() {

        List<Match> matches = getBrokerFactory().getMatchBroker().findAll();

        if (id != null) {
            matches = matches.stream()
                    .filter(match -> match.getId().equals(id))
                    .collect(Collectors.toList());
        } else if (roundId != null) {
            matches = matches.stream()
                    .filter(match -> match.getRoundId().equals(roundId))
                    .collect(Collectors.toList());
        } else if (seasonId != null) {
            matches = matches.stream()
                    .filter(match -> match.getSeasonId().equals(seasonId))
                    .collect(Collectors.toList());
        } else if (sportId != null) {
            matches = matches.stream()
                    .filter(match -> match.getSportId().equals(sportId))
                    .collect(Collectors.toList());
        }
        if (overtime) {
            matches = matches.stream()
                    .filter(match -> match.getOverTime().equals(overtime))
                    .collect(Collectors.toList());
        }
        if (arenaId != null) {
            matches = matches.stream()
                    .filter(match -> match.getArenaId().equals(arenaId))
                    .collect(Collectors.toList());
        }
        if (date != null) {
            matches = matches.stream()
                    .filter(match -> match.getDate().isEqual(date))
                    .collect(Collectors.toList());
        }
        if (datefrom != null) {
            matches = matches.stream()
                    .filter(match -> match.getDate().isAfter(datefrom.minusDays(1)))
                    .collect(Collectors.toList());
        }
        if (dateto != null) {
            matches = matches.stream()
                    .filter(match -> match.getDate().isBefore(dateto.plusDays(1)))
                    .collect(Collectors.toList());
        }
        if (teamId != null) {
            matches = matches.stream()
                    .filter(match -> match.getHomeTeamId().equals(teamId) || match.getAwayTeamId().equals(teamId))
                    .collect(Collectors.toList());
        }

        matches.forEach(m -> {
            m.setHomeTeamName(getBrokerFactory().getTeamBroker().findById(m.getHomeTeamId()).getName());
            m.setAwayTeamName(getBrokerFactory().getTeamBroker().findById(m.getAwayTeamId()).getName());
            m.setArenaName(getBrokerFactory().getArenaBroker().findById(m.getArenaId()).getName());
        });

        return matches;
    }
}
