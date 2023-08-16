package com.jungmook.blog.Controller;

import com.jungmook.blog.Service.BoardService;
import com.jungmook.blog.dto.ResponseDto;
import com.jungmook.blog.entity.BoardEntity;
import com.jungmook.blog.entity.PopularSearchEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    @Autowired
    BoardService boardService;
    @GetMapping("/top3")
    public ResponseDto<List<BoardEntity>> getTop3(){
        return boardService.getTop3();
    }

    @GetMapping("/list")
    public ResponseDto<List<BoardEntity>> getList(){
        return boardService.getList();
    }

    @GetMapping("/popularSearchList")
    public ResponseDto<List<PopularSearchEntity>> getPopularSearchList(){
        return boardService.getPopularSearch();
    }
}
