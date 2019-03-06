package techtask.nanda.techniciantask.model.report;

import java.util.ArrayList;
import java.util.List;

import techtask.nanda.techniciantask.model.task.Result;

/**
 * Created by nandana.samudera on 13/12/2018.
 */

public class CreateReportTeknisiStatus {
    private List<CreateReportTeknisi> result;
    private int req_status;
    private String req_msg;

    public CreateReportTeknisiStatus(List<CreateReportTeknisi> result, int req_status, String req_msg) {
        this.result = result;
        this.req_status = req_status;
        this.req_msg = req_msg;
    }

    public List<CreateReportTeknisi> getResult() {
        return result;
    }

    public void setResult(List<CreateReportTeknisi> result) {
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
