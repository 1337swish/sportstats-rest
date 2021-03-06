package com.example.sportstats.controller;

import com.example.sportstats.service.AddSeasonTeamService;
import com.example.sportstats.service.DeleteSeasonTeamService;
import com.example.sportstats.service.EditSeasonTeamService;
import com.example.sportstats.service.GetSeasonTeamService;
import com.example.sportstats.service.ServiceRunner;
import com.example.sportstats.service.SportstatsServiceException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Claes Andersson
 */
@RestController
@CrossOrigin
public class SeasonTeamController {

    @GetMapping("/seasonteams")
    public String getTeamToSeason(@RequestParam(required = false) Integer teamid, @RequestParam(required = false) Integer seasonid) {

        try {
            return new ServiceRunner(new GetSeasonTeamService(teamid, seasonid)).execute();
        } catch (SportstatsServiceException e) {
            throw new ResponseStatusException(e.getHttpStatusCode(), e.getMessage());
        }
    }

    @PostMapping("/seasonteams")
    public String addSeasonTeam(@RequestParam Integer teamid, @RequestParam Integer seasonid) {

        try {
            return new ServiceRunner(new AddSeasonTeamService(teamid, seasonid)).execute();
        } catch (SportstatsServiceException e) {
            throw new ResponseStatusException(e.getHttpStatusCode(), e.getMessage());
        }
    }

    @PutMapping("/seasonteams")
    public String editSeasonTeam(@RequestParam Integer id, @RequestParam(required = false) Integer teamid, @RequestParam(required = false) Integer seasonid) {

        try {
            return new ServiceRunner(new EditSeasonTeamService(id, teamid, seasonid)).execute();
        } catch (SportstatsServiceException e) {
            throw new ResponseStatusException(e.getHttpStatusCode(), e.getMessage());
        }
    }

    @DeleteMapping("/seasonteams")
    public String deleteSeasonTeam(@RequestParam Integer id) {

        try {
            return new ServiceRunner(new DeleteSeasonTeamService(id)).execute();
        } catch (SportstatsServiceException e) {
            throw new ResponseStatusException(e.getHttpStatusCode(), e.getMessage());
        }
    }
}
