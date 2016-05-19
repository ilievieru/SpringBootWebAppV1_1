package ness.spring.repo.service;

import ness.spring.repo.InfoLog;
import ness.spring.repo.InfoLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by v3790149 on 5/19/2016.
 */
@Component
public class PersonalRepoImpl implements PersonalRepo {
    @Autowired
    InfoLogRepository repository;

    public void save(InfoLog infoLog) {
        repository.save(infoLog);

    }

    public List findByIp(String ip) {
        return repository.findByIp(ip);
    }

    public List findById(long id) {
        return (List) repository.findOne(id);
    }

    public Iterable findAll() {
        return repository.findAll();
    }
}
