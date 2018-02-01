package worldline.ssm.rd.ux.wltwitter.interfaces;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by qkame on 25/01/2018.
 */

/**
 * Interface between a fragment and an activity
 */
public interface TweetChangeListener {

    public void onTweetRetrieved(List<Tweet> tweets);

}
