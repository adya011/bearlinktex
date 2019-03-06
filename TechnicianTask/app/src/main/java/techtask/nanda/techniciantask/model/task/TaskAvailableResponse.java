package techtask.nanda.techniciantask.model.task;

import techtask.nanda.techniciantask.model.task.Status;

/**
 * Created by ASUS on 09/12/2018.
 */

public class TaskAvailableResponse {
    private int res_code;
    private Status status;

    public TaskAvailableResponse(int res_code, Status status) {
        this.res_code = res_code;
        this.status = status;
    }

    public int getRes_code() {
        return res_code;
    }

    public void setRes_code(int res_code) {
        this.res_code = res_code;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
