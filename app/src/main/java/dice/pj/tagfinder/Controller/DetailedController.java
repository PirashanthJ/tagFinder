package dice.pj.tagfinder.Controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import dice.pj.tagfinder.Models.Tag;
import dice.pj.tagfinder.Models.TagDetail;
import dice.pj.tagfinder.Other.HttpRequest;
import dice.pj.tagfinder.Views.DetailedView;
import dice.pj.tagfinder.Views.SplashScreen;

public class DetailedController
{
    private TagDetail tagDetail;
    private Context context;
    public DetailedController(Context context,String values)
    {
        this.context=context;
        getData(values);
    }
    private void getData(String title)
    {
        //prepare given argument for Http request
        String tagTitle="";
        if(title.contains(" "))
        {
            title.replace(" ","%20");
        }
        tagTitle=title;

        try
        {
            // retrieve list of quotes for given tag
            String response = HttpRequest.get("https://api.tronalddump.io/tag/"+tagTitle)
                    .body();

            Gson gson = new Gson();
            tagDetail=new TagDetail();
            tagDetail = gson.fromJson(response, TagDetail.class);
        }
        catch (Exception e)
        {
            Log.d("error", e.getMessage());
            Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
            ((DetailedView)(context)).finish();
        }
    }
    public String getValues()
    {
        StringBuilder builder=new StringBuilder();
        for(Tag tag: tagDetail.getEmbedded().getTags())
        {
            builder.append(tag.getValue()+"\n\n");
        }
        return builder.toString();
    }
    public String getCount()
    {
        return String.valueOf(tagDetail.getCount());
    }

}
