package humble.slave.mvp_weather_java.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("id")
    @Expose
    private int id = 0;
    @Expose
    @SerializedName("name")
    private String name = "";

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    @Expose
    @SerializedName("country")
    private String country = "";
}
