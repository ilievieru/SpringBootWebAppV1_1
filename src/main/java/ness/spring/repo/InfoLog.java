package ness.spring.repo;
/**
 * Created by V3790149 on 5/16/2016.
 */

import javax.persistence.*;

@Entity
public class InfoLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return this.id;
    }

    public InfoLog() {

    }

    public InfoLog(String ip, String size, String response) {
        this.ip = ip;
        this.nr = size;
        this.response = response;
    }
    @Column(name = "IP")
    public String ip;

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return this.ip;
    }

    @Column(name = "BytesSend")
    public String nr;

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getNr() {
        return this.nr;
    }

    @Column(name = "Response")
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
