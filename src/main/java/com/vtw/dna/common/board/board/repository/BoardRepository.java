package com.vtw.dna.common.board.board.repository;

import com.vtw.dna.common.board.board.dto.BoardCommand;
import com.vtw.dna.common.board.board.dto.BoardFile;
import com.vtw.dna.common.board.board.dto.BoardFilter;
import com.vtw.dna.common.board.board.dto.BoardQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardRepository {
    int count(BoardFilter filter, Pageable pageable);

    Long countByBoardType(Long boardMasterId);

    List<BoardFile> selectFileList(Long boardId);

    BoardFile selectFile(Long boardId);

    void removeFile(Long id);

    List<BoardQuery> findAll(BoardFilter filter, Pageable pageable);

    List<BoardQuery> findPinAll(Long boardMasterId);

    List<BoardQuery> findPopupAll();

    Optional<BoardQuery> findById(Long id);

    void addViewCount(Long id);

    int insert(BoardCommand board);

    int upload(BoardFile file);

    int update(BoardCommand board);

    int delete(BoardCommand board);

    List<BoardQuery> findAllByParentId(Long parentId);
}
