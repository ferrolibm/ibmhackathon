package com.langiappeworkshop.autosafedemo;


public class MailSender {
//    public String jobNo;
//    public String teamNo;
//    private static final String username = "mail@gmail.com";
//    private static final String password = "123456";
//    private static final String emailid = "mail2@outlook.com";
//    private static final String subject = "Photo";
//    private static final String message = "Hello";
//    private Multipart multipart = new MimeMultipart();
//    private MimeBodyPart messageBodyPart = new MimeBodyPart();
//    public File mediaFile;
//
//
//    private void sendMail(String email, String subject, String messageBody) {
//        Session session = createSessionObject();
//
//        try {
//            Message message = createMessage(email, subject, messageBody, session);
//            new SendMailTask().execute(message);
//        } catch (AddressException e) {
//            e.printStackTrace();
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private Session createSessionObject() {
//        Properties properties = new Properties();
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port", "587");
//
//        return Session.getInstance(properties, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//        });
//    }
//
//    private Message createMessage(String email, String subject, String messageBody, Session session) throws
//            MessagingException, UnsupportedEncodingException {
//        Message message = new MimeMessage(session);
//        message.setFrom(new InternetAddress("mail2@outlook.com", "Naveed Qureshi"));
//        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email, email));
//        message.setSubject(subject);
//        message.setText(messageBody);
//        return message;
//    }
//
//
//    public class SendMailTask extends AsyncTask<Message, Void, Void> {
//        private ProgressDialog progressDialog;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progressDialog = ProgressDialog.show(Mailing.this, "Please wait", "Sending mail", true, false);
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            progressDialog.dismiss();
//        }
//
//        protected Void doInBackground(javax.mail.Message... messages) {
//            try {
//                Transport.send(messages[0]);
//            } catch (MessagingException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }
}
