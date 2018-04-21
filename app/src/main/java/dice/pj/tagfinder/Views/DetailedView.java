package dice.pj.tagfinder.Views;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import dice.pj.tagfinder.Controller.DetailedController;
import dice.pj.tagfinder.R;

public class DetailedView extends AppCompatActivity
{

    private ProgressBar progressBar;
    private TextView mainText;
    private TextView subTitle;
    private DetailedController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        setContentView(R.layout.activity_detailed_view);

        Toolbar mTopToolbar=(Toolbar)findViewById(R.id.my_toolbar);
        progressBar=findViewById(R.id.progressBar2);
        mainText=findViewById(R.id.mainText);
        subTitle=findViewById(R.id.subTitle);
        TextView toolbarText=findViewById(R.id.Title);

        mTopToolbar.setPadding(0,0,0,0);
        mTopToolbar.setContentInsetsAbsolute(0,0);

        mainText.setTextIsSelectable(true);
        mainText.setMovementMethod(new ScrollingMovementMethod());

        //get name of tag from intent and set to heading
        if(getIntent().hasExtra("value"))
        {
            String value=getIntent().getExtras().get("value").toString();
            toolbarText.setText(value);
            new getDetail().execute(value);// get quotes from tag
        }

    }
    private void setController(DetailedController controller)
    {
        this.controller=controller;
    }
    public class getDetail extends AsyncTask<String,Void,DetailedController>
    {
        @Override
        protected  void onPreExecute()
        {

        }
        @Override
        protected DetailedController doInBackground(String ... strings)
        {
            DetailedController controller= new DetailedController(getApplicationContext(),strings[0]);
            return controller;
        }
        protected void onPostExecute(DetailedController controller)
        {
            setController(controller);
            progressBar.setVisibility(View.INVISIBLE);
            mainText.setText(controller.getValues());
            String numberOfQuotes= controller.getCount();

            if(numberOfQuotes.equals("1"))
            {
                subTitle.setText(numberOfQuotes+" Quote Found");
            }
            else
            {
                subTitle.setText(numberOfQuotes+" Quotes Found");
            }

        }
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
    @Override
    public void onPause()
    {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        finish();
    }
    @Override
    public void onStop()
    {
        super.onStop();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        finish();
    }
}
