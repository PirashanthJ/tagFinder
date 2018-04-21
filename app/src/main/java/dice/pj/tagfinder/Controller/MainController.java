package dice.pj.tagfinder.Controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import dice.pj.tagfinder.Models.TagList;
import dice.pj.tagfinder.Other.HttpRequest;
import dice.pj.tagfinder.Views.SplashScreen;

/**
 * Created by pj on 20/04/2018.
 */

public class MainController implements Serializable
{
    TagList tags;
    private Context context;
    public MainController(Context context)
    {
        this.context=context;

        try
        {
            // retrieve list of all tags
            String response = HttpRequest.get("https://api.tronalddump.io/tag")
                    .body();

            Gson gson = new Gson();
            tags=new TagList();
            tags = gson.fromJson(response, TagList.class);

        }
        catch (Exception e)
        {
            Log.d("error", e.getMessage());
            Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
            ((SplashScreen)(context)).finish();
        }
    }
    public String[] getTags()
    {
        return tags.getTitles();
    }
}
