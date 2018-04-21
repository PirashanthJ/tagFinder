package dice.pj.tagfinder.Views;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


import dice.pj.tagfinder.Controller.MainController;
import dice.pj.tagfinder.R;

public class SplashScreen extends AppCompatActivity
{

    private ImageView diceLogo;
    private ProgressBar progressBar;
    private AnimatorSet animatorSet;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);

        diceLogo=findViewById(R.id.dice);
        progressBar=findViewById(R.id.progressBar);

        handleAnimation(diceLogo);
       // new getTags().execute();


    }
    public void handleAnimation(View view)
    {
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(diceLogo, "y", 665f);
        animatorY.setDuration(2000);

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(diceLogo, "x", 350f);
        animatorX.setDuration(2000);

        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(diceLogo, View.ALPHA, 1.0F, 0.0F);
        alphaAnimation.setDuration(1000);

        ObjectAnimator rotate = ObjectAnimator.ofFloat(diceLogo, "rotation", 0F, 360F);
        rotate.setDuration(1000);

        animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorX, animatorY);
        animatorSet.addListener(new Animator.AnimatorListener()
        {
            @Override
            public void onAnimationStart(Animator animator)
            {
                progressBar.setVisibility(View.VISIBLE);
                diceLogo.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                new getTags().execute();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorSet.start();
    }
    public class getTags extends AsyncTask<Void,Void,MainController>
    {
        @Override
        protected  void onPreExecute()
        {
            checkConnection();
        }
        @Override
        protected MainController doInBackground(Void... voids)
        {
            //initialise main controller which gets tag names from API
            MainController controller= new MainController(getApplicationContext());
            return controller;
        }
        protected void onPostExecute(MainController controller)
        {
            if(isNetworkConnected())
            {
                //pass array of tags to Main activity as intent
                Intent intent= new Intent(getApplicationContext(),MainView.class);
                Bundle bundle= new Bundle();
                bundle.putStringArray("list",controller.getTags());
                intent.putExtra("bundle",bundle);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Check your internet connection",Toast.LENGTH_SHORT).show();
                while(!isNetworkConnected())
                {
                    if(isNetworkConnected())
                    {
                        new getTags().execute();
                    }
                }
            }
        }
    }
    @Override
    public void onStop()
    {
        super.onStop();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        finish();
    }
    @Override
    public void onPause()
    {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        finish();
    }
    private boolean isNetworkConnected()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    private void checkConnection()
    {
        boolean isConnected = isNetworkConnected();
        if(!isConnected)
        {
            Toast.makeText(getApplicationContext(),"Check your internet connection",Toast.LENGTH_SHORT).show();
        }
    }
}
