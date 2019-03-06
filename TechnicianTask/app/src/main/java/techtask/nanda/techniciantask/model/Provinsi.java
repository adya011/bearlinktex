package techtask.nanda.techniciantask.model;

/**
 * Created by ASUS on 11/12/2018.
 */

public class Provinsi {
    private String name;
    private String id;

    public Provinsi(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
