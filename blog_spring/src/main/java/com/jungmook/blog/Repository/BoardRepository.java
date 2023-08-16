package com.jungmook.blog.Repository;

import com.jungmook.blog.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    public List<BoardEntity> findTop3ByBoardWriteDateAfterOrderByBoardLikeCountDesc(Date boardDate);
    public List<BoardEntity> findByOrderByBoardWriteDateDesc();
}
