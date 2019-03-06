package techtask.nanda.techniciantask.model.pengerjaan;

/**
 * Created by nandana.samudera on 14/12/2018.
 */

public class CreateTaskPengerjaanResponse {
    private int res_code;
    private CreateTaskPengerjaanStatus status;
    private int req_status;
    private String req_msg;

    public CreateTaskPengerjaanResponse(int res_code, CreateTaskPengerjaanStatus status, int req_status, String req_msg) {
        this.res_code = res_code;
        this.status = status;
        this.req_status = req_status;
        this.req_msg = req_msg;
    }

    public int getRes_code() {
        return res_code;
    }

    public void setRes_code(int res_code) {
        this.res_code = res_code;
    }

    public CreateTaskPengerjaanStatus getStatus() {
        return status;
    }

    public void setStatus(CreateTaskPengerjaanStatus status) {
        this.status = status;
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
