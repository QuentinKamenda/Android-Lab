package worldline.ssm.rd.ux.wltwitter.async;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.SQLOutput;
import java.util.List;

import worldline.ssm.rd.ux.wltwitter.helpers.TwitterHelper;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetChangeListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by qkame on 25/01/2018.
 */

public class RetrieveTweetsAsyncTask extends AsyncTask<String, Void, List<Tweet>> {


    private TweetChangeListener mlistener;

    public RetrieveTweetsAsyncTask(TweetChangeListener mlistener) {
        this.mlistener = mlistener;
    }

    @Override
    protected List<Tweet> doInBackground(String... login) {
        if((null != login ) && (login.length >0)){
            return TwitterHelper.getTweetsOfUser(login[0]);
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Tweet> tweets) {
        super.onPostExecute(tweets);

        mlistener.onTweetRetrieved(tweets);
    }
}
