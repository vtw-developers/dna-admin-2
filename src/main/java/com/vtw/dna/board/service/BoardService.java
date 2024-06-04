package com.vtw.dna.board.service;

import com.vtw.dna.board.repository.BoardRepository;
import com.vtw.dna.board.dto.BoardCommand;
import com.vtw.dna.board.dto.BoardFilter;
import com.vtw.dna.board.dto.BoardQuery;
import com.vtw.dna.common.rest.EntityAlreadyExistsException;
import com.vtw.dna.common.rest.NoSuchEntityException;
import com.vtw.dna.common.rest.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository repository;

    public Page<BoardQuery> list(BoardFilter filter, Pageable pageable) throws Exception {
        int count = repository.count(filter, pageable);
        List<BoardQuery> list = repository.findAll(filter, pageable);
        Page<BoardQuery> page = Page.<BoardQuery>builder().totalCount(count).data(list).build();
        return page;
    }

    public BoardQuery find(Long id) throws Exception {
        BoardQuery entity = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("Board", id));
        repository.addViewCount(id);
        return entity;
    }

    public void create(BoardCommand entity) throws Exception {
        entity.setBoardNo(calcBoardNo(entity));
        entity.setViewCount(0L);
        if(entity.getParentId() != null) entity.setTitle("RE: " + entity.getTitle());
        repository.insert(entity);
    }

    private Long calcBoardNo(BoardCommand entity) {
        Long boardNo = repository.countByBoardType(entity.getBoardMasterId()) + 1;
        return boardNo;
    }

    public void update(BoardCommand entity) throws Exception {
        find(entity.getId()); // 해당 ID의 Entity가 존재하지 않으면 Exception 발생
        repository.update(entity);
    }

    public void delete(BoardCommand entity) throws Exception {
        repository.delete(entity);
    }

}
