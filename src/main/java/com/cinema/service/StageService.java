package com.cinema.service;

import com.cinema.model.Stage;
import java.util.List;

public interface StageService {
    Stage add(Stage stage);

    List<Stage> getAll();

    Stage get(Long stageId);
}
