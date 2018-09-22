package kg.neobis.satz;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {



            @POST("tr.json/translate?")
    Call<Model> getTranslation(@Query("key" ) String key, @Query("text") String text,
                                    @Query("lang") String lang);

}
