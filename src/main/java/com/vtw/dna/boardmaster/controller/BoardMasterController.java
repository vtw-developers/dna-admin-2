package com.vtw.dna.boardmaster.controller;

import com.vtw.dna.boardmaster.dto.BoardMasterCommand;
import com.vtw.dna.boardmaster.dto.BoardMasterFilter;
import com.vtw.dna.boardmaster.dto.BoardMasterQuery;
import com.vtw.dna.boardmaster.service.BoardMasterService;
import com.vtw.dna.common.rest.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("boardMaster")
public class BoardMasterController {

    private final BoardMasterService service;

    @GetMapping(value = "list")
    public Page<BoardMasterQuery> list(BoardMasterFilter filter, Pageable pageable) throws Exception {
        Page<BoardMasterQuery> page = service.list(filter, pageable);
        return page;
    }

    @GetMapping(value = "find")
    public BoardMasterQuery find(@RequestParam Long id) throws Exception {
        BoardMasterQuery entity = service.find(id);
        return entity;
    }

    @PostMapping(value = "create")
    public BoardMasterCommand create(@Valid @RequestBody BoardMasterCommand entity) throws Exception {
        service.create(entity);
        return entity;
    }

    @PostMapping(value = "update")
    public BoardMasterCommand update(@Valid @RequestBody BoardMasterCommand entity) throws Exception {
        service.update(entity);
        return entity;
    }

    @PostMapping(value = "delete")
    public BoardMasterCommand delete(@Valid @RequestBody BoardMasterCommand entity) throws Exception {
        service.delete(entity);
        return entity;
    }

}
