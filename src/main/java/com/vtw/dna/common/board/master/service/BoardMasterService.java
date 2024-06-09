package com.vtw.dna.common.board.master.service;

import com.vtw.dna.common.board.master.dto.BoardMasterCommand;
import com.vtw.dna.common.board.master.dto.BoardMasterFilter;
import com.vtw.dna.common.board.master.dto.BoardMasterQuery;
import com.vtw.dna.common.board.master.repository.BoardMasterRepository;
import com.vtw.dna.common.exception.EntityAlreadyExistsException;
import com.vtw.dna.common.exception.NoSuchEntityException;
import com.vtw.dna.common.rest.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardMasterService {

    private final BoardMasterRepository repository;

    public Page<BoardMasterQuery> list(BoardMasterFilter filter, Pageable pageable) throws Exception {
        int count = repository.count(filter, pageable);
        List<BoardMasterQuery> list = repository.findAll(filter, pageable);
        Page<BoardMasterQuery> page = Page.<BoardMasterQuery>builder().totalCount(count).data(list).build();
        return page;
    }

    public BoardMasterQuery find(Long id) throws Exception {
        BoardMasterQuery entity = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("BoardMaster", id));
        return entity;
    }

    public void create(BoardMasterCommand entity) throws Exception {
        validate(entity);
        repository.insert(entity);
    }

    public void update(BoardMasterCommand entity) throws Exception {
        find(entity.getId()); // 해당 ID의 Entity가 존재하지 않으면 Exception 발생
        validate(entity);
        repository.update(entity);
    }

    public void delete(BoardMasterCommand entity) throws Exception {
        repository.delete(entity);
    }

    public void validate(BoardMasterCommand entity) {
        boolean existsByName = existsByName(entity.getId(), entity.getName());
        if (existsByName) {
            throw new EntityAlreadyExistsException("BoardMaster", "name", entity.getName());
        }
    }

    public boolean existsByName(Long id, String name) {
        boolean exists = repository.existsByName(id, name);
        return exists;
    }

}
