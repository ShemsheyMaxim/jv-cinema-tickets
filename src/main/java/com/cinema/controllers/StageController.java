package com.cinema.controllers;

import com.cinema.model.Stage;
import com.cinema.model.dto.StageRequestDto;
import com.cinema.model.dto.StageResponseDto;
import com.cinema.service.StageService;
import com.cinema.service.mapper.StageMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stages")
public class StageController {
    private final StageMapper stageMapper;
    private final StageService stageService;

    @Autowired
    public StageController(StageMapper stageMapper,
                           StageService stageService) {
        this.stageMapper = stageMapper;
        this.stageService = stageService;
    }

    @PostMapping
    public void create(@RequestBody @Valid StageRequestDto stageRequestDto) {
        Stage stage = stageMapper.toStageEntity(stageRequestDto);
        stageService.add(stage);
    }

    @GetMapping
    public List<StageResponseDto> getAll() {
        return stageService.getAll()
                .stream()
                .map(stageMapper::toStageDto)
                .collect(Collectors.toList());
    }
}
