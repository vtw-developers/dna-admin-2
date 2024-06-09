package com.vtw.dna.common.board.comment.service;

import com.vtw.dna.common.board.comment.dto.CommentCommand;
import com.vtw.dna.common.board.comment.dto.CommentQuery;
import com.vtw.dna.common.board.comment.repository.CommentRepository;
import com.vtw.dna.common.exception.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository repository;

    public List<CommentQuery> list(Long boardId) throws Exception {
        List<CommentQuery> list = repository.findAll(boardId);
        return list;
    }

    public CommentQuery find(Long id) throws Exception {
        CommentQuery entity = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("Comment", id));
        return entity;
    }

    public void create(CommentCommand entity) throws Exception {
        entity.setCommentNo(calcCommentNo(entity.getBoardId()));
        repository.insert(entity);
    }

    public Long count(Long boardId) {
        Long commentNo = repository.count(boardId);
        return commentNo;
    }

    private Long calcCommentNo(Long boardId) {
        Long commentNo = repository.countByBoardId(boardId) + 1;
        return commentNo;
    }

    public void update(CommentCommand entity) throws Exception {
        find(entity.getId()); // 해당 ID의 Entity가 존재하지 않으면 Exception 발생
        repository.update(entity);
    }

    public void delete(CommentCommand entity) throws Exception {
        entity.setUseYn(false);
        repository.delete(entity);
    }

}
