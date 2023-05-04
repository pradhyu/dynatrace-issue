package com.example.dynatraceissue;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class Restos {

    @GetMapping("/ping")
    String test() {
      return "pong";
    }
    
}
