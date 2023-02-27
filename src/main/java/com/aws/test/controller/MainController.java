package com.aws.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {
    private ModelAndView mv;

    @GetMapping("/")
    public ModelAndView index(){
        log.info("MainController - index()");
        mv = new ModelAndView();

        mv.setViewName("main/index");
        return mv;
    }
}
