package humble.slave.mvp_weather_java.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import humble.slave.mvp_weather_java.R;
import humble.slave.mvp_weather_java.common.RequestCompleteListener;
import humble.slave.mvp_weather_java.databinding.ActivityMainBinding;
import humble.slave.mvp_weather_java.model.data.City;
import humble.slave.mvp_weather_java.model.data.WeatherDataModel;
import humble.slave.mvp_weather_java.model.data.WeatherInfoResponse;
import humble.slave.mvp_weather_java.presenter.WeatherInfoShowPresenter;
import humble.slave.mvp_weather_java.presenter.WeatherInfoShowPresenterImpl;

public class MainActivity extends AppCompatActivity implements MainActivityView{

    ActivityMainBinding binding;
    List<City> cityList;
    WeatherInfoShowPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new WeatherInfoShowPresenterImpl(getApplicationContext());
        presenter.fetchCityList(new RequestCompleteListener<List<City>>() {
            @Override
            public void onRequestSuccess(List<City> data) {
                onCityListFetchSuccess(data);
            }

            @Override
            public void onRequestFailed(String errorMessage) {
                onCityListFetchFailure(errorMessage);
            }
        });



        binding.layoutInput.btnViewWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.outputGroup.setVisibility(View.GONE);
                presenter.fetchWeatherInfo(cityList.get(binding.layoutInput.spinner.getSelectedItemPosition()).getId(), new RequestCompleteListener<WeatherDataModel>() {
                    @Override
                    public void onRequestSuccess(WeatherDataModel data) {
                        onWeatherInfoFetchSuccess(data);
                    }

                    @Override
                    public void onRequestFailed(String errorMessage) {
                        onWeatherInfoFetchFailure(errorMessage);
                    }
                });
            }
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

        binding.layoutWeatherBasic.tvTemperature.setText(weatherDataModel.getTemperature());
        binding.layoutWeatherAdditional.tvHumidityValue.setText(weatherDataModel.getHumidity());
        binding.layoutWeatherAdditional.tvPressureValue.setText(weatherDataModel.getPressure());
        binding.layoutWeatherAdditional.tvVisibilityValue.setText(weatherDataModel.getVisibility());

    }

    @Override
    public void onWeatherInfoFetchFailure(String errorMessage) {
        binding.outputGroup.setVisibility(View.GONE);
        binding.tvErrorMessage.setVisibility(View.VISIBLE);
        binding.tvErrorMessage.setText(errorMessage);
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