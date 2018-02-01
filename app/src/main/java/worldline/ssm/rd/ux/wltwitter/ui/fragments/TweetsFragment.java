package worldline.ssm.rd.ux.wltwitter.ui.fragments;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.SharedPreferences;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.WLTwitterActivity;
import worldline.ssm.rd.ux.wltwitter.WLTwitterApplication;
import worldline.ssm.rd.ux.wltwitter.async.RetrieveTweetsAsyncTask;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetChangeListener;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;


/**
 * Created by qkame on 25/01/2018.
 */

public class TweetsFragment extends Fragment implements TweetChangeListener {


    ListView mListView;

    private TweetListener mListener;

    public TweetsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);


        //Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_wltwitter, container, false);

        // Get the ListView
        View rootView = inflater.inflate(R.layout.fragment_wltwitter, container, false);

        // Set a Progress Bar as empty view, and display it (set adapter with no element)
        /*
        final ProgressBar progressBar = new ProgressBar(getActivity());
        progressBar.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);
        mListView.setEmptyView(progressBar);
        */

        // Add the view in out content view
        /*
        ViewGroup root = (ViewGroup) rootView.findViewById(R.id.tweetsRootRelativeLayout);
        root.addView(progressBar);
        */

        mListView = (ListView) rootView.findViewById(R.id.tweetsListsView);

        //mListView.setOnClickListener((View.OnClickListener) this);


        return rootView;
    }

    @Override
    public void onTweetRetrieved(List<Tweet> tweets) {

        /*
        for (Tweet tweet:tweets){
            Log.d("result", tweet.text);
        }
        */

        final ArrayAdapter<Tweet> adapter = new ArrayAdapter<Tweet>(getActivity(), android.R.layout.simple_list_item_1, tweets);
        mListView.setAdapter(adapter);

    }

    @Override
    public void onStart() {
        super.onStart();

        final String login = this.getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE).getString("LOGIN", "POTUS");
        if (!TextUtils.isEmpty(login)){
            RetrieveTweetsAsyncTask task = new RetrieveTweetsAsyncTask(this);
            task.execute(login);
        }
    }


    // Bind the Activity
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof TweetListener){
            mListener = (TweetListener) activity;
        }
    }


    public void onItemClick(AdapterView<?> adapter, View view, int position) {

        if (null != mListener){
            final Tweet tweet = (Tweet) adapter.getItemAtPosition(position);
            mListener.onViewTweet(tweet);
        }
    }


}
