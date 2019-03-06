package techtask.nanda.techniciantask.model;

import java.util.List;

/**
 * Created by ASUS on 12/12/2018.
 */

public class TeamLeaderStatus {
    private List<TeamLeader> result;
    private int req_status;
    private String req_msg;

    public TeamLeaderStatus(List<TeamLeader> result, int req_status, String req_msg) {
        this.result = result;
        this.req_status = req_status;
        this.req_msg = req_msg;
    }

    public List<TeamLeader> getResult() {
        return result;
    }

    public void setResult(List<TeamLeader> result) {
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
