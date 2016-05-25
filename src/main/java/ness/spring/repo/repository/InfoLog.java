package ness.spring.repo.repository;
/**
 * Created by V3790149 on 5/16/2016.
 */

import com.fasterxml.jackson.annotation.JsonView;
import ness.spring.repo.mapper.Views;

import javax.persistence.*;

@Entity
@Table(name = "ilie")
public class InfoLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.AdminInfoLog.class)
    private int id;

    public int getId() {
        return this.id;
    }

    public InfoLog() {

    }

    public InfoLog(String ip, String size, String response) {
        this.ip = ip;
        this.nr = size;
        this.response = response;
    }
    @Column(name = "ip")
    @JsonView(Views.UserInfoLog.class)
    public String ip;

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return this.ip;
    }

    @Column(name = "bytes")
    @JsonView(Views.AdminInfoLog.class)
    public String nr;

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getNr() {
        return this.nr;
    }

    @Column(name = "response")
    @JsonView(Views.UserInfoLog.class)
    public String response;

    public void setResponse(String response){
        this.response = response;
    }

    public String getResponse(){
        return response;
    }

    @Override
    public String toString() {
        return "ID " + this.id + " IP " + this.ip + " Bytes Send " + this.nr + " response " + this.getResponse();
    }
}
