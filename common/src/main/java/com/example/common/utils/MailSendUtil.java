package com.example.common.utils;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

/**
 * @author hyosunghan
 * @since 2019-09-12
 */
public class MailSendUtil {
    private final static String host = "smtp.qq.com"; //smtp.qq.com，smtp.gmail.com
    private final static String username = "ihyosung@qq.com";//ihyosung@qq.com，hariexpresserp@gmail.com
    private final static String password = "utghnrilpahrbbjb"; //utghnrilpahrbbjb，hari@2020
    private final static String senderName = "hahaha";
    private final static String replayAddress = "ihyosung@qq.com"; //ihyosung@qq.com，hariexpresserp@gmail.com

    public static void sendMail(String receiver, String title, String content)throws Exception{

        Message message = getMessage(receiver, title);

        Multipart mainPart = new MimeMultipart();
        BodyPart html = new MimeBodyPart();
        html.setContent(content, "text/html; charset=utf-8");
        mainPart.addBodyPart(html);
        message.setContent(mainPart);
//        message.setText(content);// 发送文本

        Transport.send(message);
    }

    private static Message getMessage(String receiver, String title) throws Exception{
        final Properties p = System.getProperties() ;
        p.setProperty("mail.smtp.host", host);
        p.setProperty("mail.smtp.auth", "true");
        p.setProperty("mail.smtp.user", username);
        p.setProperty("mail.smtp.pass", password);

        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session session = Session.getInstance(p, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(p.getProperty("mail.smtp.user"),p.getProperty("mail.smtp.pass"));
            }
        });
        session.setDebug(true);
        Message message = new MimeMessage(session);
        //消息发送的主题
        message.setSubject(title);
        //接受消息的人
        message.setReplyTo(InternetAddress.parse(replayAddress));
        //消息的发送者
        message.setFrom(new InternetAddress(p.getProperty("mail.smtp.user"),senderName));
        // 创建邮件的接收者地址，并设置到邮件消息中
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(receiver));
        // 消息发送的时间
        message.setSentDate(new Date());


        return message ;
    }

    public static void main(String[] args) {
        String receiver = "815379806@qq.com"; //发送对象的邮箱
        String title = "无主题";
        String content = "<!DOCTYPE html>\n" +
                "<html lang=\"en\" xmlns:th=\"http://www.thymeleaf.org\">\n" +
                "\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\"/>\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\"/>\n" +
                "  <title>email</title>\n" +
                "</head>\n" +
                "<style>\n" +
                "  html,\n" +
                "  body {\n" +
                "    margin: 0;\n" +
                "    padding: 0;\n" +
                "    height: 100%;\n" +
                "  }\n" +
                "\n" +
                "  .emailBox {\n" +
                "    position: relative;\n" +
                "    margin: 0 auto;\n" +
                "    min-height: 80%;\n" +
                "  }\n" +
                "\n" +
                "  div,\n" +
                "  p {\n" +
                "    margin: 0;\n" +
                "    padding: 0;\n" +
                "    list-style: none;\n" +
                "  }\n" +
                "\n" +
                "  .emailBox {\n" +
                "    padding: 60px\n" +
                "  }\n" +
                "\n" +
                "  .title {\n" +
                "    text-align: center\n" +
                "  }\n" +
                "\n" +
                "  @media(min-width:1200px) {\n" +
                "\n" +
                "    .emailConent,\n" +
                "    .footerBox {\n" +
                "      width: 800px;\n" +
                "    }\n" +
                "\n" +
                "    .emailBox {\n" +
                "      padding: 60px\n" +
                "    }\n" +
                "  }\n" +
                "\n" +
                "  @media(max-width:1200px) {\n" +
                "\n" +
                "    .emailConent,\n" +
                "    .footerBox {\n" +
                "      width: 85%;\n" +
                "    }\n" +
                "\n" +
                "    .emailBox {\n" +
                "      padding: 60px 10px 10px 10px\n" +
                "    }\n" +
                "  }\n" +
                "\n" +
                "  @media(max-width:760px) {\n" +
                "    img {\n" +
                "      width: 150px;\n" +
                "    }\n" +
                "  }\n" +
                "\n" +
                "  @media(min-width:760px) {\n" +
                "    img {\n" +
                "      width: 260px;\n" +
                "    }\n" +
                "  }\n" +
                "\n" +
                "  .emailConent {\n" +
                "    margin: 0 auto;\n" +
                "    border-top: 5px solid #999;\n" +
                "    color: #666;\n" +
                "    margin-top: 60px\n" +
                "  }\n" +
                "\n" +
                "  .conentTitle h1 {\n" +
                "    text-align: left;\n" +
                "    color: #666\n" +
                "  }\n" +
                "\n" +
                "  .f18 {\n" +
                "    font-size: 18px\n" +
                "  }\n" +
                "\n" +
                "  .f16 {\n" +
                "    font-size: 16px\n" +
                "  }\n" +
                "\n" +
                "  .conents {\n" +
                "    margin: 60px 0;\n" +
                "  }\n" +
                "\n" +
                "  .conents h1 {\n" +
                "    text-align: center;\n" +
                "    color: #00c1de\n" +
                "  }\n" +
                "\n" +
                "  .text {\n" +
                "    padding: 50px;\n" +
                "    background-color: #e8e8e8;\n" +
                "  }\n" +
                "\n" +
                "  .text p {\n" +
                "    font-size: 14px;\n" +
                "    margin-bottom: 30px\n" +
                "  }\n" +
                "\n" +
                "  .emailBottom p {\n" +
                "    font-size: 14px;\n" +
                "  }\n" +
                "\n" +
                "  .emailFooter img {\n" +
                "    margin-right: 10px;\n" +
                "    /* height: 60px; */\n" +
                "  }\n" +
                "\n" +
                "  .emailFooter {\n" +
                "    padding: 10px;\n" +
                "    width: 100%;\n" +
                "    background-color: #2e3443;\n" +
                "    color: #eee;\n" +
                "    text-align: center;\n" +
                "    font-size: 16px;\n" +
                "  }\n" +
                "\n" +
                "  .footerBox {\n" +
                "    margin: 0 auto;\n" +
                "  }\n" +
                "\n" +
                "  .emailFooter span {\n" +
                "    font-size: 14px;\n" +
                "    color: #fff\n" +
                "  }\n" +
                "\n" +
                "  .mb150 {\n" +
                "    margin-bottom: 150px;\n" +
                "  }\n" +
                "</style>\n" +
                "\n" +
                "<body>\n" +
                "  <div class=\"emailBox\" >\n" +
                "    <div class=\"title\">\n" +
                "      <img src=\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1568269134281&di=f283e95f226c3c926b0088f4a2517290&imgtype=0&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F908fa0ec08fa513db777cf78376d55fbb3fbd9b3.jpg\" alt=\"\"/>\n" +
                "    </div>\n" +
                "    <div class=\"emailConent mb150\">\n" +
                "      <div class=\"conentTitle\">\n" +
                "        <h1 th:text=\"${mobile}\">12345647897</h1>\n" +
                "        <p class=\"f18\"> Aqui está o código <i>HARIEXPRESS</i> que você precisa para fazer login na conta <i th:text=\"${mobile}\"></i>:</p>\n" +
                "      </div>\n" +
                "      <div class=\"conents\">\n" +
                "        <span th:text=\"${code}\">qweasd</span>\n" +
                "        <div class=\"text\">\n" +
                "          <p>Este e-mail foi gerado com o objetivo de verificar e autenticar sua conta. </p>\n" +
                "          <p>O código <i>KOF98A</i> é necessário para completar o login. Ninguém pode acessar sua conta sem acessar est</p>\n" +
                "          <p>Se você não está tentando fazer o login, por favor, mude sua senha do HARIEXPRESS, e considere alte</p>\n" +
                "          <p>Se você não conseguir acessar sua conta, use este link de recuperação específico para obter assistê</p>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "      <div class=\"emailBottom\">\n" +
                "        <p>Equipe hariexpress</p>\n" +
                "        <p>https://www.hariexpress.com</p>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "    <div class=\"emailFooter\">\n" +
                "      <div class=\"footerBox\">\n" +
                "        <div>\n" +
                "          <img src=\"http://ph04gsti2.bkt.gdipper.com/email_two1.png\" alt=\"\"/>\n" +
                "        </div>\n" +
                "        <span>Hariexpress | Solution-All-In-One © 2018 Todos os direitos reservados</span>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
        try {
            MailSendUtil.sendMail(receiver, title, content);
        } catch (Exception e) {
            System.out.print("'"+title+"'的邮件发送失败！");
            e.printStackTrace();
        }
    }
}