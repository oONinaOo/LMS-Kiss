package register;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void registerToFile(HttpServletRequest request) throws IOException{
        FileWriter writer = new FileWriter("/home/nina/LMS/src/logins.csv", true);

        writer.append(request.getParameter("email"));
        writer.append(", ");
        writer.append(request.getParameter("pw"));
        writer.append(", ");
        writer.append(request.getParameter("role"));
        writer.append(", ");
        writer.append(request.getParameter("name"));
        writer.append('\n');

        writer.flush();
        writer.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        registerToFile(request);
        RequestDispatcher success=request.getRequestDispatcher("registered.html");
        success.include(request,response);
    }


}
