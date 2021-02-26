package com.cinema.dao;

import com.cinema.model.Stage;
import java.util.List;
import java.util.Optional;

public interface StageDao {
    Stage add(Stage stage);

    List<Stage> getAll();

    Optional<Stage> get(Long stageId);
}
