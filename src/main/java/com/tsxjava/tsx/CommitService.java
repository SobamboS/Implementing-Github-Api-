package com.tsxjava.tsx;


import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public interface CommitService{
    List<JSONObject> listCommits() throws IOException, InterruptedException;



}

