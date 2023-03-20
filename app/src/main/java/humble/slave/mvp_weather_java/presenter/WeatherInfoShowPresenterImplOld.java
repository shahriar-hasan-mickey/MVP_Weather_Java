package humble.slave.mvp_weather_java.presenter;

import android.content.Context;
import android.util.Log;

import java.util.List;

import humble.slave.mvp_weather_java.common.RequestCompleteListener;
import humble.slave.mvp_weather_java.model.WeatherInfoModel;
import humble.slave.mvp_weather_java.model.WeatherInfoModelImpl;
import humble.slave.mvp_weather_java.model.data.City;
import humble.slave.mvp_weather_java.model.data.WeatherDataModel;
import humble.slave.mvp_weather_java.model.data.WeatherInfoResponse;
import humble.slave.mvp_weather_java.view.MainActivity;
import humble.slave.mvp_weather_java.view.MainActivityView;

public class WeatherInfoShowPresenterImplOld{

/**
 * THIS A OLDER AND RESOURCE CONSUMING VERSION, A BETTER VERSION IS BEING USED INSTEAD WHICH IS THE WeatherInfoShowPresenterImpl CLASS
**/

    private MainActivityView view = new MainActivity();

    Context context;

    private final WeatherInfoModel model;

    public WeatherInfoShowPresenterImplOld(Context context) {
        this.context = context;
        model = new WeatherInfoModelImpl(context);
    }


//    @Override
    public void fetchCityList(RequestCompleteListener<List<City>> callback) {
//
        Log.i("DEBUG", ">>>>>> FETCH CITY LIST");
        model.getCityList(new RequestCompleteListener<List<City>>() {
//            @Override
            public void onRequestSuccess(List<City> data) {
                Log.i("DEBUG", ">>>>>> HERE");
                callback.onRequestSuccess(data);
//                INSTEAD IF WE WERE TO USE view.onRequestSuccess(data) THEN ITS AN ERROR BECAUSE
//                THEN IT CREATED A NEW INSTANCE OF VIEW AND IS CALLING NEW ONREQUESTSUCCESS METHOD
//                WHICH WILL NEVER WORK UNTIL THAT NEW VIEWS ACTIVITY IS CREATED
//                BUT IN THIS CASE IT WOULD NEVER HAD BEEN CREATED. SO WHAT I DID SET ANOTHER CALLBACK
//                FROM (VIEW TO PRESENTER) AS LIKE FROM (PRESENTER TO MODEL) TO WORK ON THE
//                SAME VIEW AND ACTIVITY.
            }

//            @Override
            public void onRequestFailed(String errorMessage) {
                callback.onRequestFailed(errorMessage);
            }
        });
    }

//    @Override
    public void fetchWeatherInfo(int cityId, RequestCompleteListener<WeatherDataModel> callback) {
//        view.handleProgressBarVisibility(View.VISIBLE);

        model.getWeatherInformation(cityId, new RequestCompleteListener<WeatherInfoResponse>() {
//            @Override
            public void onRequestSuccess(WeatherInfoResponse data) {
//                view.handleProgressBarVisibility(View.GONE);
                WeatherDataModel weatherDataModel = new WeatherDataModel();
                weatherDataModel.setHumidity(data.getMain().getHumidity().toString());
                weatherDataModel.setPressure(data.getMain().getPressure().toString());
                weatherDataModel.setTemperature(data.getMain().getTemp().toString());
                weatherDataModel.setVisibility(data.getVisibility().toString());

                callback.onRequestSuccess(weatherDataModel);
            }

//            @Override
            public void onRequestFailed(String errorMessage) {
                callback.onRequestFailed(errorMessage);
            }
        });
    }

//    @Override
    public void detachView() {
        view = null;
    }
}
