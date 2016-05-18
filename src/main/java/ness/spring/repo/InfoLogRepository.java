package ness.spring.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by V3790149 on 5/16/2016.
 */
@Repository
public interface InfoLogRepository extends CrudRepository<InfoLog, Long> {
    List<InfoLog> findByIp(String ip);
}
