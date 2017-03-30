package login;

import java.io.FileWriter;
import java.io.IOException;
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
        ArrayList<UserInfo> log = readCsv.CSVReader();
        UserInfo user = new UserInfo(request.getParameter("email"), request.getParameter("pw"), "", "");

        String result = "";

        for(int i = 0; i < log.size(); i++){
            if(log.get(i).email.equals(user.email)){
                if(log.get(i).password.equals(user.password)){
                    if(log.get(i).role.equals("student")){
                        RequestDispatcher login=request.getRequestDispatcher("studentpage/dashboard.html");
                        login.include(request,response);
                    }
                    if(log.get(i).role.equals("mentor")){
                        RequestDispatcher login=request.getRequestDispatcher("mentorpage/dashboard.html");
                        login.include(request,response);
                    }

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
