package dice.pj.tagfinder.Views;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.ProgressBar;
import dice.pj.tagfinder.Other.SimpleArrayAdapter;
import dice.pj.tagfinder.R;

public class MainView extends AppCompatActivity
{
    ListView myList;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //find views
        Toolbar mTopToolbar=(Toolbar)findViewById(R.id.my_toolbar);
        myList=findViewById(R.id.myList);
        progressBar=findViewById(R.id.progressBar);

        mTopToolbar.setPadding(0,0,0,0);//for tab otherwise give space in tab
        mTopToolbar.setContentInsetsAbsolute(0,0);

        // gets array of names from splash screen and set to list view
        if(getIntent().hasExtra("bundle"))
        {
            String [] temp= getIntent().getExtras().getBundle("bundle").getStringArray("list");
            if(temp!=null)
            {
                setAdapter(temp);
            }
        }

    }
    private void setAdapter(String[] tagTitles)
    {
        //pass array to custom adapter and set
        SimpleArrayAdapter adapter=new SimpleArrayAdapter(this,tagTitles);
        myList.setAdapter(adapter);
    }
    @Override
    public void onPause()
    {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
    @Override
    public void onStop()
    {
        super.onStop();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
    @Override
    public void onResume()
    {
        super.onResume();
        // need to refresh listview to set colour of all items back to normal
        if(getIntent().hasExtra("bundle"))
        {
            String [] temp= getIntent().getExtras().getBundle("bundle").getStringArray("list");
            if(temp!=null)
            {
                setAdapter(temp);
            }
        }
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
}
