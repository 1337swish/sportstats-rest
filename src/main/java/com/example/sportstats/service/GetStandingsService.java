package com.example.sportstats.service;

import com.example.sportstats.domain.Match;
import com.example.sportstats.domain.Sport;
import com.example.sportstats.domain.StandingsPost;
import com.example.sportstats.domain.Team;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Claes Andersson
 */
public class GetStandingsService extends BaseService<List<StandingsPost>> {

    private final Integer teamId;
    private final Integer seasonId;
    private final Boolean home;

    public GetStandingsService(Integer teamId, Integer seasonId, Boolean home) {

        this.teamId = teamId;
        this.seasonId = seasonId;
        this.home = home;
    }

    @Override
    public List<StandingsPost> execute() {

        List<Match> matches = getBrokerFactory().getMatchBroker().findAll();

        if (matches.isEmpty()) {
            throw new SportstatsServiceException("There are no matches to show standings for", HttpStatus.NOT_FOUND);
        }

        if (teamId != null) {
            matches = matches.stream()
                    .filter(match -> match.getHomeTeamId().equals(teamId) || match.getAwayTeamId().equals(teamId))
                    .collect(Collectors.toList());
        }
        if (seasonId != null) {
            matches = matches.stream()
                    .filter(match -> match.getSeasonId().equals(seasonId))
                    .collect(Collectors.toList());
        }

        // Only get played matches
        matches = matches.stream()
                .filter(match -> match.getAttendance() != null)
                .collect(Collectors.toList());

        if (matches.isEmpty()) {
            throw new SportstatsServiceException("There are no matches to show standings for", HttpStatus.NOT_FOUND);
        }

        Sport sport = getBrokerFactory().getSportBroker().findById(matches.get(0).getSportId());
        Integer win = sport.getPointsForWin();
        Integer winOvertime = sport.getPointsForOvertimeWin();
        Integer tie = sport.getPointsForDraw();
        Integer loss = sport.getPointsForLoss();
        Integer lossOvertime = sport.getPointsForOvertimeLoss();

        Map<Integer, StandingsPost> teamPosts = new HashMap();
        matches.forEach(match -> {
            StandingsPost homePost;
            StandingsPost awayPost;

            // Make sure each team have a post
            if (teamPosts.containsKey(match.getHomeTeamId())) {
                homePost = teamPosts.get(match.getHomeTeamId());
            } else {
                homePost = new StandingsPost();
                Team homeTeam = getBrokerFactory().getTeamBroker().findById(match.getHomeTeamId());
                homePost.setTeamId(homeTeam.getId());
                homePost.setTeamName(homeTeam.getName());
                teamPosts.put(homeTeam.getId(), homePost);
            }
            if (teamPosts.containsKey(match.getAwayTeamId())) {
                awayPost = teamPosts.get(match.getAwayTeamId());
            } else {
                awayPost = new StandingsPost();
                Team awayTeam = getBrokerFactory().getTeamBroker().findById(match.getAwayTeamId());
                awayPost.setTeamId(awayTeam.getId());
                awayPost.setTeamName(awayTeam.getName());
                teamPosts.put(awayTeam.getId(), awayPost);
            }
            // Fill table
            if (home == Boolean.TRUE || home == null) {
                homePost.increaseAmountOfMatches();
            }
            if (home == Boolean.FALSE || home == null) {
                awayPost.increaseAmountOfMatches();
            }
            if (match.getHomeTeamScore() > match.getAwayTeamScore()) {
                if (match.getOverTime()) {
                    if (home == Boolean.TRUE || home == null) {
                        homePost.increaseAmountOfWinsOvertime();
                        homePost.increaseTotalScore(winOvertime);
                    }
                    if (home == Boolean.FALSE || home == null) {
                        awayPost.increaseAmountOfLossesOvertime();
                        awayPost.increaseTotalScore(lossOvertime);
                    }
                } else {
                    if (home == Boolean.TRUE || home == null) {
                        homePost.increaseAmountOfWins();
                        homePost.increaseTotalScore(win);
                    }
                    if (home == Boolean.FALSE || home == null) {
                        awayPost.increaseAmountOfLosses();
                        awayPost.increaseTotalScore(loss);
                    }
                }
            } else if (match.getHomeTeamScore() < match.getAwayTeamScore()) {
                if (match.getOverTime()) {
                    if (home == Boolean.FALSE || home == null) {
                        awayPost.increaseAmountOfWinsOvertime();
                        awayPost.increaseTotalScore(winOvertime);
                    }
                    if (home == Boolean.TRUE || home == null) {
                        homePost.increaseAmountOfLossesOvertime();
                        homePost.increaseTotalScore(lossOvertime);
                    }
                } else {
                    if (home == Boolean.FALSE || home == null) {
                        awayPost.increaseAmountOfWins();
                        awayPost.increaseTotalScore(win);
                    }
                    if (home == Boolean.TRUE || home == null) {
                        homePost.increaseAmountOfLosses();
                        homePost.increaseTotalScore(loss);
                    }
                }
            } else {
                if (home == Boolean.TRUE || home == null) {
                    homePost.increaseAmountOfTies();
                    homePost.increaseTotalScore(tie);
                }
                if (home == Boolean.FALSE || home == null) {
                    awayPost.increaseAmountOfTies();
                    awayPost.increaseTotalScore(tie);
                }
            }
            if (home == Boolean.TRUE || home == null) {
                homePost.increaseAmountOfGoalsFor(match.getHomeTeamScore());
                homePost.increaseAmountOfGoalsAgainst(match.getAwayTeamScore());
            }
            if (home == Boolean.FALSE || home == null) {
                awayPost.increaseAmountOfGoalsFor(match.getAwayTeamScore());
                awayPost.increaseAmountOfGoalsAgainst(match.getHomeTeamScore());
            }
        });

        List<StandingsPost> posts = new ArrayList(teamPosts.values());
        if (teamId != null) {
            posts = posts.stream()
                    .filter(post -> post.getTeamId().equals(teamId))
                    .collect(Collectors.toList());
        }
        posts.sort((h1, h2) -> h2.getTotalScore().compareTo(h1.getTotalScore()));

        return posts;
    }
}
