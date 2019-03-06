package techtask.nanda.techniciantask.model.report;

import android.net.Uri;

/**
 * Created by nandana.samudera on 13/12/2018.
 */

public class ReportImageTeknisi {
    String name;
    Uri uri;

    public ReportImageTeknisi() {}

    public ReportImageTeknisi(String name, Uri uri) {
        this.name = name;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
