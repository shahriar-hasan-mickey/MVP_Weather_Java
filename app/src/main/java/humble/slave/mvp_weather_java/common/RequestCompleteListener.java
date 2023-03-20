package humble.slave.mvp_weather_java.common;

public interface RequestCompleteListener <T>{
    void onRequestSuccess(T data);
    void onRequestFailed(String errorMessage);
}
