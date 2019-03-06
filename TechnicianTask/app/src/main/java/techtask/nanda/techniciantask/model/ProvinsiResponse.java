package techtask.nanda.techniciantask.model;

import techtask.nanda.techniciantask.model.task.Result;
import techtask.nanda.techniciantask.model.task.Status;

/**
 * Created by ASUS on 11/12/2018.
 */

public class ProvinsiResponse {
    private int res_code;
    private ProvinsiStatus status;
    private String res_function;

    public ProvinsiResponse(int res_code, ProvinsiStatus status, String res_function) {
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

    public ProvinsiStatus getStatus() {
        return status;
    }

    public void setStatus(ProvinsiStatus status) {
        this.status = status;
    }

    public String getRes_function() {
        return res_function;
    }

    public void setRes_function(String res_function) {
        this.res_function = res_function;
    }
}
