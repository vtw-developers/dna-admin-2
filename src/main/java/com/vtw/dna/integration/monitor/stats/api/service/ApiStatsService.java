package com.vtw.dna.integration.monitor.stats.api.service;

import com.vtw.dna.common.exception.NoSuchEntityException;
import com.vtw.dna.integration.monitor.stats.api.dto.ApiForStats;
import com.vtw.dna.integration.monitor.stats.api.dto.ApiStatsFilter;
import com.vtw.dna.integration.monitor.stats.api.dto.ApiStatsQuery;
import com.vtw.dna.integration.monitor.stats.api.repository.ApiStatsRepository;
import lombok.RequiredArgsConstructor;
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
        List<ApiStatsQuery> stats = repository.findAll(filter);

        List<ApiStatsQuery> allStats = new ArrayList<>();
        for (ApiForStats api : apis) {
            for (LocalDate date = startDate; (date.isBefore(endDate) || date.isEqual(endDate)); date = date.plusDays(1)) {
                ApiStatsQuery query = new ApiStatsQuery();
                query.setServiceGroup(api.getServiceGroup());
                query.setApi(api.getApi());
                query.setDate(date);

                LocalDate finalDate = date;
                ApiStatsQuery apiStat = stats.stream().filter(stat -> stat.getDate().isEqual(finalDate) && stat.getApi() != null && stat.getApi().equals(api.getApi())).findFirst().orElse(null);
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
