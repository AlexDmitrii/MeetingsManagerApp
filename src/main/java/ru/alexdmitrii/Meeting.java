package ru.alexdmitrii;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Meeting {

    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int reminderMinutesBefore;

}
