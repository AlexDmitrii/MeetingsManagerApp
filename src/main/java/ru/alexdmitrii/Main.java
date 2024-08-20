package ru.alexdmitrii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class Main {
    private static final MeetingManager meetingManager = new MeetingManager();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command;

        while (true) {
            System.out.println("Введите команду (add, update, remove, list, exit): ");
            command = reader.readLine();
            String[] argsCommand = command.split(" ");
            switch (argsCommand[0]) {
                case "add" -> {
                    Meeting newMeeting = parseMeeting(argsCommand);
                    meetingManager.addMeeting(newMeeting);
                }
                case "update" -> {
                    if (argsCommand.length > 5) {
                        String oldMeetingTitle = argsCommand[5];
                        Meeting oldMeeting = meetingManager.getMeetingByTitle(oldMeetingTitle);
                        Meeting updatedMeeting = parseMeeting(argsCommand);

                        meetingManager.updateMeeting(oldMeeting, updatedMeeting);
                    }
                }
                case "remove" -> {
                    if (args.length > 1) {
                        String meetingTitle = args[1];
                        meetingManager.removeMeeting(meetingTitle);
                    }
                }
                case "list" -> meetingManager.printMeetings();
                case "exit" -> System.exit(0);
                default -> System.out.println("Неизвестная команда. Попробуйте снова.");
            }
        }
    }

    public static Meeting parseMeeting(String[] command) {
        if (command.length < 4) {
            System.out.println("Недостаточно аргументов, пример: add \"Title\" 2023-03-01T10:00 2023-03-01T11:00 15");
            return null;
        }

        String title = command[1];
        LocalDateTime meetingStart = LocalDateTime.parse(command[2]);
        LocalDateTime meetingEnd = LocalDateTime.parse(command[3]);
        int reminderTime = command.length > 4 ? Integer.parseInt(command[4]) : -1;

        return new Meeting(title, meetingStart, meetingEnd, reminderTime);
    }
}