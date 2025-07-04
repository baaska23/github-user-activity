package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.*;

public class UserActivityService {
    public String fetchAPI(String username, String branch) throws IOException, InterruptedException {
        username = username.trim();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/users/" + username + "/" + branch))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public void displayStat(String username, String branch) throws Exception {
        String fetchedBody = fetchAPI(username, branch);

        if ("events".equals(branch)) {
            JSONArray arr = new JSONArray(fetchedBody);
            List<String> allPushEvents = new ArrayList<>();
            List<String> allIssuesEvents = new ArrayList<>();
            List<String> allWatchEvents = new ArrayList<>();
            String repoUrl = "";
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                String event = obj.getString("type");
                JSONObject repoObj = obj.getJSONObject("repo");
                String repo = repoObj.getString("name");

                JSONObject payload = obj.getJSONObject("payload");
                if (payload.has("issue")) {
                    JSONObject issue = payload.getJSONObject("issue");
                    repoUrl = issue.getString("repository_url");
                }

                if ("PushEvent".equals(event)) {
                    allPushEvents.add(repo);
                } else if ("IssuesEvent".equals(event)) {
                    allIssuesEvents.add(repo);
                } else if ("WatchEvent".equals(event)) {
                    allWatchEvents.add(repo);
                }
            }

            int count = 0;
            repoUrl = repoUrl.substring(repoUrl.lastIndexOf("/") + 1);
            int issueSize = allIssuesEvents.size();
            String lastWatchEvent = allWatchEvents.getFirst();
            String lastPushEvent = allPushEvents.getFirst();

            if (lastPushEvent != null) {
                for (String event : allPushEvents) {
                    if (event.equals(lastPushEvent)) {
                        count++;
                    }
                }
            }
            System.out.println(username + " pushed " + count + " commits to " + lastPushEvent);
            System.out.println(username + " opened " + issueSize + " issue in " + repoUrl);
            System.out.println(username + " starred " + String.join(", ", lastWatchEvent) + " repos");
        } else if ("following".equals(branch)) {
            JSONArray arr = new JSONArray(fetchedBody);
            List<String> allLogins = new ArrayList<>();
            for (int i = 0; i < arr.length(); i++) {
                JSONObject user = arr.getJSONObject(i);
                String login = user.getString("login");
                allLogins.add(login);
            }
            System.out.println(username + " follows " + String.join(", ", allLogins));
        } else if ("repos".equals(branch)) {
            JSONArray arr = new JSONArray(fetchedBody);
            List<String> allRepos = new ArrayList<>();
            for (int i = 0; i < arr.length(); i++) {
                JSONObject repo = arr.getJSONObject(i);
                String repoStr = repo.getString("name");
                allRepos.add(repoStr);
            }
            System.out.println(username + " has repositories of " + String.join(", ", allRepos));
        }
    }
    public void displayCountByEventType(String username, String branch, String eventType) throws IOException, InterruptedException {
        String fetchedBody = fetchAPI(username, branch);

        int count = 0;
        if(eventType != null){
            JSONArray arr = new JSONArray(fetchedBody);
            for(int i=0; i<arr.length(); i++){
                JSONObject type = arr.getJSONObject(i);
                String event = type.getString("type");
                if(eventType.equals(event)){
                    count++;
                }
            }
        }
        System.out.println("There are " + count + " " + eventType + " activity");
    }
}