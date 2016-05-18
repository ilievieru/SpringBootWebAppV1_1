package ness.spring.repo;

import ness.spring.repo.util.ReadAndUpload;
import ness.spring.repo.util.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by V3790149 on 5/16/2016.
 */
@RestController
public class Controller {
    @Autowired
    InfoLogRepository repo;

    @RequestMapping(value = "/salut")
    public String salut() {
        return "Salut user";
    }

    ReadAndUpload read = new ReadAndUpload(repo);

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public
    @ResponseBody
    String multipleSave(@RequestParam("file") MultipartFile[] files) {
        String fileName = null;
        String msg = "";
        BufferedOutputStream buffStream = null;
        ExecutorService executor = Executors.newFixedThreadPool(4);
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                try {
                    fileName = files[i].getOriginalFilename();
                    byte[] bytes = files[i].getBytes();
                    buffStream =
                            new BufferedOutputStream(new FileOutputStream(new File(fileName)));
                    buffStream.write(bytes);
                    Task t = new Task(fileName, repo);
                    executor.submit(t);
                    msg += "You have successfully uploaded " + fileName + "<br/>";
                } catch (Exception e) {
                    return "You failed to upload " + fileName + ": " + e.getMessage() + "<br/>";
                } finally {
                    try {
                        buffStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return msg;
        } else {
            return "Unable to upload. File is empty.";
        }
    }

    @RequestMapping("/{ip}/see")
    @ResponseBody
    public String ipdetailes(@PathVariable("ip") String ip) {
        String rez = "Salut ";
        // System.out.println(ip);
        Iterable<InfoLog> list = repo.findByIp(ip);
        for (InfoLog inf : list)
            rez += "<p>" + inf.toString() + "</p>";
        return rez;
    }

    @RequestMapping("/findAll")
    public String find(){
        String rez = "";
        Iterable<InfoLog> list = repo.findAll();
        for(InfoLog info : list){
            rez += "<p>" + info.toString() +"</p>";
        }
        return rez;
    }

    @RequestMapping("/{ip}/bytes")
    @ResponseBody
    public String bytesCount(@PathVariable("ip") String ip) {
        int total = 0;
        // System.out.println(ip);
        Iterable<InfoLog> list = repo.findByIp(ip);
        for (InfoLog inf : list)
            total += Integer.parseInt(inf.getNr().trim());
        return "<p>IP " + ip + " total bytes send " + total ;
    }


    @RequestMapping("/{ip}/response")
    @ResponseBody
    public String response(@PathVariable("ip") String ip) {
        float total200 = 0;
        float total404 = 0;
        // System.out.println(ip);
        List<InfoLog> list = repo.findByIp(ip);
        int tot = list.size();
        for (InfoLog inf : list){
           if(inf.getResponse().contains("200"))
               total200 ++;
            if(inf.getResponse().contains("404"))
                total404 ++;
        }
        float tot200 = total200/tot ;
        float tot404 = total404/tot;
        return "<p>IP " + ip + " has " + tot200*100 +"% positive responses and " + tot404*100  + "% negative responses ";
    }
}
