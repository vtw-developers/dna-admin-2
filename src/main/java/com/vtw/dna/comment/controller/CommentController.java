package com.vtw.dna.comment.controller;

import com.vtw.dna.comment.dto.CommentCommand;
import com.vtw.dna.comment.dto.CommentQuery;
import com.vtw.dna.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("comment")
public class CommentController {

    private final CommentService service;

    @GetMapping(value = "list")
    public Map list(Long boardId) throws Exception {
        List<CommentQuery> list = service.list(boardId);
        Map map = new HashMap();
        map.put("list", list);
        map.put("count", service.count(boardId));
        return map;
    }

    @PostMapping(value = "create")
    public CommentCommand create(@Valid @RequestBody CommentCommand entity) throws Exception {
        service.create(entity);
        return entity;
    }

    @PostMapping(value = "update")
    public CommentCommand update(@Valid @RequestBody CommentCommand entity) throws Exception {
        service.update(entity);
        return entity;
    }

    @PostMapping(value = "delete")
    public CommentCommand delete(@Valid @RequestBody CommentCommand entity) throws Exception {
        service.delete(entity);
        return entity;
    }

}
