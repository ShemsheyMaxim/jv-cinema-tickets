package com.cinema.service.mapper.impl;

import com.cinema.model.Stage;
import com.cinema.model.dto.StageRequestDto;
import com.cinema.model.dto.StageResponseDto;
import com.cinema.service.mapper.StageMapper;
import org.springframework.stereotype.Component;

@Component
public class StageMapperImpl implements StageMapper {
    @Override
    public StageResponseDto toStageDto(Stage stage) {
        StageResponseDto responseDto = new StageResponseDto();
        responseDto.setId(stage.getId());
        responseDto.setCapacity(stage.getCapacity());
        responseDto.setDescription(stage.getDescription());
        return responseDto;
    }

    @Override
    public Stage toStageEntity(StageRequestDto requestDto) {
        Stage stage = new Stage();
        stage.setCapacity(requestDto.getCapacity());
        stage.setDescription(requestDto.getDescription());
        return stage;
    }
}
