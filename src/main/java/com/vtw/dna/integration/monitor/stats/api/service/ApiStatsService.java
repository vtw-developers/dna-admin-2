package com.vtw.dna.integration.monitor.stats.api.service;

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

    public List<ApiStatsQuery> stats(ApiStatsFilter filter) throws Exception {
        LocalDate startDate = filter.getStartDate();
        LocalDate endDate = filter.getEndDate();

        if (startDate == null) {
            startDate = LocalDate.now().minusYears(1);
        }

        if (endDate == null) {
            endDate = LocalDate.now();
        }

        List<ApiForStats> apis = repository.findAllApi();
        List<ApiStatsQuery> stats = repository.findAll(filter);

        List<ApiStatsQuery> allStats = new ArrayList<>();
        for (ApiForStats api : apis) {
            for (LocalDate date = startDate; (date.isBefore(endDate) || date.isEqual(endDate)); date = date.plusMonths(1)) {
                ApiStatsQuery query = new ApiStatsQuery();
                query.setServiceGroup(api.getServiceGroup());
                query.setApi(api.getApi());
                query.setYear(date.getYear());
                query.setMonth(date.getMonthValue());

                LocalDate finalDate = date;
                ApiStatsQuery apiStat = stats.stream().filter(stat -> stat.getYear() == finalDate.getYear() && stat.getMonth() == finalDate.getMonthValue() && stat.getApi() != null && stat.getApi().equals(api.getApi())).findFirst().orElse(null);
                if (apiStat != null) {
                    query.setCount(apiStat.getCount());
                }
                allStats.add(query);
            }
        }

        return allStats;
    }

}
