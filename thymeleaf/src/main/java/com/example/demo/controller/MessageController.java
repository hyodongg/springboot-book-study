package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController {
    @GetMapping("/message/basic")
    public String getMassageBasic(){
        return "message/message-basic";
    }
}
