package ness.spring.repo.util;

import ness.spring.repo.service.PersonalRepoImpl;

/**
 * Created by V3790149 on 5/18/2016.
 */
public class Task implements Runnable {
    private String path;
    PersonalRepoImpl repo;

    public Task(String path, PersonalRepoImpl repo) {
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
