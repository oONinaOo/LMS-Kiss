package login;

import java.io.IOException;

/**
 * Created by nina on 2017.03.29..
 */
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("text/html");
        CSVHandling readCsv = new CSVHandling();
        ArrayList<loginDb> log = readCsv.CSVReader();
        loginDb user = new loginDb(request.getParameter("email"), request.getParameter("pw"), "", "");

        String result = "";

        for(int i = 0; i < log.size(); i++){
            if(log.get(i).email.equals(user.email)){
                if(log.get(i).passwors.equals(user.passwors)){
                    RequestDispatcher login=request.getRequestDispatcher("index.html");
                    login.include(request,response);

                    FileWriter writer = new FileWriter("/home/nina/workspace/LMS-Kiss/src/currentuser.csv");

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
                RequestDispatcher wronguserandpw=request.getRequestDispatcher("wronguserandpw.html");
                wronguserandpw.include(request,response);
                break;
            }
        }

    }

}
