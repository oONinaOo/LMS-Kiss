package register;

import java.io.IOException;

/**
 * Created by nina on 2017.03.29..
 */
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void registerToFile(HttpServletRequest request) throws IOException{
        FileWriter writer = new FileWriter("/home/nina/workspace/LMS-Kiss/src/logins.csv", true);
        File f = new File("logins.csv");

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

        System.out.println(f.getAbsolutePath());

        final String TEST_PATH = "./LMS-Kiss/";
        String absoluteTestPath = new File(TEST_PATH).getAbsolutePath();
        System.out.println(absoluteTestPath);

        try{
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream("logins.csv");
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null)   {
                // Print the content on the console
                System.out.println (strLine);
            }
            //Close the input stream
            in.close();
        }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        System.out.println();
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
        registerToFile(request);
        RequestDispatcher success=request.getRequestDispatcher("registered.html");
        success.include(request,response);
    }


}
