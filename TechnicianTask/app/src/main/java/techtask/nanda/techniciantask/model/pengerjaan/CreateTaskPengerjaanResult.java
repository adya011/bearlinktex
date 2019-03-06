package techtask.nanda.techniciantask.model.pengerjaan;

/**
 * Created by nandana.samudera on 14/12/2018.
 */

public class CreateTaskPengerjaanResult {
    private String message;

    public CreateTaskPengerjaanResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
