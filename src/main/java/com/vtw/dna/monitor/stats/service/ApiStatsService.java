package com.vtw.dna.monitor.stats.service;

import com.vtw.dna.apiinfo.repository.ApiInfoRepository;
import com.vtw.dna.common.rest.NoSuchEntityException;
import com.vtw.dna.common.rest.Page;
import com.vtw.dna.monitor.stats.dto.ApiForStats;
import com.vtw.dna.monitor.stats.dto.ApiStatsFilter;
import com.vtw.dna.monitor.stats.dto.ApiStatsQuery;
import com.vtw.dna.monitor.stats.repository.ApiStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ApiStatsService {

    private final ApiStatsRepository repository;

    public List<ApiStatsQuery> list(ApiStatsFilter filter) throws Exception {
        LocalDate startDate = filter.getStartDate();
        LocalDate endDate = filter.getEndDate();

        List<ApiForStats> apis = repository.findAllApi();
        List<ApiStatsQuery> stats = repository.findAll();

        List<ApiStatsQuery> allStats = new ArrayList<>();
        for (ApiForStats api : apis) {
            for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
                ApiStatsQuery query = new ApiStatsQuery();
                query.setServiceGroup(api.getServiceGroup());
                query.setApi(api.getApi());
                query.setDate(date);

                LocalDate finalDate = date;
                ApiStatsQuery apiStat = stats.stream().filter(stat -> stat.getDate().isEqual(finalDate) && stat.getApi().equals(api.getApi())).findFirst().orElse(null);
                if (apiStat != null) {
                    query.setCount(apiStat.getCount());
                }
                allStats.add(query);
            }
        }

        return allStats;
    }

    public ApiStatsQuery find(Long id) throws Exception {
        ApiStatsQuery entity = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("ApiInfo", id));
        return entity;
    }

}
