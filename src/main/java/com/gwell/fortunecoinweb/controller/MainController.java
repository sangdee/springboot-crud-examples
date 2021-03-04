package com.gwell.fortunecoinweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : Sangji Lee
 * @see : https://github.com/sangdee
 * @since : 2021-03-04
 */

@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("asdf","ff");
        return "index";
    }
}
