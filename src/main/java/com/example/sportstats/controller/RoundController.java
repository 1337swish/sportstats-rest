package com.example.sportstats.controller;

import com.example.sportstats.service.GetRoundsService;
import com.example.sportstats.service.AddRoundService;
import com.example.sportstats.service.DeleteRoundService;
import com.example.sportstats.service.EditRoundService;
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
public class RoundController {

    @GetMapping("/rounds")
    public String getRounds(@RequestParam(required = false) Integer id, @RequestParam(required = false) Integer seasonid) {

        try {
            return new ServiceRunner(new GetRoundsService(id, seasonid)).execute();
        } catch (SportstatsServiceException e) {
            throw new ResponseStatusException(e.getHttpStatusCode(), e.getMessage());
        }
    }

    @PostMapping("/rounds")
    public String addRound(@RequestParam Integer seasonid, @RequestParam Integer roundnumber) {

        try {
            return new ServiceRunner(new AddRoundService(seasonid, roundnumber)).execute();
        } catch (SportstatsServiceException e) {
            throw new ResponseStatusException(e.getHttpStatusCode(), e.getMessage());
        }
    }

    @PutMapping("/rounds")
    public String editRound(@RequestParam Integer id, @RequestParam(required = false) Integer seasonid, @RequestParam(required = false) Integer roundnumber) {

        try {
            return new ServiceRunner(new EditRoundService(id, seasonid, roundnumber)).execute();
        } catch (SportstatsServiceException e) {
            throw new ResponseStatusException(e.getHttpStatusCode(), e.getMessage());
        }
    }

    @DeleteMapping("/rounds")
    public String deleteRound(@RequestParam Integer id) {

        try {
            return new ServiceRunner(new DeleteRoundService(id)).execute();
        } catch (SportstatsServiceException e) {
            throw new ResponseStatusException(e.getHttpStatusCode(), e.getMessage());
        }
    }
}
