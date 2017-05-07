// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.RatingBar;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag;
import android.widget.RatingBar$OnRatingBarChangeListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class RatingDialogFrag extends NetflixDialogFrag implements RatingBar$OnRatingBarChangeListener, MdxMiniPlayerDialog
{
    public static final String INTENT_NAME = "ui_rating";
    public static final String PARAM_RATING = "rating";
    public static final String PARAM_RATING_USER = "rating_user";
    public static final String PARAM_VIDEO_ID = "videoId";
    public static final String PARAM_VIDEO_TITLE = "videoTitle";
    private static final String TAG = "mdxui";
    private boolean mIsUserRating;
    private float mRating;
    private RatingBar mRatingBar;
    private String mVideoId;
    private String mVideoTitle;
    
    @SuppressLint({ "InlinedApi" })
    public static RatingDialogFrag newInstance(final Rating rating, final String s, final String s2) {
        if (s == null) {
            throw new IllegalArgumentException("Playable ID can not be null!");
        }
        final RatingDialogFrag ratingDialogFrag = new RatingDialogFrag();
        ratingDialogFrag.setStyle(1, 2131558710);
        final Bundle arguments = new Bundle();
        ratingDialogFrag.setArguments(arguments);
        arguments.putFloat("rating", rating.value);
        arguments.putBoolean("rating_user", rating.user);
        arguments.putString("videoId", s);
        arguments.putString("videoTitle", s2);
        return ratingDialogFrag;
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.mRating = this.getArguments().getFloat("rating");
        this.mIsUserRating = this.getArguments().getBoolean("rating_user");
        this.mVideoId = this.getArguments().getString("videoId");
        this.mVideoTitle = this.getArguments().getString("videoTitle");
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(2130903147, viewGroup, false);
        int n = 2131165528;
        if (this.mIsUserRating) {
            n = 2131165529;
        }
        (this.mRatingBar = (RatingBar)inflate.findViewById(n)).setOnRatingBarChangeListener((RatingBar$OnRatingBarChangeListener)this);
        this.mRatingBar.setRating(this.mRating);
        this.mRatingBar.setVisibility(0);
        ((TextView)inflate.findViewById(2131165527)).setText((CharSequence)this.getString(2131493263, new Object[] { this.mVideoTitle }));
        return inflate;
    }
    
    public void onRatingChanged(final RatingBar ratingBar, final float n, final boolean b) {
        if (Log.isLoggable("mdxui", 3)) {
            Log.d("mdxui", "User change rating " + b + " to " + n);
        }
        if (!b) {
            return;
        }
        final Activity activity = this.getActivity();
        if (activity == null) {
            Log.e("mdxui", "Activity is NULL, we can update rating!");
            return;
        }
        final Intent intent = new Intent("ui_rating");
        intent.addCategory("LocalIntentNflxUi");
        intent.putExtra("rating", n);
        intent.putExtra("videoId", this.mVideoId);
        LocalBroadcastManager.getInstance((Context)activity).sendBroadcast(intent);
        this.dismissAllowingStateLoss();
        this.getFragmentManager().beginTransaction().remove((Fragment)this).commit();
    }
    
    public static class Rating
    {
        public boolean user;
        public float value;
    }
}
