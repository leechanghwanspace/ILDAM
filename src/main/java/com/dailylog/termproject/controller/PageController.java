package com.dailylog.termproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String showMainPage() {
        return "main";
    }

    @RequestMapping(value = "/plan", method = RequestMethod.GET)
    public String showPlanPage() {
        return "plan";
    }

    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    public String showRecommendPage() {
        return "recommend";
    }

}
