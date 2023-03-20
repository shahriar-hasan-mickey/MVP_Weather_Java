package humble.slave.mvp_weather_java.presenter;

import java.util.List;

import humble.slave.mvp_weather_java.common.RequestCompleteListener;
import humble.slave.mvp_weather_java.model.data.City;
import humble.slave.mvp_weather_java.model.data.WeatherDataModel;
import humble.slave.mvp_weather_java.model.data.WeatherInfoResponse;

public interface WeatherInfoShowPresenter {
    public void fetchCityList();
    public void fetchWeatherInfo(int cityId);

//    public void fetchCityList(RequestCompleteListener<List<City>> callback);
//    public void fetchWeatherInfo(int cityId, RequestCompleteListener<WeatherDataModel> callback);
    public void detachView();
}
