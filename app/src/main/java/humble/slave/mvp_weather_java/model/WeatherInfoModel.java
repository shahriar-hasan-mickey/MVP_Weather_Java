package humble.slave.mvp_weather_java.model;

import java.util.List;

import humble.slave.mvp_weather_java.common.RequestCompleteListener;
import humble.slave.mvp_weather_java.model.data.City;
import humble.slave.mvp_weather_java.model.data.WeatherInfoResponse;

public interface WeatherInfoModel {
    public void getCityList(RequestCompleteListener<List<City>> callback);
    public void getWeatherInformation(int cityId , RequestCompleteListener<WeatherInfoResponse> callback);
}
