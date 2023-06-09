package humble.slave.mvp_weather_java.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import humble.slave.mvp_weather_java.databinding.ActivityMainBinding;
import humble.slave.mvp_weather_java.model.WeatherInfoModel;
import humble.slave.mvp_weather_java.model.WeatherInfoModelImpl;
import humble.slave.mvp_weather_java.model.data.City;
import humble.slave.mvp_weather_java.model.data.WeatherDataModel;
import humble.slave.mvp_weather_java.presenter.WeatherInfoShowPresenter;
import humble.slave.mvp_weather_java.presenter.WeatherInfoShowPresenterImpl;

public class MainActivity extends AppCompatActivity implements MainActivityView{

    ActivityMainBinding binding;
    List<City> cityList;
    WeatherInfoShowPresenter presenter;
    WeatherInfoModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        model = new WeatherInfoModelImpl(getApplicationContext());
        presenter = new WeatherInfoShowPresenterImpl(this, model);
        presenter.fetchCityList();



        binding.layoutInput.btnViewWeather.setOnClickListener(view -> {
            binding.outputGroup.setVisibility(View.GONE);
            presenter.fetchWeatherInfo(cityList.get(binding.layoutInput.spinner.getSelectedItemPosition()).getId());
        });
    }

    @Override
    public void handleProgressBarVisibility(int visibility) {
        binding.progressBar.setVisibility(visibility);
    }

    @Override
    public void onCityListFetchSuccess(List<City> cityList) {
        Log.i("DEBUG", ">>>>>> HERE2");
        this.cityList = cityList;

        Log.i("data", cityList.toString());
        ArrayAdapter<String> cityAdapter= new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                convertToCityNameList(cityList)
        );
        Log.i("DEBUG", ">>>>>> AFTER ADAPTER CREATION");
        binding.layoutInput.spinner.setAdapter(cityAdapter);
        Log.i("DEBUG", ">>>>>> AFTER ADAPTER BINDING");
    }

    @Override
    public void onCityListFetchFailure(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWeatherInfoFetchSuccess(WeatherDataModel weatherDataModel) {
        binding.outputGroup.setVisibility(View.VISIBLE);
        binding.tvErrorMessage.setVisibility(View.GONE);

        binding.layoutWeatherBasic.tvDateTime.setText(weatherDataModel.getDateTime());
        binding.layoutWeatherBasic.tvTemperature.setText(weatherDataModel.getTemperature());
        binding.layoutWeatherBasic.tvCityCountry.setText(weatherDataModel.getCityAndCountry());
        Glide.with(this).load(weatherDataModel.getWeatherConditionIconUrl()).into(binding.layoutWeatherBasic.ivWeatherCondition);
        binding.layoutWeatherBasic.tvWeatherCondition.setText(weatherDataModel.getWeatherConditionIconDescription());

        binding.layoutWeatherAdditional.tvHumidityValue.setText(weatherDataModel.getHumidity());
        binding.layoutWeatherAdditional.tvPressureValue.setText(weatherDataModel.getPressure());
        binding.layoutWeatherAdditional.tvVisibilityValue.setText(weatherDataModel.getVisibility());

        binding.layoutSunsetSunrise.tvSunriseTime.setText(weatherDataModel.getSunrise());
        binding.layoutSunsetSunrise.tvSunsetTime.setText(weatherDataModel.getSunset());

    }

    @Override
    public void onWeatherInfoFetchFailure(String errorMessage) {
        binding.outputGroup.setVisibility(View.GONE);
        binding.tvErrorMessage.setVisibility(View.VISIBLE);
        binding.tvErrorMessage.setText(errorMessage);
    }


    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();

    }

    // TODO : CONVERT-TO-CITY-NAME-LIST
    private List<String> convertToCityNameList(List<City> city_list){

        List<String> cityNameList = new ArrayList<>();
        for(City i : city_list){
            cityNameList.add(i.getName());
        }
        Log.i("DEBUG", ">>>>>> IN CONVERT TO CITY NAME LIST");
        return cityNameList;

    }
}