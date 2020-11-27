package com.example.sportstats.controller;

import com.example.sportstats.service.AddSportService;
import com.example.sportstats.service.DeleteSportService;
import com.example.sportstats.service.EditSportService;
import com.example.sportstats.service.GetSportsService;
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
public class SportController {

    @GetMapping("/sports")
    public String getSports() {

        return new ServiceRunner(new GetSportsService()).execute();
    }

    @PostMapping("/sports")
    public String addSport(@RequestParam String name, @RequestParam(required = false) Integer win, @RequestParam(required = false) Integer winot, @RequestParam(required = false) Integer draw, @RequestParam(required = false) Integer loss, @RequestParam(required = false) Integer lossot) {

        return new ServiceRunner(new AddSportService(name, win, winot, draw, loss, lossot)).execute();
    }

    @PutMapping("/sports")
    public String editMatch(@RequestParam Integer id, @RequestParam(required = false) String name, @RequestParam(required = false) Integer win, @RequestParam(required = false) Integer winot, @RequestParam(required = false) Integer draw, @RequestParam(required = false) Integer loss, @RequestParam(required = false) Integer lossot) {

        return new ServiceRunner(new EditSportService(id, name, win, winot, draw, loss, lossot)).execute();
    }

    @DeleteMapping("/sports")
    public String getSports(@RequestParam Integer id) {

        return new ServiceRunner(new DeleteSportService(id)).execute();
    }
}
