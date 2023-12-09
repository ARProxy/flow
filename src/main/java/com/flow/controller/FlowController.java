package com.flow.controller;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@NoArgsConstructor
@RequestMapping("/")
public class FlowController {

    @GetMapping(value = "")
    public String flowTest() {
        return "main";
    }
}
