package ness.spring.repo.util;

import ness.spring.repo.InfoLog;
import ness.spring.repo.InfoLogRepository;

import java.io.*;
import java.util.StringTokenizer;
import java.util.zip.GZIPInputStream;

/**
 * Created by V3790149 on 5/18/2016.
 */
public class ReadAndUpload {
    InfoLogRepository repo;
    public ReadAndUpload(InfoLogRepository repo){
     this.repo = repo;
    }
    public ReadAndUpload(){

    }

    public String read(String path) {
        String result = " ";
        try {
            InputStream fileStream = new FileInputStream(path);
            InputStream gzipStream = new GZIPInputStream(fileStream);
            Reader decoder = new InputStreamReader(gzipStream);
            BufferedReader buffered = new BufferedReader(decoder);
            String strLine;
            /* read log line by line */
            while ((strLine = buffered.readLine()) != null) {
             /* parse strLine to obtain what you want */
                result = result + "<p>" + matcher(strLine) + "</p>";
            }
            fileStream.close();
            gzipStream.close();
            decoder.close();
            buffered.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return result;
    }

    public String matcher(String line) {
        String result = " ";
        StringTokenizer matcher = new StringTokenizer(line);
        String hostName = matcher.nextToken();
        result = "\n" + result + " Hostname: " + hostName;
        matcher.nextToken();
        matcher.nextToken();
        String dateTime = matcher.nextToken("]");
        result = "\n" + result + " Date/Time: " + dateTime;
        //matcher.nextToken(" "); // again
        String request = matcher.nextToken("\"");
        result = "\n" + result + " Request: " + request;
        matcher.nextToken(" "); // again
        String response = matcher.nextToken();
        result = "\n" + result + " Response: " + response;
        String referer = matcher.nextToken();
        result = "\n" + result + " Referer: " + referer;
        String byteCount = matcher.nextToken("\"");
        result = "\n" + result + " ByteCount: " + byteCount;
        matcher.nextToken(" "); // again
        String userAgent = matcher.nextToken("\"");
        result = "\n" + result + " User-Agent: " + userAgent;

        return result;
    }

    public void readForUpload(String path) {

        try {
            InputStream fileStream = new FileInputStream(path);
            InputStream gzipStream = new GZIPInputStream(fileStream);
            Reader decoder = new InputStreamReader(gzipStream);
            BufferedReader buffered = new BufferedReader(decoder);
            String strLine;
            /* read log line by line */
            while ((strLine = buffered.readLine()) != null) {
             /* parse strLine to obtain what you want */
                upload(strLine);
            }
            fileStream.close();
            gzipStream.close();
            decoder.close();
            buffered.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void upload(String line) {
        StringTokenizer matcher = new StringTokenizer(line);
        String hostName = matcher.nextToken();
        //System.out.println(hostName);
        matcher.nextToken();
        matcher.nextToken();
        String dateTime = matcher.nextToken("]");
        //matcher.nextToken(" "); // again
        String request = matcher.nextToken("\"");
        matcher.nextToken(" "); // again
        String response = matcher.nextToken();
        String referer = matcher.nextToken();
        String byteCount = matcher.nextToken("\"");
        matcher.nextToken(" "); // again
        String userAgent = matcher.nextToken("\"");

        //creaza entitate

        InfoLog inf = new InfoLog(hostName, byteCount);
        repo.save(inf);
    }

}
