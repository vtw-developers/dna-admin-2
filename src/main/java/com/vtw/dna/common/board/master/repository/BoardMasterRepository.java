package com.vtw.dna.common.board.master.repository;

import com.vtw.dna.common.board.master.dto.BoardMasterCommand;
import com.vtw.dna.common.board.master.dto.BoardMasterFilter;
import com.vtw.dna.common.board.master.dto.BoardMasterQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMasterRepository {
    int count(BoardMasterFilter filter, Pageable pageable);

    List<BoardMasterQuery> findAll(BoardMasterFilter filter, Pageable pageable);

    Optional<BoardMasterQuery> findById(Long id);

    int insert(BoardMasterCommand apiInfo);

    int update(BoardMasterCommand apiInfo);

    int delete(BoardMasterCommand apiInfo);

    boolean existsByName(Long id, String name);
}
