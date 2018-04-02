package com.example.demo.controller;

import com.example.demo.WordLadder;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
public class UserController {
    WordLadder wl = new WordLadder();

    @GetMapping("/wordladder")
    @RequestMapping(value="/wordladder", method = RequestMethod.GET)
    public String helloworld(@RequestParam String w1, @RequestParam String w2) {
        Set<String> dic = new HashSet<String>();
        wl.getKeyWords(dic);
        return wl.getLadder(w1, w2, dic);
    }
}
