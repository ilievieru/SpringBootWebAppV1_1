package ness.spring.repo;

import ness.spring.repo.util.ReadAndUpload;
import ness.spring.repo.util.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                try {
                    fileName = files[i].getOriginalFilename();
                    byte[] bytes = files[i].getBytes();
                    BufferedOutputStream buffStream =
                            new BufferedOutputStream(new FileOutputStream(new File("C:\\Users\\V3790149\\IdeaProjects\\SpringBootWebAppV1_1\\src\\" + fileName)));
                    buffStream.write(bytes);
                    buffStream.close();
                    String path = "C:\\Users\\V3790149\\IdeaProjects\\SpringBootWebAppV1_1\\src\\" + fileName;
                    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
                    Task t = new Task(path,repo);
                    executor.execute(t);
                    msg += "You have successfully uploaded " + fileName + "<br/>";
                } catch (Exception e) {
                    return "You failed to upload " + fileName + ": " + e.getMessage() + "<br/>";
                }
            }
            return msg;
        } else {
            return "Unable to upload. File is empty.";
        }
    }

    @RequestMapping("/readfromFile/{name}")
    @ResponseBody
    public String test(@PathVariable("name") String name) {
        String path = "C:\\Users\\V3790149\\IdeaProjects\\SpringBootWebAppV1_1\\src\\" + name + ".log";
        ReadAndUpload readAndUpload = new ReadAndUpload();
        return readAndUpload.read(path);
    }

    @RequestMapping("/read/{ip}/cauta")
    @ResponseBody
    public String response(@PathVariable("ip") String ip) {
        String rez = "Salut ";
       // System.out.println(ip);
        Iterable<InfoLog> list = repo.findByIp(ip);
            for (InfoLog inf : list)
                rez += "<p>" + inf.toString() + "</p>";
        return rez;
    }


}
