package ness.spring.repo.service;

import ness.spring.repo.repository.InfoLog;

import java.util.List;

/**
 * Created by v3790149 on 5/19/2016.
 */
public interface PersonalRepo {

    void save(InfoLog infoLog);
    List findByIp(String ip);
    List findById(long id);
    Iterable findAll();


}
