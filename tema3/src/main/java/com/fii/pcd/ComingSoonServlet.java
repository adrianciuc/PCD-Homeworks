package com.fii.pcd;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ComingSoonServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getOutputStream().println(
                "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>EDUE</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <h5>Coming soon ...:)</h5>\n" +
                        "</body>\n" +
                        "</html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getOutputStream().println(
                "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>EDUE</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h4>Coming soon ...:)</h4>\n" +
                "</body>\n" +
                "</html>");
    }
}
