package ru.zakharov.market.services;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import ru.zakharov.market.entities.ShopOrder;
import ru.zakharov.market.utils.Mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


@Service
public class EmailService {
    private JavaMailSender emailSender;
    private Configuration freemarkerConfig;

    @Autowired
    public void setEmailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Autowired
    public void setFreemarkerConfig(@Qualifier("getFreeMarkerConfiguration")
                                            Configuration freemarkerConfig) {
        this.freemarkerConfig = freemarkerConfig;
    }

    public void sendSimpleMessage(String mailTo, ShopOrder shopOrder) throws MessagingException, IOException, TemplateException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper mimeHelper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());


        //mimeHelper.addAttachment("logo.png", new ClassPathResource("memorynotfound-logo.png"));
        Template template = freemarkerConfig.getTemplate("order-mail-template.ftl");
        Mail mail = prepareMail(mailTo, "Order Compete", shopOrder);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getModel());

        mimeHelper.setTo(mail.getTo());
        mimeHelper.setText(html, true);
        mimeHelper.setSubject(mail.getSubject());
        mimeHelper.setFrom(mail.getFrom());

        emailSender.send(message);
    }

    private Mail prepareMail(String to, String subject, ShopOrder shopOrder) {
        Mail mail = new Mail();
        mail.setFrom("lexaz77757@gmail.com");
        mail.setTo(to);
        mail.setSubject(subject);
        Map<String, Object> model = new HashMap<>();
        model.put("name", "Market");
        model.put("location", "Krasnodar");
        model.put("shopOrder", shopOrder);
        model.put("itemList",shopOrder.getCartItemList());
        model.put("signature", "127.0.0.1");
        mail.setModel(model);
        return mail;
    }
}