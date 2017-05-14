package com.seek.api.controllers;

import com.seek.api.config.FoundITConfig;
import com.seek.api.model.Job;
import com.seek.api.model.Reviewer;
import com.seek.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by langley on 8/5/17.
 */
@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final UserService userService;

    @Autowired
    private FoundITConfig foundITConfig;

    @Autowired
    public JobController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllJobs(@RequestParam(value = "title", required = false) String title) {
        String url = foundITConfig.getJobServiceHost() + "/api/jobs";
        if (title != null) {
            url += "/?title=" + title;
        }

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Job[]> responseEntity = restTemplate.getForEntity(url, Job[].class);

//        List<Job> jobs = new ArrayList<>();
//        if (title != null && !title.isEmpty()) {
//            jobs = jobService.findJobByName(title);
//        } else {
//            jobs = jobService.findAllJob();
//        }
        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {

        String url = foundITConfig.getJobServiceHost() + "/api/jobs/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Job> responseEntity = restTemplate.getForEntity(url, Job.class);
//        Job job = jobService.findJobByID(id);
        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Job> addNewJob(@RequestBody Job job) {
        // Get user from token
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        job.setPublisher(username);

        for (Reviewer reviewer : job.getReviewers()) {
            reviewer.setJob(job);
        }

        String url = foundITConfig.getJobServiceHost() + "/api/jobs";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Job> responseEntity = restTemplate.postForEntity(url, job, Job.class);

//        jobService.addJob(job);
        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Job> updateJob(@RequestBody Job job) {

        String url = foundITConfig.getJobServiceHost() + "/api/jobs";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(url, job);

//        jobService.updateJob(job);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePostById( @PathVariable Long id) {

        String url = foundITConfig.getJobServiceHost() + "/api/jobs/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url);

//        jobService.deleteJob(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
