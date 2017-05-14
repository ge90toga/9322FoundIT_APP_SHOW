package com.seek.api.controllers;

import com.seek.api.config.FoundITConfig;
import com.seek.api.dto.ApplicationDTO;
import com.seek.api.model.Application;
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
@RequestMapping("/api/apply")
public class ApplyController {

    private final UserService userService;

    @Autowired
    private FoundITConfig foundITConfig;

    @Autowired
    public ApplyController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllApplications() {

        String url = foundITConfig.getJobServiceHost() + "/api/apply";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Application[]> responseEntity = restTemplate.getForEntity(url, Application[].class);

//        List<Application> applications =  jobService.findAllApplication();
        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Application> getApplicationById(@PathVariable Long id) {

        String url = foundITConfig.getJobServiceHost() + "/api/apply/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Application> responseEntity = restTemplate.getForEntity(url, Application.class);

//        Application application = jobService.findApplicationByID(id);
        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Application> addNewApplication(@RequestBody Application application) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        application.setUserID(username);
        application.setEmail(username);

        String url = foundITConfig.getJobServiceHost() + "/api/apply";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Application> responseEntity = restTemplate.postForEntity(url, application, Application.class);

//        jobService.addApplication(application);
        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Application> updateApplication(@RequestBody Application application) {
        String url = foundITConfig.getJobServiceHost() + "/api/apply";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(url, application);

//        jobService.updateApplication(application);
        return new ResponseEntity<>(application, HttpStatus.OK);
    }

    @RequestMapping(value = "/combo", method = RequestMethod.GET)
    public ResponseEntity<?> getAllApplicationCombo() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        String url = foundITConfig.getJobServiceHost() + "/api/apply/combo/?username=" + username;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ApplicationDTO[]> responseEntity = restTemplate.getForEntity(url, ApplicationDTO[].class);

//        List<ApplicationDTO> applicationDTOS = jobService.findApplicationComboByPublisher(username);
        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public ResponseEntity<?> getMyApplications() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        String url = foundITConfig.getJobServiceHost() + "/api/apply/my/?username=" + username;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Application[]> responseEntity = restTemplate.getForEntity(url, Application[].class);

//        List<Application> applications =  jobService.findApplicationByApplicant(username);
        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
    }

}
