package dice.pj.tagfinder.Other;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import dice.pj.tagfinder.Controller.DetailedController;
import dice.pj.tagfinder.R;
import dice.pj.tagfinder.Views.DetailedView;

public class SimpleArrayAdapter extends ArrayAdapter<String>
{
    private String[] tags;
    public SimpleArrayAdapter (Context context, String [] tags)
    {
        super(context,-1,tags);
        this.tags=tags;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder=null;
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.mylist,parent,false);
            holder= new ViewHolder();
            holder.title=convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)convertView.getTag();
        }

        holder.title.setText(tags[position]);
        final ViewHolder finalHolder = holder;

        holder.title.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(!isNetworkConnected())
                {
                    Toast.makeText(getContext(), "Check your internet connection", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    finalHolder.title.setBackgroundColor(Color.BLUE);
                    Intent intent= new Intent(getContext(),DetailedView.class);
                    intent.putExtra("value", finalHolder.title.getText().toString());
                    getContext().startActivity(intent);
                }
            }
        });

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide);
        convertView.startAnimation(animation);

        return convertView;
    }

    private static class ViewHolder
    {
        TextView title;
    }
    private boolean isNetworkConnected()
    {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

