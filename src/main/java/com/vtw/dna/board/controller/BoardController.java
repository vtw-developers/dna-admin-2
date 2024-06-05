package com.vtw.dna.board.controller;

import com.vtw.dna.board.dto.BoardCommand;
import com.vtw.dna.board.dto.BoardFilter;
import com.vtw.dna.board.dto.BoardQuery;
import com.vtw.dna.board.service.BoardService;
import com.vtw.dna.common.rest.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

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

    @GetMapping(value = "download")
    public ResponseEntity download(@RequestParam Long id, HttpServletResponse response) throws Exception {
        return service.download(id, response);
    }

    @GetMapping(value = "remove-file")
    public Long removeFile(@RequestParam Long id) throws Exception {
        service.removeFile(id);
        return id;
    }

    @PostMapping(value = "create")
    public BoardCommand create(@Valid @RequestPart BoardCommand entity, @RequestPart(required = false) List<MultipartFile> files, @RequestPart(required = false) List<String> removeFiles) throws Exception {
        service.create(entity);
        if (files != null) service.upload(entity.getId(), files);
        return entity;
    }

    @PostMapping(value = "update")
    public BoardCommand update(@Valid @RequestPart BoardCommand entity, @RequestPart(required = false) List<MultipartFile> files) throws Exception {
        service.update(entity);
        if (files != null) service.upload(entity.getId(), files);
        return entity;
    }

    @PostMapping(value = "delete")
    public BoardCommand delete(@Valid @RequestBody BoardCommand entity) throws Exception {
        entity.setUseYn(false);
        service.update(entity);
        return entity;
    }

}
