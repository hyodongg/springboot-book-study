package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController {
    @GetMapping("/message/basic")
    public String getMassageBasic(){
        return "message/message-basic";
    }

    @GetMapping("/message/customer")
    public String getMassageCustomer(Model model){
        model.addAttribute("name","조효동");
        model.addAttribute("phone","010-3223-9978");
        return "message/message-customer";
    }

    @GetMapping("/message/key")
    public String getMessageKey(Model model){
        model.addAttribute("contactForm","customer.contact.short");
        model.addAttribute("name","조효동");
        model.addAttribute("phone","010-3223-9978");
        return "message/message-key";
    }
}
