package dice.pj.tagfinder.Models;

import com.google.gson.annotations.SerializedName;

import dice.pj.tagfinder.Interfaces.MainInterface;

public class TagDetail implements MainInterface
{
    @SerializedName("count") private int count;
    @SerializedName("total")  private int total;
    @SerializedName("_embedded") private Embedded embedded;

    @Override
    public int getCount()
    {
        return count;
    }

    public Embedded getEmbedded()
    {
        return embedded;
    }
}
