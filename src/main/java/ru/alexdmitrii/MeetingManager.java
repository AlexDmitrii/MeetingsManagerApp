package ru.alexdmitrii;

import java.util.ArrayList;
import java.util.List;

public class MeetingManager {

    private final List<Meeting> meetings;

    public MeetingManager() {
        this.meetings = new ArrayList<>();
    }

    public boolean addMeeting(Meeting meeting) {
        for (Meeting m : meetings) {
            if (meeting.getStartTime().isBefore(m.getEndTime()) && meeting.getEndTime().isAfter(m.getStartTime())) {
                System.out.println("Встреча пересекается с другой!");
                return false;
            }
        }
        meetings.add(meeting);
        return true;
    }

    public boolean updateMeeting(Meeting oldMeeting, Meeting newMeeting) {
        if (meetings.contains(oldMeeting)) {
            removeMeeting(oldMeeting);
            if (addMeeting(newMeeting)) {
                return true;
            } else {
                meetings.add(oldMeeting);
            }
        }
        return false;
    }

    public void removeMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    public void removeMeeting(String title) {
        meetings.stream().filter(meeting -> meeting.getTitle().equals(title)).findFirst().ifPresent(meetings::remove);
    }

    public Meeting getMeetingByTitle(String title) {
        return meetings.stream().filter(meeting -> meeting.getTitle().equals(title)).findFirst().orElse(null);
    }

    public List<Meeting> getAllMeetings() {
        return meetings;
    }

    public void printMeetings() {
        int count = 1;
        for (Meeting meeting : getAllMeetings()) {
            System.out.println(count++ + ". " + meeting.getTitle()
                    + ": Начало: " + meeting.getStartTime()
                    + ", Конец: " + meeting.getEndTime()
                    + ", Минут до оповещения: "
                    + meeting.getReminderMinutesBefore());
        }
    }
}
