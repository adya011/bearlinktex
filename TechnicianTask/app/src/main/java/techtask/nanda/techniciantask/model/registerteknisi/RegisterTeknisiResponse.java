package techtask.nanda.techniciantask.model.registerteknisi;

/**
 * Created by ASUS on 11/12/2018.
 */

public class RegisterTeknisiResponse {
    private int res_code;
    private RegisterStatus status;
    private String res_function;

    public RegisterTeknisiResponse(int res_code, RegisterStatus status, String res_function) {
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

    public RegisterStatus getStatus() {
        return status;
    }

    public void setStatus(RegisterStatus status) {
        this.status = status;
    }

    public String getRes_function() {
        return res_function;
    }

    public void setRes_function(String res_function) {
        this.res_function = res_function;
    }
}
