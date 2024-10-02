package com.vtw.dna.integration.manage.schedule.quartz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ApplicationService {

    public List<Application> findAll() {
        return List.of(Application.builder().id("Central").build());
    }

}
