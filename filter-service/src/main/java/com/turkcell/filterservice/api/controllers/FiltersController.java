package com.turkcell.filterservice.api.controllers;

import com.turkcell.filterservice.business.abstracts.FilterService;
import com.turkcell.filterservice.business.dto.responses.GetAllFiltersResponse;
import com.turkcell.filterservice.business.dto.responses.GetFilterResponse;
import com.turkcell.filterservice.entities.Filter;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/filters")
public class FiltersController {
    private final FilterService service;

    @PostConstruct
    public void createDb() {
        service.add(new Filter());
    }

    @GetMapping
    public List<GetAllFiltersResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetFilterResponse getByIId(@PathVariable UUID id) {
        return service.getById(id);
    }
}
