package com.gamla.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Created by root on 6/2/17.
 */
@Controller
public class HomeController {

    @GetMapping(value="/login")
    public String home(){
        return "almacenHome";
    }

    @GetMapping("/")
    public String login(){ return "login";}

    @GetMapping("/pricing-tables")
    public String pricingTables() {
        return "pricing-tables";
    }

    @GetMapping("/plain-page")
    public String plainPage() {
        return "home";
    }




}
