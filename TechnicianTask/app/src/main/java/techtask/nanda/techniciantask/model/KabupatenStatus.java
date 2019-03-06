package techtask.nanda.techniciantask.model;

import java.util.List;

/**
 * Created by ASUS on 12/12/2018.
 */

public class KabupatenStatus {
    private List<Kabupaten> result;
    private int req_status;
    private String req_msg;

    public KabupatenStatus(List<Kabupaten> result, int req_status, String req_msg) {
        this.result = result;
        this.req_status = req_status;
        this.req_msg = req_msg;
    }

    public List<Kabupaten> getResult() {
        return result;
    }

    public void setResult(List<Kabupaten> result) {
        this.result = result;
    }

    public int getReq_status() {
        return req_status;
    }

    public void setReq_status(int req_status) {
        this.req_status = req_status;
    }

    public String getReq_msg() {
        return req_msg;
    }

    public void setReq_msg(String req_msg) {
        this.req_msg = req_msg;
    }
}
