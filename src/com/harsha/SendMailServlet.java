package com.harsha;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SendMailServlet {
	
	@RequestMapping("/")
	public String home()
	{
		return "index";
	}
	@RequestMapping(value="/sendMail",method= RequestMethod.GET)
	public ModelAndView send(HttpServletRequest request)
	{
		String mailToSendMsg=request.getParameter("email");
		String subject = "Testing";
		String msgBody = "Hai this is Harsha testing my project.";
		Properties propertiesobj = new Properties();
		/*propertiesobj.put("mail.smtp.auth", "true"); // authentication purpose
		propertiesobj.put("mail.smtp.host", "smtp.gmail.com"); // only gmail accout is
													// possible
		propertiesobj.put("mail.smtp.port", 587); // default port id for smtp
		propertiesobj.put("mail.smtp.starttls.enable", "true");*/
		Session session = Session.getDefaultInstance(propertiesobj,null);
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("harsha.vardhan@a-cti.com", "Admin"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mailToSendMsg));
			msg.setSubject(subject);
			msg.setText(msgBody);
			Transport.send(msg);
		} catch (IOException e) {
			System.out.println(e);
		} catch (MessagingException e) {
			System.out.println(e);
		}
		return new ModelAndView("index","message","Mail successfully sent.");
	}
}
