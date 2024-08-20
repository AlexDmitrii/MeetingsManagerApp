package ru.alexdmitrii;

import java.util.Timer;
import java.util.TimerTask;

public class NotificationManager {

    private final Timer timer;

    public NotificationManager() {
        this.timer = new Timer();
    }

    public void scheduleNotification(Meeting meeting) {
        long reminderTime = meeting.getStartTime().minusMinutes(meeting.getReminderMinutesBefore()).toInstant(java.time.ZoneOffset.UTC).toEpochMilli();
        long currentTime = System.currentTimeMillis();

        if (reminderTime > currentTime) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Напоминание: Встреча \"" + meeting.getTitle() + "\" начинается в " + meeting.getStartTime());
                }
            }, reminderTime - currentTime);
        }
    }

}
