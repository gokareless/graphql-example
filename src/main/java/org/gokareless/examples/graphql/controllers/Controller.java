package org.gokareless.examples.graphql.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Controller {

    @GetMapping
    public ResponseEntity<String> get() {
        System.out.println("Request. Inside controller");
        return ResponseEntity.ok().body("Hello!");
    }
}
