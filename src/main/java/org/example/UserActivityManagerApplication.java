package org.example;

import java.util.Arrays;
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

    public void readInput(String command) throws Exception{
        if (command.startsWith("github-activity")){
            String[] parts = command.trim().split("\\s+");
            System.out.println(Arrays.toString(parts));
            if(parts.length >= 2){
                String username = parts[1];
                String branch = (parts.length >= 3) ? parts[2] : "events";
                service.getApi(username, branch);
            } else {
                System.out.println("Try again");
            }
        }
    }
}
