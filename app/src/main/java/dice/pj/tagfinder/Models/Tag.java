package dice.pj.tagfinder.Models;

import com.google.gson.annotations.SerializedName;

public class Tag
{
    @SerializedName("value") String value;

    public String getValue()
    {
        return value;
    }

}
