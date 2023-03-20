package humble.slave.mvp_weather_java.presenter;

import android.view.View;

import java.util.List;

import humble.slave.mvp_weather_java.common.RequestCompleteListener;
import humble.slave.mvp_weather_java.model.WeatherInfoModel;
import humble.slave.mvp_weather_java.model.data.City;
import humble.slave.mvp_weather_java.model.data.WeatherDataModel;
import humble.slave.mvp_weather_java.model.data.WeatherInfoResponse;
import humble.slave.mvp_weather_java.view.MainActivity;

public class WeatherInfoShowPresenterImpl implements WeatherInfoShowPresenter{

    private MainActivity view;
    private WeatherInfoModel model;

    public WeatherInfoShowPresenterImpl(MainActivity view, WeatherInfoModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void fetchCityList() {
        model.getCityList(new RequestCompleteListener<List<City>>() {
            @Override
            public void onRequestSuccess(List<City> data) {
                view.onCityListFetchSuccess(data);
            }

            @Override
            public void onRequestFailed(String errorMessage) {
                view.onCityListFetchFailure(errorMessage);
            }
        });
    }

    @Override
    public void fetchWeatherInfo(int cityId) {
        view.handleProgressBarVisibility(View.VISIBLE);

        model.getWeatherInformation(cityId, new RequestCompleteListener<WeatherInfoResponse>() {
            @Override
            public void onRequestSuccess(WeatherInfoResponse data) {
                view.handleProgressBarVisibility(View.GONE);

                WeatherDataModel weatherDataModel = new WeatherDataModel();
                weatherDataModel.setHumidity(data.getMain().getHumidity().toString());
                weatherDataModel.setPressure(data.getMain().getPressure().toString());
                weatherDataModel.setTemperature(data.getMain().getTemp().toString());
                weatherDataModel.setVisibility(data.getVisibility().toString());

                view.onWeatherInfoFetchSuccess(weatherDataModel);
            }

            @Override
            public void onRequestFailed(String errorMessage) {
                view.handleProgressBarVisibility(View.GONE);

                view.onWeatherInfoFetchFailure(errorMessage);
            }
        });
    }

    @Override
    public void detachView() {
        view = null;
    }
}
