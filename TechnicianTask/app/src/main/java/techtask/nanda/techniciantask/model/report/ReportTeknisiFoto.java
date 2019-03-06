package techtask.nanda.techniciantask.model.report;

/**
 * Created by ASUS on 03/02/2019.
 */

public class ReportTeknisiFoto {
    private int res_code;
    private ReportTeknisiFotoStatus status;
    private String res_function;

    public int getRes_code() {
        return res_code;
    }

    public void setRes_code(int res_code) {
        this.res_code = res_code;
    }

    public ReportTeknisiFotoStatus getStatus() {
        return status;
    }

    public void setStatus(ReportTeknisiFotoStatus status) {
        this.status = status;
    }

    public String getRes_function() {
        return res_function;
    }

    public void setRes_function(String res_function) {
        this.res_function = res_function;
    }
}
