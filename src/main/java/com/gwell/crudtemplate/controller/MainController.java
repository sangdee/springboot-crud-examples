package com.gwell.crudtemplate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : Sangji Lee
 * @see : https://github.com/sangdee
 * @since : 2021-03-04
 */

@Controller
public class MainController {

    /**
     * DashBoard Controller
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
