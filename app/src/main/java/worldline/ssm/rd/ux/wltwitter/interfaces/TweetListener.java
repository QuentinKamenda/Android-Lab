package worldline.ssm.rd.ux.wltwitter.interfaces;

import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by qkame on 31/01/2018.
 */

public interface TweetListener {

    public void onRetweet(Tweet tweet);
    public void onViewTweet(Tweet tweet);
}
