package login;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by nina on 2017.03.29..
 */
public class CSVHandling {

    public ArrayList<UserInfo> CSVReader(){
        String csvFile = "/home/nina/LMS/src/logins.csv";
        String line;
        String cvsSplitBy = ", ";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile));
            String input;
            int count = 0;
            while((input = bufferedReader.readLine()) != null)
            {
                count++;
            }

            ArrayList<UserInfo> logins = new ArrayList<UserInfo>();


            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] oneLine = line.split(cvsSplitBy);
                UserInfo user= new UserInfo(oneLine[0], oneLine[1], oneLine[2], oneLine[3]);
                logins.add(user);


            } return logins;

        } catch (IOException e) {
            e.printStackTrace();

        } return null;

    }
}

