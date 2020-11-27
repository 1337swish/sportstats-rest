package com.example.sportstats.controller;

import com.example.sportstats.service.GetStandingsService;
import com.example.sportstats.service.ServiceRunner;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Claes Andersson
 */
@RestController
@CrossOrigin
public class StandingsController {

    @GetMapping("/standings")
    public String getStandings(@RequestParam(required = false) Integer teamid, @RequestParam(required = false) Integer seasonid, @RequestParam(required = false) Boolean home) {

        return new ServiceRunner(new GetStandingsService(teamid, seasonid, home)).execute();
    }
}
