package com.hiring_platform.Hiring.Platform.utility;

public class Data {
    public static String getMessageBody(String otp , String user){

        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <title>OTP Verification</title>\n" +
                "  <style>\n" +
                "    body {\n" +
                "      font-family: Arial, sans-serif;\n" +
                "      background-color: #f4f4f7;\n" +
                "      color: #333333;\n" +
                "      padding: 20px;\n" +
                "    }\n" +
                "    .container {\n" +
                "      max-width: 500px;\n" +
                "      margin: auto;\n" +
                "      background-color: #ffffff;\n" +
                "      padding: 30px;\n" +
                "      border-radius: 8px;\n" +
                "      box-shadow: 0 2px 5px rgba(0,0,0,0.1);\n" +
                "    }\n" +
                "    .otp {\n" +
                "      font-size: 32px;\n" +
                "      font-weight: bold;\n" +
                "      color: #007bff;\n" +
                "      letter-spacing: 4px;\n" +
                "    }\n" +
                "    .footer {\n" +
                "      margin-top: 20px;\n" +
                "      font-size: 12px;\n" +
                "      color: #777777;\n" +
                "      text-align: center;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div class=\"container\">\n" +
                "    <h2>Hello "+ user+" ,</h2>\n" +
                "    <p>We received a request to verify your email address. Use the OTP below to complete the process:</p>\n" +
                "    <p class=\"otp\">" + otp + "</p>\n" +
                "    <p>This OTP is valid for the next 10 minutes. Do not share this code with anyone.</p>\n" +
                "    <p>If you did not request this, please ignore this email.</p>\n" +
                "    <div class=\"footer\">\n" +
                "      <p>&copy; 2025 YourCompany. All rights reserved.</p>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>\n";
    }
}
