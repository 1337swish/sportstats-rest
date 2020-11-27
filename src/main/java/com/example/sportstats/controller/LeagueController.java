package com.example.sportstats.controller;

import com.example.sportstats.service.AddLeagueService;
import com.example.sportstats.service.DeleteLeagueService;
import com.example.sportstats.service.EditLeagueService;
import com.example.sportstats.service.GetLeaguesService;
import com.example.sportstats.service.ServiceRunner;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Claes Andersson
 */
@RestController
@CrossOrigin
public class LeagueController {

    @GetMapping("/leagues")
    public String getLeagues(@RequestParam(required = false) Integer id, @RequestParam(required = false) Integer sportid) {

        return new ServiceRunner(new GetLeaguesService(id, sportid)).execute();
    }

    @PostMapping("/leagues")
    public String addLeague(@RequestParam String name, @RequestParam Integer sportid, @RequestParam String country) {

        return new ServiceRunner(new AddLeagueService(name, sportid, country)).execute();
    }

    @PutMapping("/leagues")
    public String editLeague(@RequestParam Integer id, @RequestParam(required = false) String name, @RequestParam(required = false) Integer sportid, @RequestParam(required = false) String country) {

        return new ServiceRunner(new EditLeagueService(id, name, sportid, country)).execute();
    }

    @DeleteMapping("/leagues")
    public String deleteLeague(@RequestParam Integer id) {

        return new ServiceRunner(new DeleteLeagueService(id)).execute();
    }
}
