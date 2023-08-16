package com.jungmook.blog.Service;

import com.jungmook.blog.Repository.BoardRepository;
import com.jungmook.blog.Repository.PopularSearchRepository;
import com.jungmook.blog.dto.ResponseDto;
import com.jungmook.blog.entity.BoardEntity;
import com.jungmook.blog.entity.PopularSearchEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    PopularSearchRepository popularSearchRepository;

    public ResponseDto<List<BoardEntity>> getTop3(){
        List<BoardEntity> boardList = new ArrayList<BoardEntity>();
        Date date = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));
        try{
            boardList = boardRepository.findTop3ByBoardWriteDateAfterOrderByBoardLikeCountDesc(date);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed("DataBase Error");
        }
        return ResponseDto.setSuccess("Success", boardList);
    }

    public ResponseDto<List<BoardEntity>> getList(){
        List<BoardEntity> boardList = new ArrayList<BoardEntity>();
        try {
            boardList = boardRepository.findByOrderByBoardWriteDateDesc();
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("DataBase Error");
        }

        return ResponseDto.setSuccess("Success", boardList);
    }

    public ResponseDto<List<PopularSearchEntity>> getPopularSearch(){
        List<PopularSearchEntity> popularSearchList = new ArrayList<PopularSearchEntity>();
        try{
            popularSearchList = popularSearchRepository.findTop10ByOrderByPopularSearchCountDesc();
        }catch (Exception e){
            e.printStackTrace();
            ResponseDto.setFailed("DataBase Error");
        }

        return ResponseDto.setSuccess("Success", popularSearchList);
    }
}
