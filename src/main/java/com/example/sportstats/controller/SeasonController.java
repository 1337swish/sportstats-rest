package com.example.sportstats.controller;

import com.example.sportstats.service.AddSeasonService;
import com.example.sportstats.service.DeleteSeasonService;
import com.example.sportstats.service.EditSeasonService;
import com.example.sportstats.service.GetSeasonsService;
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
public class SeasonController {

    @GetMapping("/seasons")
    public String getSeasons(@RequestParam(required = false) Integer id, @RequestParam(required = false) Integer leagueid) {

        return new ServiceRunner(new GetSeasonsService(id, leagueid)).execute();
    }

    @PostMapping("/seasons")
    public String addSeason(@RequestParam Integer leagueid, @RequestParam Integer startyear, @RequestParam Integer endyear, @RequestParam Integer rounds, @RequestParam(defaultValue = "false") Boolean autoaddrounds) {

        return new ServiceRunner(new AddSeasonService(leagueid, startyear, endyear, rounds, autoaddrounds)).execute();
    }
    
    @PutMapping("/seasons")
    public String editSeason(@RequestParam Integer id, @RequestParam(required = false) Integer leagueid, @RequestParam(required = false) Integer startyear, @RequestParam(required = false) Integer endyear, @RequestParam(required = false) Integer rounds) {

        return new ServiceRunner(new EditSeasonService(id, leagueid, startyear, endyear, rounds)).execute();
    }
    
    @DeleteMapping("/seasons")
    public String deleteSeason(@RequestParam Integer id) {

        return new ServiceRunner(new DeleteSeasonService(id)).execute();
    }
}
