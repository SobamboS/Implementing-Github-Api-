package com.tsxjava.tsx;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/commits")
public class CommitController{

        private final CommitService commitService;

        public CommitController(CommitService commitService) {
            this.commitService = commitService;
        }

        @GetMapping
        public List<JSONObject> getCommits() throws IOException, InterruptedException {
            return commitService.getCommits();
        }
    }

