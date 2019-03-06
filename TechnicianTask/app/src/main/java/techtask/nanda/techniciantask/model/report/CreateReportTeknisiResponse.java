package techtask.nanda.techniciantask.model.report;

/**
 * Created by nandana.samudera on 13/12/2018.
 */

public class CreateReportTeknisiResponse {
    private int res_code;
    private CreateReportTeknisiStatus status;
    private String res_function;

    public CreateReportTeknisiResponse(int res_code, CreateReportTeknisiStatus status, String res_function) {
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

    public CreateReportTeknisiStatus getStatus() {
        return status;
    }

    public void setStatus(CreateReportTeknisiStatus status) {
        this.status = status;
    }

    public String getRes_function() {
        return res_function;
    }

    public void setRes_function(String res_function) {
        this.res_function = res_function;
    }
}
