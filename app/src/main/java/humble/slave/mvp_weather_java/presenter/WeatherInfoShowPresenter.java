package humble.slave.mvp_weather_java.presenter;

public interface WeatherInfoShowPresenter {
    void fetchCityList();
    void fetchWeatherInfo(int cityId);

//    public void fetchCityList(RequestCompleteListener<List<City>> callback);
//    public void fetchWeatherInfo(int cityId, RequestCompleteListener<WeatherDataModel> callback);
    void detachView();
}
