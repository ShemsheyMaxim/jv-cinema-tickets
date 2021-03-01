package com.opera.service.mapper;

import com.opera.model.Stage;
import com.opera.model.dto.StageRequestDto;
import com.opera.model.dto.StageResponseDto;

public interface StageMapper {
    StageResponseDto toStageDto(Stage stage);

    Stage toStageEntity(StageRequestDto requestDto);
}
