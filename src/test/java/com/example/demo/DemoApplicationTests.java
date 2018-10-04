package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() throws Exception{
        Properties pro = new Properties();
        pro.load(DemoApplicationTests.class.getClassLoader().getResourceAsStream("mail.properties"));
        Session session = Session.getDefaultInstance(pro);
        MimeMessage message = new MimeMessage(session);
        //设置发件人
        message.setFrom(new InternetAddress("15970000@163.com","提醒"));
        //内容
        message.setText("恭喜啊啊啊啊");
        //标题
        message.setSubject("小测试");
        //发送给谁
        message.setRecipient(Message.RecipientType.TO,new InternetAddress("11111@qq.com","utf-8"));
        //保存
        message.saveChanges();
        message.setSentDate(new Date());
        //创建运载
        Transport transport = session.getTransport();
        transport.connect(pro.getProperty("mail.username"),pro.getProperty("mail.password"));
        //message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message,message.getAllRecipients());
        transport.close();
    }

}
