package techtask.nanda.techniciantask.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import techtask.nanda.techniciantask.fragment.SettingsDetail.AccountFragment;
import techtask.nanda.techniciantask.model.TaskPengerjaanResponse;
import techtask.nanda.techniciantask.model.KabupatenResponse;
import techtask.nanda.techniciantask.model.account.AccountUserResponse;
import techtask.nanda.techniciantask.model.pengerjaan.CreateTaskPengerjaan;
import techtask.nanda.techniciantask.model.pengerjaan.CreateTaskPengerjaanResponse;
import techtask.nanda.techniciantask.model.user.LoginUser;
import techtask.nanda.techniciantask.model.user.LoginUserResponse;
import techtask.nanda.techniciantask.model.ProvinsiResponse;
import techtask.nanda.techniciantask.model.registerteknisi.RegisterPerson;
import techtask.nanda.techniciantask.model.registerteknisi.RegisterTeknisiResponse;
import techtask.nanda.techniciantask.model.TeamLeaderResponse;
import techtask.nanda.techniciantask.model.report.CreateReportTeknisi;
import techtask.nanda.techniciantask.model.report.CreateReportTeknisiResponse;
import techtask.nanda.techniciantask.model.report.ReportTeknisiResponse;

/**
 * Created by ASUS on 11/12/2018.
 */

public interface ApiInterface {
    @POST("login/getLogin")
    Call<LoginUserResponse> loginTeknisi(@Body LoginUser loginUser);

    @GET("util/getProvinsi")
    Call<ProvinsiResponse> callProvinsi();

    @GET("util/getKabupaten")
    Call<KabupatenResponse> callKabupatenByProvinsi(@Query("id_provinsi") String id_provinsi);

    @POST("person/create_person")
    Call<RegisterTeknisiResponse> registerPerson(@Body RegisterPerson registerTeknisi);

    @POST("task_wo/create_task_wo")
    Call<CreateTaskPengerjaanResponse> createTaskWo(@Body CreateTaskPengerjaan createTaskPengerjaan);

    @POST("report/createReportTeknisi")
    Call<CreateReportTeknisiResponse> createReportTeknisi(@Body CreateReportTeknisi createReportTeknisi);

    @GET("report/getReportTeknisiById")
    Call<CreateReportTeknisiResponse> callReportTeknisi(@Query("id_task") String id_task);

    @GET("person/getSettingPerson")
    Call<AccountUserResponse> callAccountSettingPerson(@Query("id_person") String id_person);

    @GET("/tl/teamLeader/callTeamLeaderByRegional")
    Call<TeamLeaderResponse> callTeamLeaderByRegional(@Query("alamat_regional") String alamatRegional);

    @GET("/report/teknisi/callReportTeknisiById")
    Call<ReportTeknisiResponse> callReportTeknisiById(@Query("id_task_pengerjaan") int idTaskPengerjaan);

    @GET("task_wo/get_wo_ByIdTeknisi_status")
    Call<TaskPengerjaanResponse> callTaskWoByIdTeknisi(@Query("id_teknisi") String idTaskTeknisi, @Query("status_task") String status_task);

    @POST("report/teknisi/updateFotoReportTeknisi")
    Call<ReportTeknisiResponse> updateReportFotoTeknisi(@Query("id_task_report") String idTaskReport,
                                                        @Query("img1") String img1, @Query("img1") String img2, @Query("img1") String img3,
                                                        @Query("img1") String img4, @Query("img1") String img5, @Query("img1") String img6,
                                                        @Query("img1") String img7, @Query("img1") String img8, @Query("img1") String img9);
}
