package techtask.nanda.techniciantask.model;

/**
 * Created by ASUS on 12/12/2018.
 */

public class Kabupaten {
    private String id;
    private String name;

    public Kabupaten(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
