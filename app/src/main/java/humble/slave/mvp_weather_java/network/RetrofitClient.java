package humble.slave.mvp_weather_java.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public String BASE_URL = "https://samples.openweathermap.org/data/2.5/";
//    public String APP_ID = "b6907d289e10d714a6e88b30761fae22";


    //  TODO : Building the url using retrofit with the additional converter from json data type :
    private Retrofit retrofit;

    public Retrofit client() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
