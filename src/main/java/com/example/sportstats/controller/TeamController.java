package com.example.sportstats.controller;

import com.example.sportstats.service.GetTeamsService;
import com.example.sportstats.service.AddTeamService;
import com.example.sportstats.service.DeleteTeamService;
import com.example.sportstats.service.EditTeamService;
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
public class TeamController {

    @GetMapping("/teams")
    public String getTeams(@RequestParam(required = false) Integer id, @RequestParam(required = false) Integer arenaid, @RequestParam(required = false) Integer seasonid, @RequestParam(required = false) Integer sportid) {

        return new ServiceRunner(new GetTeamsService(id, arenaid, seasonid, sportid)).execute();
    }

    @PostMapping("/teams")
    public String addTeam(@RequestParam String name, @RequestParam Integer sportid, @RequestParam Integer arenaid, @RequestParam Integer createdyear) {

        return new ServiceRunner(new AddTeamService(name, sportid, arenaid, createdyear)).execute();
    }

    @PutMapping("/teams")
    public String editTeam(@RequestParam Integer id, @RequestParam(required = false) String name, @RequestParam(required = false) Integer sportid, @RequestParam(required = false) Integer arenaid, @RequestParam(required = false) Integer createdyear) {

        return new ServiceRunner(new EditTeamService(id, name, sportid, arenaid, createdyear)).execute();
    }

    @DeleteMapping("/teams")
    public String deleteTeam(@RequestParam Integer id) {

        return new ServiceRunner(new DeleteTeamService(id)).execute();
    }
}
