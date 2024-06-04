package com.vtw.dna.comment.repository;

import com.vtw.dna.comment.dto.CommentCommand;
import com.vtw.dna.comment.dto.CommentQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CommentRepository {
    Long count(Long boardId);

    Long countByBoardId(Long commentMasterId);

    List<CommentQuery> findAll(Long boardId);

    Optional<CommentQuery> findById(Long id);

    int insert(CommentCommand comment);

    int update(CommentCommand comment);

    int delete(CommentCommand comment);
}
