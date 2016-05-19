package ness.spring.repo.util;

import ness.spring.repo.repository.InfoLog;
import ness.spring.repo.service.PersonalRepo;

import java.io.*;
import java.util.StringTokenizer;
import java.util.zip.GZIPInputStream;

/**
 * Created by V3790149 on 5/18/2016.
 */
public class ReadAndUpload {
    PersonalRepo repo;

    public ReadAndUpload(PersonalRepo repo) {
        this.repo = repo;
    }

    BufferedReader buffered;

    public void readForUpload(String path) {

        try {
            InputStream fileStream = new FileInputStream(path);
            InputStream gzipStream = new GZIPInputStream(fileStream);
            Reader decoder = new InputStreamReader(gzipStream);
            buffered = new BufferedReader(decoder);
            String strLine;
            while ((strLine = buffered.readLine()) != null) {
                upload(strLine);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                buffered.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void upload(String line) {
        StringTokenizer matcher = new StringTokenizer(line);
       // System.out.println("The line"+line);
        String hostName = matcher.nextToken();
        matcher.nextToken();
        matcher.nextToken();

        String dateTime = matcher.nextToken("]");
        matcher.nextToken("\"");
        String request = matcher.nextToken("\"");

        matcher.nextToken(" ");
        String response = matcher.nextToken();
        String byteCount = matcher.nextToken("\"");


        String referer = matcher.nextToken();
        matcher.nextToken("\"");
        String userAgent = matcher.nextToken("\"");
        InfoLog inf = new InfoLog(hostName, byteCount, response);
        //System.out.println("host "+hostName + " \ndata " + dateTime + " \nrequest " + request +  "\nresponse " + response + " \nreferer" + referer + "\nbyte " + byteCount + "\nuserAgent " + userAgent);
        repo.save(inf);
    }

}
