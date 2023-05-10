package com.tsxjava.tsx;


import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommitService{


        private static final Logger logger = LoggerFactory.getLogger(CommitService.class);

        @Value("${github.username}")
        private String username;

        @Value("${github.token}")
        private String token;

        public List<JSONObject> getCommits() throws IOException, InterruptedException {
            HttpClient httpClient = HttpClient.newBuilder()
                 //   .version(HttpClient.Version.HTTP_2)
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.github.com/repos/" + username + "/dummy-github-events/commits"))
                    .GET()
                    .header("Accept", "application/vnd.github.v3+json")
                    .header("Authorization", "token " + token)
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Failed to retrieve commits from Github API: " + response.body());
            }

            JSONArray jsonArray = new JSONArray(response.body());
            List<JSONObject> commits = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                commits.add(jsonArray.getJSONObject(i));
            }
            logger.info("Retrieved {} commits", commits.size());

            return commits;
        }

    }

