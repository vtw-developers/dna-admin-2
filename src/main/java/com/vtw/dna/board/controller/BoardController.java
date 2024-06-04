package com.vtw.dna.board.controller;

import com.vtw.dna.board.dto.BoardCommand;
import com.vtw.dna.board.dto.BoardFilter;
import com.vtw.dna.board.dto.BoardQuery;
import com.vtw.dna.board.service.BoardService;
import com.vtw.dna.common.rest.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("board")
public class BoardController {

    private final BoardService service;

    @GetMapping(value = "list")
    public Page<BoardQuery> list(BoardFilter filter, Pageable pageable) throws Exception {
        Page<BoardQuery> page = service.list(filter, pageable);
        return page;
    }

    @GetMapping(value = "find")
    public BoardQuery find(@RequestParam Long id) throws Exception {
        BoardQuery entity = service.find(id);
        return entity;
    }

    @PostMapping(value = "create")
    public BoardCommand create(@Valid @RequestBody BoardCommand entity) throws Exception {
        service.create(entity);
        return entity;
    }

    @PostMapping(value = "update")
    public BoardCommand update(@Valid @RequestBody BoardCommand entity) throws Exception {
        service.update(entity);
        return entity;
    }

    @PostMapping(value = "delete")
    public BoardCommand delete(@Valid @RequestBody BoardCommand entity) throws Exception {
        service.delete(entity);
        return entity;
    }

}
