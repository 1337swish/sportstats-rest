package com.example.sportstats.controller;

import com.example.sportstats.service.AddMatchService;
import com.example.sportstats.service.DeleteMatchService;
import com.example.sportstats.service.EditMatchService;
import com.example.sportstats.service.GetMatchesService;
import com.example.sportstats.service.ServiceRunner;
import com.example.sportstats.service.SportstatsServiceException;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
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
public class MatchController {

    @GetMapping("/matches")
    public String getMatches(@RequestParam(required = false) Integer id, @RequestParam(required = false) Integer roundid, @RequestParam(required = false) Integer seasonid, @RequestParam(required = false) Integer sportid, @RequestParam(defaultValue = "false") Boolean overtime, @RequestParam(required = false) Integer arenaid, @RequestParam(required = false) Integer teamid, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datefrom, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateto) {

        try {
            return new ServiceRunner(new GetMatchesService(id, roundid, seasonid, sportid, overtime, arenaid, teamid, date, datefrom, dateto)).execute();
        } catch (SportstatsServiceException e) {
            throw new ResponseStatusException(e.getHttpStatusCode(), e.getMessage());
        }
    }

    @PostMapping("/matches")
    public String addMatch(@RequestParam Integer hometeamid, @RequestParam Integer awayteamid, @RequestParam Integer arenaid, @RequestParam Integer roundid, @RequestParam Integer seasonid, @RequestParam Integer sportid, @RequestParam(required = false) Integer hometeamscore, @RequestParam(required = false) Integer awayteamscore, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestParam(required = false) Integer attendance, @RequestParam(required = false) Boolean overtime) {

        try {
            return new ServiceRunner(new AddMatchService(hometeamid, awayteamid, arenaid, roundid, seasonid, sportid, hometeamscore, awayteamscore, date, attendance, overtime)).execute();
        } catch (SportstatsServiceException e) {
            throw new ResponseStatusException(e.getHttpStatusCode(), e.getMessage());
        }
    }

    @PutMapping("/matches")
    public String editMatch(@RequestParam Integer id, @RequestParam(required = false) Integer hometeamid, @RequestParam(required = false) Integer awayteamid, @RequestParam(required = false) Integer arenaid, @RequestParam(required = false) Integer roundid, @RequestParam(required = false) Integer seasonid, @RequestParam(required = false) Integer sportid, @RequestParam(required = false) Integer hometeamscore, @RequestParam(required = false) Integer awayteamscore, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestParam(required = false) Integer attendance, @RequestParam(required = false) Boolean overtime) {

        try {
            return new ServiceRunner(new EditMatchService(id, hometeamid, awayteamid, arenaid, roundid, seasonid, sportid, hometeamscore, awayteamscore, date, attendance, overtime)).execute();
        } catch (SportstatsServiceException e) {
            throw new ResponseStatusException(e.getHttpStatusCode(), e.getMessage());
        }
    }

    @DeleteMapping("/matches")
    public String deleteMatch(@RequestParam Integer id) {

        try {
            return new ServiceRunner(new DeleteMatchService(id)).execute();
        } catch (SportstatsServiceException e) {
            throw new ResponseStatusException(e.getHttpStatusCode(), e.getMessage());
        }
    }
}
