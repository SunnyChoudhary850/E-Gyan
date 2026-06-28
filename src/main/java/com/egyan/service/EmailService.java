package com.egyan.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendHtmlMail(String to, String subject, String htmlBody) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);
            helper.setFrom("royal.sunny002@gmail.com");
            mailSender.send(message);
        } catch (MessagingException e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }

    public void sendOtpEmail(String to, String otp) {
            String subject = "Your EGyan Verification Code";
            String body = """
        <div style="font-family:Arial,sans-serif;max-width:600px;margin:auto;background:#f9f9f9;border-radius:12px;overflow:hidden;">
          <div style="background:#2d6a4f;padding:30px;text-align:center;">
            <h1 style="color:#fff;margin:0;font-size:28px;">EGyan</h1>
            <p style="color:#a8d5b5;margin:6px 0 0;">Email Verification</p>
          </div>
          <div style="padding:30px;text-align:center;">
            <h2 style="color:#1b4332;">Your OTP Code</h2>
            <p style="color:#555;">Use the code below to verify your email address. It expires in 10 minutes.</p>
            <div style="background:#fff;border:2px solid #2d6a4f;border-radius:12px;padding:20px;margin:20px auto;width:fit-content;">
              <h1 style="color:#2d6a4f;font-size:42px;letter-spacing:12px;margin:0;">%s</h1>
            </div>
            <p style="color:#999;font-size:13px;">If you did not request this, please ignore this email.</p>
          </div>
          <div style="background:#f0f0f0;padding:15px;text-align:center;">
            <p style="color:#999;font-size:12px;margin:0;">© 2025 EGyan Institute of Excellence</p>
          </div>
        </div>
    """.formatted(otp);
            sendHtmlMail(to, subject, body);

    }

    public void sendStudentWelcome(String to, String name) {
        String subject = "Welcome to EGyan, " + name + "! 🎓";
        String body = """
            <div style="font-family:Arial,sans-serif;max-width:600px;margin:auto;background:#f9f9f9;border-radius:12px;overflow:hidden;">
              <div style="background:#2d6a4f;padding:30px;text-align:center;">
                <h1 style="color:#fff;margin:0;font-size:28px;">EGyan</h1>
                <p style="color:#a8d5b5;margin:6px 0 0;">Institute of Excellence</p>
              </div>
              <div style="padding:30px;">
                <h2 style="color:#1b4332;">🎉 Welcome aboard, %s!</h2>
                <p style="color:#555;line-height:1.7;">Your student account has been successfully created on EGyan. You can now browse courses, enroll, and access study materials.</p>
                <div style="background:#e8f5e9;border-left:4px solid #2d6a4f;padding:15px;border-radius:6px;margin:20px 0;">
                  <p style="margin:0;color:#1b4332;font-weight:bold;">What's next?</p>
                  <ul style="color:#555;margin:8px 0 0;padding-left:20px;">
                    <li>Browse available courses</li>
                    <li>Enroll in courses that interest you</li>
                    <li>Access lecture notes, PDFs and videos</li>
                  </ul>
                </div>
                <a href="http://localhost:8080/egyan" style="display:inline-block;background:#2d6a4f;color:#fff;padding:12px 28px;border-radius:8px;text-decoration:none;font-weight:bold;">Go to EGyan Portal →</a>
              </div>
              <div style="background:#f0f0f0;padding:15px;text-align:center;">
                <p style="color:#999;font-size:12px;margin:0;">© 2025 EGyan Institute of Excellence</p>
              </div>
            </div>
        """.formatted(name);
        sendHtmlMail(to, subject, body);
    }

    public void sendFacultyWelcome(String to, String name) {
        String subject = "Welcome to EGyan Faculty Portal, " + name + "!";
        String body = """
            <div style="font-family:Arial,sans-serif;max-width:600px;margin:auto;background:#f9f9f9;border-radius:12px;overflow:hidden;">
              <div style="background:#1b4332;padding:30px;text-align:center;">
                <h1 style="color:#fff;margin:0;font-size:28px;">EGyan</h1>
                <p style="color:#a8d5b5;margin:6px 0 0;">Faculty Portal</p>
              </div>
              <div style="padding:30px;">
                <h2 style="color:#1b4332;">👨‍🏫 Welcome, Prof. %s!</h2>
                <p style="color:#555;line-height:1.7;">Your faculty account has been created on EGyan. You can now create courses and upload study materials for your students.</p>
                <div style="background:#e8f5e9;border-left:4px solid #1b4332;padding:15px;border-radius:6px;margin:20px 0;">
                  <p style="margin:0;color:#1b4332;font-weight:bold;">Your capabilities:</p>
                  <ul style="color:#555;margin:8px 0 0;padding-left:20px;">
                    <li>Create and manage courses</li>
                    <li>Upload PDFs, videos and presentations</li>
                    <li>View enrolled students</li>
                  </ul>
                </div>
                <a href="http://localhost:8080/egyan" style="display:inline-block;background:#1b4332;color:#fff;padding:12px 28px;border-radius:8px;text-decoration:none;font-weight:bold;">Go to Faculty Portal →</a>
              </div>
              <div style="background:#f0f0f0;padding:15px;text-align:center;">
                <p style="color:#999;font-size:12px;margin:0;">© 2025 EGyan Institute of Excellence</p>
              </div>
            </div>
        """.formatted(name);
        sendHtmlMail(to, subject, body);
    }

    public void sendEnrollmentConfirmation(String to, String studentName, String courseTitle, String teacherName) {
        String subject = "🎓 Enrolled in " + courseTitle + " — EGyan";
        String body = """
            <div style="font-family:Arial,sans-serif;max-width:600px;margin:auto;background:#f9f9f9;border-radius:12px;overflow:hidden;">
              <div style="background:#2d6a4f;padding:30px;text-align:center;">
                <h1 style="color:#fff;margin:0;font-size:28px;">EGyan</h1>
              </div>
              <div style="padding:30px;">
                <h2 style="color:#1b4332;">🎉 Congratulations, %s!</h2>
                <p style="color:#555;line-height:1.7;">You have successfully enrolled in the following course:</p>
                <div style="background:#fff;border:1px solid #ddd;border-radius:10px;padding:20px;margin:20px 0;">
                  <h3 style="color:#1b4332;margin:0 0 8px;">📚 %s</h3>
                  <p style="color:#777;margin:0;font-size:14px;">👨‍🏫 Instructor: <strong>%s</strong></p>
                </div>
                <p style="color:#555;line-height:1.7;">You can now access all course materials, lecture notes, and videos uploaded by your instructor.</p>
                <a href="http://localhost:8080/egyan" style="display:inline-block;background:#2d6a4f;color:#fff;padding:12px 28px;border-radius:8px;text-decoration:none;font-weight:bold;">View Course Materials →</a>
              </div>
              <div style="background:#f0f0f0;padding:15px;text-align:center;">
                <p style="color:#999;font-size:12px;margin:0;">© 2025 EGyan Institute of Excellence</p>
              </div>
            </div>
        """.formatted(studentName, courseTitle, teacherName);
        sendHtmlMail(to, subject, body);
    }

    public void sendCourseCreatedConfirmation(String to, String facultyName, String courseTitle) {
        String subject = "✅ Course Created: " + courseTitle + " — EGyan";
        String body = """
            <div style="font-family:Arial,sans-serif;max-width:600px;margin:auto;background:#f9f9f9;border-radius:12px;overflow:hidden;">
              <div style="background:#1b4332;padding:30px;text-align:center;">
                <h1 style="color:#fff;margin:0;font-size:28px;">EGyan</h1>
              </div>
              <div style="padding:30px;">
                <h2 style="color:#1b4332;">✅ Course Published, %s!</h2>
                <p style="color:#555;line-height:1.7;">Your new course has been successfully created on EGyan:</p>
                <div style="background:#fff;border:1px solid #ddd;border-radius:10px;padding:20px;margin:20px 0;">
                  <h3 style="color:#1b4332;margin:0;">📚 %s</h3>
                </div>
                <p style="color:#555;line-height:1.7;">Students can now find and enroll in your course. Start uploading materials to get them learning!</p>
                <a href="http://localhost:8080/egyan" style="display:inline-block;background:#1b4332;color:#fff;padding:12px 28px;border-radius:8px;text-decoration:none;font-weight:bold;">Manage Your Course →</a>
              </div>
              <div style="background:#f0f0f0;padding:15px;text-align:center;">
                <p style="color:#999;font-size:12px;margin:0;">© 2025 EGyan Institute of Excellence</p>
              </div>
            </div>
        """.formatted(facultyName, courseTitle);
        sendHtmlMail(to, subject, body);
    }
}