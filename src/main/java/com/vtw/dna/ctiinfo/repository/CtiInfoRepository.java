package com.vtw.dna.ctiinfo.repository;

import com.vtw.dna.ctiinfo.dto.CtiInfoCommand;
import com.vtw.dna.ctiinfo.dto.CtiInfoFilter;
import com.vtw.dna.ctiinfo.dto.CtiInfoQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CtiInfoRepository {
    int count(CtiInfoFilter filter, Pageable pageable);
    List<CtiInfoQuery> findAll(CtiInfoFilter filter, Pageable pageable);
    Optional<CtiInfoQuery> findById(Long id);
    int insert(CtiInfoCommand apiInfo);
    int update(CtiInfoCommand apiInfo);
    int delete(CtiInfoCommand apiInfo);

    boolean existsByName(Long id, String name);
}
