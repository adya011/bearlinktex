package techtask.nanda.techniciantask.model.registerteknisi;

import java.util.List;

/**
 * Created by ASUS on 12/12/2018.
 */

public class RegisterStatus {
    private List<RegisterPerson> result;
    private int req_status;
    private String req_msg;

    public RegisterStatus(List<RegisterPerson> result, int req_status, String req_msg) {
        this.result = result;
        this.req_status = req_status;
        this.req_msg = req_msg;
    }

    public List<RegisterPerson> getResult() {
        return result;
    }

    public void setResult(List<RegisterPerson> result) {
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
