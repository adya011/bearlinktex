package techtask.nanda.techniciantask.model;

/**
 * Created by ASUS on 12/12/2018.
 */

public class TeamLeaderResponse {
    private int res_code;
    private TeamLeaderStatus status;
    private String res_function;

    public TeamLeaderResponse(int res_code, TeamLeaderStatus status, String res_function) {
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

    public TeamLeaderStatus getStatus() {
        return status;
    }

    public void setStatus(TeamLeaderStatus status) {
        this.status = status;
    }

    public String getRes_function() {
        return res_function;
    }

    public void setRes_function(String res_function) {
        this.res_function = res_function;
    }
}
