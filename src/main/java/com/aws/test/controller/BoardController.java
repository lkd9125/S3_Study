package com.aws.test.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.aws.test.service.BoardService;
import com.aws.test.vo.BoardVO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class BoardController {
    
    private ModelAndView mv;

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/list")
    public ModelAndView list() throws Exception{
        log.info("BoardController - list()");
        mv = new ModelAndView();

        // DB막힘ㅋ
        // ArrayList<BoardVO> sList = boardService.getList();

        // mv.addObject("sList", sList);
       
        mv.setViewName("board/list");

        return mv;
    }

    @GetMapping("/board/insert")
    public String insert(){
        log.info("BoardController - GetInsert()");

        return "/board/insert";
    }

    @PostMapping(value="/board/insert")
    public ModelAndView insert(@ModelAttribute BoardVO vo) throws Exception {

        log.info("BoardController - PostInsert()");
        log.info("username -> {}", vo);
        mv = new ModelAndView();

        boardService.imgInsert(vo);
        
        return mv;
    }
    
}
