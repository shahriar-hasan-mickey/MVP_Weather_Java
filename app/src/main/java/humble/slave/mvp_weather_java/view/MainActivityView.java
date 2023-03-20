package humble.slave.mvp_weather_java.view;

import java.util.List;

import humble.slave.mvp_weather_java.model.data.City;
import humble.slave.mvp_weather_java.model.data.WeatherDataModel;

public interface MainActivityView {

    public void handleProgressBarVisibility(int visibility);
    public void onCityListFetchSuccess(List<City> cityList);
    public void onCityListFetchFailure(String errorMessage);
    public void onWeatherInfoFetchSuccess(WeatherDataModel weatherDataModel);
    public void onWeatherInfoFetchFailure(String errorMessage);
}
