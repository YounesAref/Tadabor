package com.younesaref.habittracker.DTO;

import javax.annotation.processing.Completion;
import java.time.LocalDate;
import java.util.List;

public record HabitCompletionHistoryResponse(
        Long habitId,
        List<LocalDate> completions
) {
}
