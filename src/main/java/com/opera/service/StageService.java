package com.opera.service;

import com.opera.model.Stage;
import java.util.List;

public interface StageService {
    Stage add(Stage stage);

    List<Stage> getAll();

    Stage get(Long stageId);
}
