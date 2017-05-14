
package com.seek.api.commandline;

import com.seek.api.dto.UserDTO;
import com.seek.api.service.MailService;
import com.seek.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * initial data at application startup.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Autowired
    MailService mailService;

    @Override
    public void run(String... arg0) throws Exception {
//        addUser();
//        testJobs();
//        mailService.sendMail("ruan.yuji@gmail.com", "hello", "123");
    }

    private void addUser() {
        userService.createUser(new UserDTO("295046974@qq.com", "123", "frank", "ROLE_ADMIN"));
        userService.createUser(new UserDTO("ruan.yuji@gmail.com", "123", "yuji", "ROLE_USER"));
        userService.createUser(new UserDTO("test@gmail.com", "123", "test1", "ROLE_USER"));
        userService.createUser(new UserDTO("review1@gmail.com", "123", "review1", "ROLE_REVIEWER"));
        userService.createUser(new UserDTO("review2@gmail.com", "123", "review2", "ROLE_REVIEWER"));
    }


}