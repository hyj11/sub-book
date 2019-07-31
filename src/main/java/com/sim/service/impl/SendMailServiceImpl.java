package com.sim.service.impl;

import com.sim.exception.ServiceException;
import com.sim.model.CommonModel;
import com.sim.service.BookService;
import com.sim.service.SendMailService;
import com.sim.utils.ResponseObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.rmi.server.ServerCloneException;

/**
 * Created with IntelliJ IDEA.
 * User: huangyj
 * 发送邮件
 * Date: 2019/7/25
 */
@Service
public class SendMailServiceImpl implements SendMailService {
    private static final Logger logger = LoggerFactory.getLogger(SendMailServiceImpl.class);
    @Autowired
    private JavaMailSender mailSender;//spring提供的发送类
    @Value("${spring.mail.username}")
    private String userName;

    public void mail() {
        logger.info("发送邮件---");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(userName);//发送者
        mailMessage.setTo(userName);//接收者
        mailMessage.setSubject("邮件测试！");//标题
        mailMessage.setText("测试邮件......");//内容
        try {
            mailSender.send(mailMessage);
        } catch (Exception e) {
            logger.error("发送邮件失败",e);
            throw new ServiceException("发送邮件失败");
        }

    }

}
