package techtask.nanda.techniciantask.model._realm;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by ASUS on 17/12/2018.
 */

public class RTaskReport extends RealmObject {
    private String id_task;
    private String sc_report;
    private String no_telpn_report;
    private String user_report;
    private String nama_report;
    private String alamat_report;
    private String odp_report;
    private String pc_report;
    private String br_code_report;
    private String port_report;
    private String sn_out_report;
    private String macstb_report;
    private String sn_ont_report;
    private String ko_report;
    private String kp_report;
    private String ivr_report;
    private String ket_report;
    private String tanggal_report;
    private boolean kendala;
    private String ket_kendala;
    private RealmList<RTaskReportPhoto> reportPhotos;

    public boolean isKendala() {
        return kendala;
    }

    public void setKendala(boolean kendala) {
        this.kendala = kendala;
    }

    public String getKet_kendala() {
        return ket_kendala;
    }

    public void setKet_kendala(String ket_kendala) {
        this.ket_kendala = ket_kendala;
    }

    public RealmList<RTaskReportPhoto> getReportPhotos() {
        return reportPhotos;
    }

    public void setReportPhotos(RealmList<RTaskReportPhoto> reportPhotos) {
        this.reportPhotos = reportPhotos;
    }

    /*public RTaskReport(String id_task_report, String id_task_pengerjaan, String nama_report, String tanggal_report, String alamat_report, String no_telpn_report, String sc_report, String pc_report, String ivr_report, String odp_report, String user_report, String ket_report, String sn_ont_report, String br_code_report, String kp_report, String port_report, String ko_report, String sn_out_report, String macstb_report) {
        this.id_task_report = id_task_report;
        this.id_task_pengerjaan = id_task_pengerjaan;
        this.nama_report = nama_report;
        this.tanggal_report = tanggal_report;
        this.alamat_report = alamat_report;
        this.no_telpn_report = no_telpn_report;
        this.sc_report = sc_report;
        this.pc_report = pc_report;
        this.ivr_report = ivr_report;
        this.odp_report = odp_report;
        this.user_report = user_report;
        this.ket_report = ket_report;
        this.sn_ont_report = sn_ont_report;
        this.br_code_report = br_code_report;
        this.kp_report = kp_report;
        this.port_report = port_report;
        this.ko_report = ko_report;
        this.sn_out_report = sn_out_report;
        this.macstb_report = macstb_report;
    }*/

    public String getId_task() {
        return id_task;
    }

    public void setId_task(String id_task) {
        this.id_task = id_task;
    }

    public String getSc_report() {
        return sc_report;
    }

    public void setSc_report(String sc_report) {
        this.sc_report = sc_report;
    }

    public String getNo_telpn_report() {
        return no_telpn_report;
    }

    public void setNo_telpn_report(String no_telpn_report) {
        this.no_telpn_report = no_telpn_report;
    }

    public String getUser_report() {
        return user_report;
    }

    public void setUser_report(String user_report) {
        this.user_report = user_report;
    }

    public String getNama_report() {
        return nama_report;
    }

    public void setNama_report(String nama_report) {
        this.nama_report = nama_report;
    }

    public String getAlamat_report() {
        return alamat_report;
    }

    public void setAlamat_report(String alamat_report) {
        this.alamat_report = alamat_report;
    }

    public String getOdp_report() {
        return odp_report;
    }

    public void setOdp_report(String odp_report) {
        this.odp_report = odp_report;
    }

    public String getPc_report() {
        return pc_report;
    }

    public void setPc_report(String pc_report) {
        this.pc_report = pc_report;
    }

    public String getBr_code_report() {
        return br_code_report;
    }

    public void setBr_code_report(String br_code_report) {
        this.br_code_report = br_code_report;
    }

    public String getPort_report() {
        return port_report;
    }

    public void setPort_report(String port_report) {
        this.port_report = port_report;
    }

    public String getSn_out_report() {
        return sn_out_report;
    }

    public void setSn_out_report(String sn_out_report) {
        this.sn_out_report = sn_out_report;
    }

    public String getMacstb_report() {
        return macstb_report;
    }

    public void setMacstb_report(String macstb_report) {
        this.macstb_report = macstb_report;
    }

    public String getSn_ont_report() {
        return sn_ont_report;
    }

    public void setSn_ont_report(String sn_ont_report) {
        this.sn_ont_report = sn_ont_report;
    }

    public String getKo_report() {
        return ko_report;
    }

    public void setKo_report(String ko_report) {
        this.ko_report = ko_report;
    }

    public String getKp_report() {
        return kp_report;
    }

    public void setKp_report(String kp_report) {
        this.kp_report = kp_report;
    }

    public String getIvr_report() {
        return ivr_report;
    }

    public void setIvr_report(String ivr_report) {
        this.ivr_report = ivr_report;
    }

    public String getKet_report() {
        return ket_report;
    }

    public void setKet_report(String ket_report) {
        this.ket_report = ket_report;
    }

    public String getTanggal_report() {
        return tanggal_report;
    }

    public void setTanggal_report(String tanggal_report) {
        this.tanggal_report = tanggal_report;
    }
}
