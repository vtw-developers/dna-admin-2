package com.vtw.dna.board.repository;

import com.vtw.dna.board.dto.BoardCommand;
import com.vtw.dna.board.dto.BoardFilter;
import com.vtw.dna.board.dto.BoardQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardRepository {
    int count(BoardFilter filter, Pageable pageable);

    Long countByBoardType(Long boardMasterId);

    List<BoardQuery> findAll(BoardFilter filter, Pageable pageable);

    Optional<BoardQuery> findById(Long id);

    int insert(BoardCommand apiInfo);

    int update(BoardCommand apiInfo);

    int delete(BoardCommand apiInfo);
}
