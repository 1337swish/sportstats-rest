package com.example.sportstats.controller;

import com.example.sportstats.service.GetStandingsService;
import com.example.sportstats.service.ServiceRunner;
import com.example.sportstats.service.SportstatsServiceException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Claes Andersson
 */
@RestController
@CrossOrigin
public class StandingsController {

    @GetMapping("/standings")
    public String getStandings(@RequestParam(required = false) Integer teamid, @RequestParam Integer seasonid, @RequestParam(required = false) Boolean home) {

        try {
            return new ServiceRunner(new GetStandingsService(teamid, seasonid, home)).execute();
        } catch (SportstatsServiceException e) {
            throw new ResponseStatusException(e.getHttpStatusCode(), e.getMessage());
        }
    }
}
