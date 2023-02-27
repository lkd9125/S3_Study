package com.aws.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TestController {
    
    private ModelAndView mv;

    @GetMapping("/test/jquerytable/list")
    public ModelAndView list(){
        log.info("TestController - list()");
        mv = new ModelAndView();        

        mv.setViewName("test/jquerytable/list");

        return mv;
    }   
}
