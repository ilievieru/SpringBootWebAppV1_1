package ness.spring.repo.util;

/**
 * Created by V3790149 on 5/18/2016.
 */
public class TaskReadFromFile implements Runnable {
    String rezult= "salut";
    String path;
    public TaskReadFromFile(String path){
        this.path = path;
    }
    public String getRezult(){
        return rezult;
    }

    @Override
    public void run() {
        ReadAndUpload read = new ReadAndUpload();
        rezult = read.read(path);
    }

}
