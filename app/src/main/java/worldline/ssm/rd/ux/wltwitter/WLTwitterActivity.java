package worldline.ssm.rd.ux.wltwitter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import worldline.ssm.rd.ux.wltwitter.async.RetrieveTweetsAsyncTask;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.ui.fragments.TweetsFragment;

import static worldline.ssm.rd.ux.wltwitter.WLTwitterLoginActivity.LOGIN;
import static worldline.ssm.rd.ux.wltwitter.WLTwitterLoginActivity.PASSWORD;

public class WLTwitterActivity extends Activity implements TweetListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wltwitter);

        String login = getIntent().getExtras().getString(LOGIN);
        getActionBar().setSubtitle(login);


        if (savedInstanceState == null){
            getFragmentManager().beginTransaction().add(R.id.container, new TweetsFragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.wltwitter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.logout)
        {
            SharedPreferences prefs = this.getSharedPreferences("preferences", MODE_PRIVATE);
            prefs.edit().remove(LOGIN).commit();
            prefs.edit().remove(PASSWORD).commit();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRetweet(Tweet tweet) {

    }

    @Override
    public void onViewTweet(Tweet tweet) {
        Toast.makeText(this, tweet.text, Toast.LENGTH_LONG).show();
    }


}
