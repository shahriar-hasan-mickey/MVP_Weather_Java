package humble.slave.mvp_weather_java.model.data;

public class WeatherDataModel {
    String dateTime = "";
    String temperature = "0";
    String cityAndCountry = "";
    String weatherConditionIconUrl = "";
    String weatherConditionIconDescription = "";
    String humidity = "";
    String pressure = "";
    String visibility = "";
    String sunrise = "";
    String sunset = "";



    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCityAndCountry() {
        return cityAndCountry;
    }

    public void setCityAndCountry(String cityAndCountry) {
        this.cityAndCountry = cityAndCountry;
    }

    public String getWeatherConditionIconUrl() {
        return weatherConditionIconUrl;
    }

    public void setWeatherConditionIconUrl(String weatherConditionIconUrl) {
        this.weatherConditionIconUrl = weatherConditionIconUrl;
    }

    public String getWeatherConditionIconDescription() {
        return weatherConditionIconDescription;
    }

    public void setWeatherConditionIconDescription(String weatherConditionIconDescription) {
        this.weatherConditionIconDescription = weatherConditionIconDescription;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}