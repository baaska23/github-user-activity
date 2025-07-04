package org.example;

import java.util.Scanner;

public class UserActivityManagerApplication {
    UserActivityService service = new UserActivityService();

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        UserActivityManagerApplication app = new UserActivityManagerApplication();

        while (true) {
            System.out.println("Enter your command: \n");
            String command = scanner.nextLine();
            try {
                app.readInput(command);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void readInput(String command) throws Exception {
        if (command.startsWith("github-activity")) {
            String[] parts = command.trim().split("\\s+");

            if (parts.length >= 2) {
                String username = parts[1];
                String branch = (parts.length >= 3) ? parts[2] : "events";
                service.displayStat(username, branch);
                service.displayCountByEventType(username, branch, "PushEvent");
                service.displayCountByEventType(username, branch, "IssuesEvent");
                service.displayCountByEventType(username, branch, "WatchEvent");
            } else {
                System.out.println("Alert: github-activity <username> <branch>");
            }
        }
    }
}
