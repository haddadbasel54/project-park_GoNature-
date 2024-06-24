package neededclasses;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.search.FlagTerm;

/**
 * The GmailEmailSender class provides methods to send emails using Gmail SMTP
 * server and fetch replies from the inbox using IMAP.
 */
public class GmailEmailSender {

	/**
	 * Sends an email using Gmail SMTP server.
	 *
	 * @param receiverEmail The email address of the recipient.
	 * @param subject       The subject of the email.
	 * @param messageBody   The body of the email.
	 */
	public static void sendEmail(String receiverEmail, String subject, String messageBody) {

		// SMTP server configuration
		String senderEmail = "gonature82@gmail.com";
		String senderPassword = "qcsj ziuy njuj jthk";
		String smtpHost = "smtp.gmail.com";
		String smtpPort = "465";
		// Set properties for SMTP
		Properties properties = System.getProperties();
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		properties.setProperty("mail.smtp.host", smtpHost);
		properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		properties.setProperty("mail.smtp.socketFactory.fallback", "false");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.port", smtpPort);
		properties.setProperty("mail.smtp.socketFactory.port", smtpPort);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.store.protocol", "pop3");
		properties.put("mail.transport.protocol", "smtp");
		System.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		// Create session with authentication
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, senderPassword);
			}
		});

		try {
			// Create a MimeMessage object
			Message message = new MimeMessage(session);
			session.setDebug(true);
			// Set sender and recipient addresses
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));

			// Set email subject and message body
			message.setSubject(subject);
			message.setText(messageBody);

			// Send the email
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
			System.err.println("Failed to send email to " + receiverEmail + ": " + e.getMessage());
		}
	}

	/**
	 * Fetches the email addresses of the senders of the replies received in the
	 * last two hours from the Gmail inbox.
	 *
	 * @return A list of email addresses of the senders of the replies.
	 */
	public static List<String> fetchReplies() {
		List<String> replyEmails = new ArrayList<>();
		String username = "gonature82@gmail.com";
		String password = "qcsj ziuy njuj jthk"; // Placeholder, don't store password in plain text
		String host = "imap.gmail.com";
		int port = 995; // Use port 995 for POP3
		Properties properties = new Properties();
		properties.setProperty("mail.store.protocol", "imaps");
		Session session = Session.getDefaultInstance(properties);
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime twoHoursAgo = now.minusHours(2);

		try {
			Store store = session.getStore("imaps");
			store.connect(host, username, password);
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_WRITE);
			Flags primaryFlag = new Flags("Primary");
			Message[] messages = emailFolder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

			for (Message message : messages) {
				System.out.println(message.getReceivedDate());
				if (message.getReceivedDate().toInstant()
						.isAfter(twoHoursAgo.atZone(ZoneId.systemDefault()).toInstant())) {
					// Get the sender's email address
					replyEmails.add(((InternetAddress) message.getFrom()[0]).getAddress());
					message.setFlag(Flags.Flag.SEEN, true);
				}
			}
			emailFolder.close(true);
			store.close();
		} catch (Exception e) {
		}

		return replyEmails;
	}
}
