package humble.slave.mvp_weather_java.model.data;




import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Clouds {

    @SerializedName("all")
    @Expose
    private Integer all;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

}
