package com.lifewithgoal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 * Created by darshanas on 8/6/2017.
 */
public class StartService {

    private static final Logger logger = LogManager.getLogger(StartService.class);
    final static String userName = "no-reply@lifewithgoals.com";
    final static String password = "123456789";

    public static void main(String[] args) {

      logger.info("Starting Notification Service");
       new Thread(new DatabaseReader()).start();

    }


}
