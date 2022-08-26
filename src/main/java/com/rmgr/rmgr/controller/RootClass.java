package com.rmgr.rmgr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

import java.util.Map;


@RestController
@SpringBootApplication
public class RootClass {
    @Autowired
    private ClientService clientService;

    @GetMapping("/{name}/{duration}/{numofresources}/")
    public Mono<String> getData(@PathVariable Map<String, String> pathVarsMap) throws Exception {
//        String duration = pathVarsMap.get("duration");
//        String name = pathVarsMap.get("name");
//        String numofresources = pathVarsMap.get("numofresources");

        return clientService.getData(pathVarsMap);

    }

}
