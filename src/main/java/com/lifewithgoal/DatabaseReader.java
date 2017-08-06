package com.lifewithgoal;

import com.lifewithgoal.entity.Email;
import com.lifewithgoal.entity.Notification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by darshanas on 8/6/2017.
 */
public class DatabaseReader implements Runnable{

    private final static Logger logger = LogManager.getLogger(DatabaseReader.logger);
    private final Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public void run() {
        startProcess();
    }

    private void startProcess(){
        while (true){
            List<Notification> notifications = getPendingNotifications();
            if(notifications != null && !notifications.isEmpty()){
                logger.info("Sending " + notifications.size() + " new messages.");
                for(Notification notification : notifications){
                    boolean state = EmailSender.sendEmail(new Email(notification.getTo(),notification.getSubject(),
                            notification.getMessage()));
                    if(state){
                        updateState(notification,2);
                    }else {
                        updateState(notification,1);
                    }
                }
            }else {
                logger.info("No Message Found, waiting for new messages.");
                try {
                    Thread.sleep(25000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private List<Notification> getPendingNotifications(){

        session.beginTransaction();
        List<Notification> notifications = session.createQuery("FROM Notification where delivery_state != 2 and " +
                "fail_attempts < 3").list();
        session.getTransaction().commit();
        return notifications;

    }

    private void updateState(Notification notification, int state){

        session.beginTransaction();
        Query query = session.createQuery("update Notification set delivery_state = :delivery_state, " +
                "fail_attempts = :fail_attempts, delivery_completed_time = :delivery_completed_time" +
                " where id = :id");
        query.setParameter("id",notification.getId());
        query.setParameter("delivery_state", state);

        if(state == 1){
            // update fail attempts
            int failAttempts = notification.getFail_attempts() + 1;
            query.setParameter("fail_attempts",failAttempts);
            query.setParameter("delivery_completed_time",null);
        }else {
            query.setParameter("fail_attempts",notification.getFail_attempts());
            query.setParameter("delivery_completed_time", new Date());
        }

        query.executeUpdate();
        session.getTransaction().commit();
        logger.info("Update Successfull for entity " + notification.getId());

    }

}
