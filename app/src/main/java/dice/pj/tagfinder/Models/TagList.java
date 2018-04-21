package dice.pj.tagfinder.Models;

import com.google.gson.annotations.SerializedName;

import dice.pj.tagfinder.Controller.MainController;
import dice.pj.tagfinder.Interfaces.MainInterface;

public class TagList implements MainInterface
{

    @SerializedName("count")  private int count;
    @SerializedName("total")  private int total;
    @SerializedName("_embedded") private String[] titles;
    @SerializedName("_links") private Links links ;

    @Override
    public int getCount()
    {
        return count;
    }
    public String[] getTitles()
    {
        return titles;
    }
    static class Links
    {
        @SerializedName("self") private Href href;
    }
    static class Href
    {
        @SerializedName("href") private String href;
    }

}
