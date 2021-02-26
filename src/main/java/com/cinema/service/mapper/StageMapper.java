package com.cinema.service.mapper;

import com.cinema.model.Stage;
import com.cinema.model.dto.StageRequestDto;
import com.cinema.model.dto.StageResponseDto;

public interface StageMapper {
    StageResponseDto toStageDto(Stage stage);

    Stage toStageEntity(StageRequestDto requestDto);
}
