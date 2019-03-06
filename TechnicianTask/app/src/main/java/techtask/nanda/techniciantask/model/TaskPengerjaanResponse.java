package techtask.nanda.techniciantask.model;

/**
 * Created by ASUS on 11/12/2018.
 */

public class TaskPengerjaanResponse {
    private int res_code;
    private TaskPengerjaanStatus status;
    private String res_function;

    public TaskPengerjaanResponse(int res_code, TaskPengerjaanStatus status, String res_function) {
        this.res_code = res_code;
        this.status = status;
        this.res_function = res_function;
    }

    public int getRes_code() {
        return res_code;
    }

    public void setRes_code(int res_code) {
        this.res_code = res_code;
    }

    public TaskPengerjaanStatus getStatus() {
        return status;
    }

    public void setStatus(TaskPengerjaanStatus status) {
        this.status = status;
    }

    public String getRes_function() {
        return res_function;
    }

    public void setRes_function(String res_function) {
        this.res_function = res_function;
    }
}
