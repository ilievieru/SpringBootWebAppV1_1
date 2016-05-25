package ness.spring.repo.service;

import ness.spring.repo.repository.InfoLog;
import ness.spring.repo.repository.InfoLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by v3790149 on 5/19/2016.
 */
@Component
public class PersonalRepoImpl implements PersonalRepo {

    @Autowired
    InfoLogRepository info;
    @Override
    public void save(InfoLog infoLog) {
        info.save(infoLog);

    }

    public List findByIp(String ip) {
       return info.findByIp(ip);
    }

    public InfoLog findById(long id) {
       return info.findOne(id);

    }

    public Iterable findAll() {
       return info.findAll();

    }
}
