package io.github.bo712.sber_test.controller;

import io.github.bo712.sber_test.Incrementer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {

    private final Incrementer incremener;

    public MyController(Incrementer incrementer) {
        this.incremener = incrementer;
    }

    @PostMapping("/incrementNumber")
    public int incrementNumber() {
        incremener.incrementNumber();
        return incremener.getNumber();
    }

    @GetMapping("/getNumber")
    public int getNumber() {
        return incremener.getNumber();
    }
}
