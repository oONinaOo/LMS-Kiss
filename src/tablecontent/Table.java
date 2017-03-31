package tablecontent;

import login.CSVHandling;
import login.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "Table")
public class Table extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "Users list:";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " +
                        "transitional//en\">\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title>\n" +
                "<style>\n" +
                "table {\n" +
                "width:40%;\n" +
                "}\n" +
                "table tr:nth-child(even) {\n" +
                "background-color: #eee;\n" +
                "}\n"+
                "table tr:nth-child(odd) {\n"+
                "background-color:#fff;\n"+
                "}\n"+
                "table th {\n"+
                "background-color: black;\n"+
                "color: white;\n"+
                "}\n" +
                "</style>" +
                "</head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<table style=with:100%>" +
                "<tr>" +
                "<th>E-mail address</th>" +
                "<th>Name</th>" +
                "<th>Role</th>" +
                "</tr>" +
                getTableContent() +
                "</body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected String getTableContent() {
        String result = "";
        CSVHandling readCsv = new CSVHandling();
        ArrayList<UserInfo> log = readCsv.CSVReader("/home/nina/LMS/src/logins.csv");


        for(int i = 0; i < log.size(); i++){
            if(currentUserRole().equals("student")){
                if(log.get(i).role.equals("student")){
                    result += "<tr>" +
                            "<td>" + log.get(i).email + "</td>" +
                            "<td>" + log.get(i).name + "</td>" +
                            "<td>" + log.get(i).role + "</td>" +
                            "</tr>";
                }

            }else{
                result += "<tr>" +
                        "<td>" + log.get(i).email + "</td>" +
                        "<td>" + log.get(i).name + "</td>" +
                        "<td>" + log.get(i).role + "</td>" +
                        "</tr>";
            }


        }
        return result;

    }
    protected String currentUserRole(){
        CSVHandling readCsv = new CSVHandling();
        ArrayList<UserInfo> log = readCsv.CSVReader("/home/nina/LMS/src/currentuser.csv");
        return log.get(0).role;

    }

    protected String currentUserName(){
        CSVHandling readCsv = new CSVHandling();
        ArrayList<UserInfo> log = readCsv.CSVReader("/home/nina/LMS/src/currentuser.csv");
        return log.get(0).name;

    }
}
