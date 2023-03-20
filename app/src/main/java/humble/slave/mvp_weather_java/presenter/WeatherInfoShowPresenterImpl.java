package humble.slave.mvp_weather_java.presenter;

import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import humble.slave.mvp_weather_java.common.RequestCompleteListener;
import humble.slave.mvp_weather_java.model.WeatherInfoModel;
import humble.slave.mvp_weather_java.model.data.City;
import humble.slave.mvp_weather_java.model.data.WeatherDataModel;
import humble.slave.mvp_weather_java.model.data.WeatherInfoResponse;
import humble.slave.mvp_weather_java.view.MainActivity;

public class WeatherInfoShowPresenterImpl implements WeatherInfoShowPresenter{

    private MainActivity view;
    private final WeatherInfoModel model;

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
                weatherDataModel.setDateTime(unixTimeStampToDateTimeString(data.getDt()));
                weatherDataModel.setTemperature(kelvinToCelsius(data.getMain().getTemp()));
                weatherDataModel.setCityAndCountry(data.getName() + data.getSys().getCountry());
                weatherDataModel.setWeatherConditionIconUrl("http://openweathermap.org/img/w/"+ data.getWeather().get(0).getIcon().toString()+".png");
                weatherDataModel.setWeatherConditionIconDescription(data.getWeather().get(0).getDescription());
                weatherDataModel.setHumidity(data.getMain().getHumidity().toString()+"%");
                weatherDataModel.setPressure(data.getMain().getPressure().toString()+" mBar");
                weatherDataModel.setVisibility(String.valueOf(data.getVisibility()/1000.0)+" KM");
                weatherDataModel.setSunrise(unixTimeStampToTimeString(data.getSys().getSunrise()));
                weatherDataModel.setSunset(unixTimeStampToTimeString(data.getSys().getSunset()));
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







    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String unixTimeStampToDateTimeString(Integer dt){
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(dt * 1000);

            SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd MMM, yyyy - hh:mm a", Locale.ENGLISH);
            outputDateFormat.setTimeZone(TimeZone.getDefault());
            return outputDateFormat.format(calendar.getTime());
        }catch (Exception e){
            e.printStackTrace();
        }
        return dt.toString();
    }

    public String unixTimeStampToTimeString(Integer dt){
        try{
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(dt * 1000);

            SimpleDateFormat outputDateFormat = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
            outputDateFormat.setTimeZone(TimeZone.getDefault());
            return outputDateFormat.format(calendar.getTime());
        }catch (Exception e){
            e.printStackTrace();
        }
        return dt.toString();
    }

    public String kelvinToCelsius(Double temp){
        return String.valueOf(temp- 273.15);
    }
}
