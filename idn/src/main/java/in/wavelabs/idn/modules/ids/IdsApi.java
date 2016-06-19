package in.wavelabs.idn.modules.ids;

import in.wavelabs.idn.DataModel.ids.IdsApiModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by vivekkiran on 6/16/16.
 */

public interface IdsApi {
   //All Modules JSON API
    String modsApiJson = "/ids/v0/definitions/api-json";
    @GET(modsApiJson)
    Call<IdsApiModel>  getModsApiJson();

   //Get Module API definition
    String modApiDef = "/ids/v0/definitions/modules/{moduleName}";
    @GET(modApiDef)
    Call<IdsApiModel>  getModApiDef(@Path("moduleName") String moduleName);

     //Module JSON API
    String modApiJson = "/api/ids/v0/definitions/modules/{moduleName}/api-json";
    @GET(modApiJson)
    Call<ResponseBody>  getModApiJson(@Path("moduleName") String moduleName);

   //Get Tenants API definition
    String tntsApiDef = "/ids/v0/definitions/tenants/{tenantId}";
    @GET(tntsApiDef)
    Call<IdsApiModel>  getTntsApiDef(@Path("tenantId") String tenantId);

   //Tenant JSON API
    String tntApiJson = "/ids/v0/definitions/tenants/{tenantId}/api-json";
    @GET(tntApiJson)
    Call<IdsApiModel>  getTntApiJson(@Path("tenantId") String tenantId);

   //Get Tenants open for biz
    String tnts = "/ids/v0/tenants";
    @GET(tnts)
    Call<IdsApiModel>  getTnts();

   //Get Tenants open for biz
    String modTnts = "/ids/v0/tenants/{moduleName}";
    @GET(modTnts)
    Call<IdsApiModel>  getModTnts(@Path("moduleName")String moduleName);


}
