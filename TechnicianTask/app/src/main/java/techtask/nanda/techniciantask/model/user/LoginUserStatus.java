package techtask.nanda.techniciantask.model.user;

import java.util.List;

/**
 * Created by nandana.samudera on 14/12/2018.
 */

public class LoginUserStatus {
    private List<LoginUserResult> result;
    private int req_status;
    private String req_msg;

    public LoginUserStatus(List<LoginUserResult> result, int req_status, String req_msg) {
        this.result = result;
        this.req_status = req_status;
        this.req_msg = req_msg;
    }

    public List<LoginUserResult> getResult() {
        return result;
    }

    public void setResult(List<LoginUserResult> result) {
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
