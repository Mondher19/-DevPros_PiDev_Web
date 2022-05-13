/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import entities.Commande;
import services.CommandeService;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author moham
 */
public class JavaMailUtils2 {
    public static void sendMail(Commande u) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "abdelkader.selma@esprit.tn";
        //Your gmail password
        String password = "191JFT1632salma";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, u);

        //Send mail
        Transport.send(message);
        
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, Commande u) {
        try {
            Commande user1 = new Commande();
            user1.setEmail(u.getEmail());
            CommandeService commandeService = new CommandeService();
//            User user = userService.rechercherParEmail(user1);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(u.getEmail()));
            message.setSubject("Verification produit");
            String htmlCode = "<h1> Les produits sont bien ajoutés  </h1> <br/> <h2><b> </b></h2>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
