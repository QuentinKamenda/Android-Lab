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


    private TweetChangeListener mListener;

    public RetrieveTweetsAsyncTask(TweetChangeListener mlistener) {
        this.mListener = mListener;
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

        for (int i = 0; i < tweets.size(); i++){
            //System.out.println(tweets.get(i).text);
            Log.d("TwitterAsyncTask", tweets.get(i).text);
        }

        if (null != mListener && null != tweets){
            mListener.onTweetRetrieved(tweets);
        }
    }
}
