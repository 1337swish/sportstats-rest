package com.example.sportstats.controller;

import com.example.sportstats.service.GetArenasService;
import com.example.sportstats.service.AddArenaService;
import com.example.sportstats.service.DeleteArenaService;
import com.example.sportstats.service.EditArenaService;
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
public class ArenaController {

    @GetMapping("/arenas")
    public String getArenas(@RequestParam(required = false) Integer id) {

        return new ServiceRunner(new GetArenasService(id)).execute();
    }

    @PostMapping("/arenas")
    public String addArena(@RequestParam String name, @RequestParam String address, @RequestParam Integer capacity) {

        return new ServiceRunner(new AddArenaService(name, address, capacity)).execute();
    }

    @PutMapping("/arenas")
    public String editArena(@RequestParam Integer id, @RequestParam(required = false) String name, @RequestParam(required = false) String address, @RequestParam(required = false) Integer capacity) {

        return new ServiceRunner(new EditArenaService(id, name, address, capacity)).execute();
    }

    @DeleteMapping("/arenas")
    public String deleteArena(@RequestParam Integer id) {

        return new ServiceRunner(new DeleteArenaService(id)).execute();
    }
}
