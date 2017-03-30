package login;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by nina on 2017.03.29..
 */
@WebServlet(name = "Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("text/html");
        CSVHandling readCsv = new CSVHandling();
        ArrayList<UserInfo> log = readCsv.CSVReader("/home/nina/LMS/src/logins.csv");
        UserInfo user = new UserInfo(request.getParameter("email"), request.getParameter("pw"), "", "");

        String result = "";

        for(int i = 0; i < log.size(); i++){
            if(log.get(i).email.equals(user.email)){
                if(log.get(i).password.equals(user.password)){

                    PrintWriter out = response.getWriter();
                    String docType =
                            "<!doctype html public \"-//w3c//dtd html 4.0 " +
                                    "transitional//en\">\n";
                    String dashboard=
                            docType +
                                    "<html xmlns=\"http://www.w3.org/1999/xhtml\">" +
                                    "<head>" + "<title> Dashboard</title>" +
                                    "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />" +
                                    "<link href=\"loggedin.css\" rel=\"stylesheet\" type=\"text/css\" />" +
                                            "<style type=\"text/css\">" +
                                              "body { \n" +
                                                    "background-color: #DADADA;\n" +
                                                    "}" +
                                    "</style></head>" +
                                    "<body>" +
                                    "<table width=\"1000\" border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\">" +
                                    "<tr>" +
                                    "<td><img src=\"logged_images/spacer.gif\" width=\"199\" height=\"1\" border=\"0\" alt=\"\" /></td>" +
                                    "<td><img src=\"logged_images/spacer.gif\" width=\"26\" height=\"1\" border=\"0\" alt=\"\" /></td>"+
                                    "<td><img src=\"logged_images/spacer.gif\" width=\"733\" height=\"1\" border=\"0\" alt=\"\" /></td>"+

                                    "<td><img src=\"logged_images/spacer.gif\" width=\"42\" height=\"1\" border=\"0\" alt=\"\" /></td>" +
                                    "<td><img src=\"logged_images/spacer.gif\" width=\"1\" height=\"1\" border=\"0\" alt=\"\" /></td>" +
                                            "</tr> <tr> <td colspan=\"4\"><img name=\"header\" src=\"logged_images/header.png\" width=\"1000\" height=\"207\" border=\"0\" id=\"header\" alt=\"\" /></td>" +
                                            "<td><img src=\"logged_images/spacer.gif\" width=\"1\" height=\"207\" border=\"0\" alt=\"\" /></td>" +
                                            "</tr> <tr> <td><img name=\"cc\" src=\"logged_images/cc.png\" width=\"199\" height=\"98\" border=\"0\" id=\"cc\" alt=\"\" /></td>" +
                                            "<td rowspan=\"2\" background=\"logged_images/left_space.png\">&nbsp;</td>" +
                                            "<td rowspan=\"2\" valign=\"top\" bgcolor=\"#FFFFFF\">&nbsp; <img src=\"logged_images/timeline.png\" height=\"25%\" width=\"100%\"> </td>" +
                                            "<td rowspan=\"2\" background=\"logged_images/right_space.png\">&nbsp;</td> <td><img src=\"logged_images/spacer.gif\" width=\"1\" height=\"98\" border=\"0\" alt=\"\" /></td>" +
                                            "</tr> <tr> <td rowspan=\"3\" valign=\"top\" bgcolor=\"#737373\">&nbsp;" +
                                            "<form action=\"/table\" method=\"post\" target=\"_blank\">" +
                                            "<br>"+
                                            "<h4><center> <font color=\"black\">Hello " + log.get(i).name + "!</font></center></h4>" +
                                            "<br>"+
                                            "<input type=\"submit\" class=\"button\" value=\"Get table\" name = \"button1\">" +
                                            "</form> " +
                                            "</td> <td><img src=\"logged_images/spacer.gif\" width=\"1\" height=\"412\" border=\"0\" alt=\"\" /></td>" +
                                            "</tr> <tr> <td colspan=\"3\"><img name=\"line\" src=\"logged_images/line.png\" width=\"801\" height=\"25\" border=\"0\" id=\"line\" alt=\"\" /></td>" +
                                            "<td><img src=\"logged_images/spacer.gif\" width=\"1\" height=\"25\" border=\"0\" alt=\"\" /></td> </tr> <tr>" +
                                            "<td colspan=\"3\" background=\"logged_images/footer.png\">&nbsp;</td>" +
                                            "<td><img src=\"logged_images/spacer.gif\" width=\"1\" height=\"18\" border=\"0\" alt=\"\" /></td> </tr> </table> </body> </html>";

                    out.println(dashboard);
                    FileWriter writer = new FileWriter("/home/nina/LMS/src/currentuser.csv");

                    writer.append(request.getParameter("email"));
                    writer.append(", ");
                    writer.append(request.getParameter("pw"));
                    writer.append(", ");

                    writer.append(log.get(i).role);
                    writer.append(", ");
                    writer.append(log.get(i).name);
                    writer.append('\n');

                    writer.flush();
                    writer.close();
                    break;
                }
                else{
                    RequestDispatcher wrongpw=request.getRequestDispatcher("wrongpw.html");
                    wrongpw.include(request,response);
                    break;

                }
            }
            else{
                if(i == log.size()-1) {
                    RequestDispatcher wronguserandpw = request.getRequestDispatcher("wronguserandpw.html");
                    wronguserandpw.include(request, response);
                }
            }
        }

    }

}
