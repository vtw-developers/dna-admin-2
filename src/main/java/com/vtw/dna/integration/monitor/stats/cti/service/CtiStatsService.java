package com.vtw.dna.integration.monitor.stats.cti.service;

import com.vtw.dna.common.rest.NoSuchEntityException;
import com.vtw.dna.integration.monitor.stats.cti.dto.CtiForStats;
import com.vtw.dna.integration.monitor.stats.cti.dto.CtiStatsFilter;
import com.vtw.dna.integration.monitor.stats.cti.dto.CtiStatsQuery;
import com.vtw.dna.integration.monitor.stats.cti.repository.CtiStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CtiStatsService {

    private final CtiStatsRepository repository;

    public List<CtiStatsQuery> list(CtiStatsFilter filter) throws Exception {
        LocalDate startDate = filter.getStartDate();
        LocalDate endDate = filter.getEndDate();

        List<CtiForStats> apis = repository.findAllApi();
        List<CtiStatsQuery> stats = repository.findAll(filter);

        List<CtiStatsQuery> allStats = new ArrayList<>();
        for (CtiForStats api : apis) {
            for (LocalDate date = startDate; (date.isBefore(endDate) || date.isEqual(endDate)); date = date.plusDays(1)) {
                CtiStatsQuery query = new CtiStatsQuery();
                query.setServiceGroup(api.getServiceGroup());
                query.setCti(api.getCti());
                query.setDate(date);

                LocalDate finalDate = date;
                CtiStatsQuery apiStat = stats.stream().filter(stat -> stat.getDate().isEqual(finalDate) && stat.getCti().equals(api.getCti())).findFirst().orElse(null);
                if (apiStat != null) {
                    query.setCount(apiStat.getCount());
                }
                allStats.add(query);
            }
        }

        return allStats;
    }

    public CtiStatsQuery find(Long id) throws Exception {
        CtiStatsQuery entity = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("ApiInfo", id));
        return entity;
    }

}
