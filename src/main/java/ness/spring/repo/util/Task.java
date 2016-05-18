package ness.spring.repo.util;

import ness.spring.repo.InfoLogRepository;

/**
 * Created by V3790149 on 5/18/2016.
 */
public class Task implements Runnable {
    private String path;
    InfoLogRepository repo;

    public Task(String path, InfoLogRepository repo) {
        this.path = path;
        this.repo = repo;
    }

    public String getPath() {
        return this.getPath();
    }

    @Override
    public void run() {
        ReadAndUpload read = new ReadAndUpload(repo);
        read.readForUpload(path);
    }
}
