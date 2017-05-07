// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import android.view.LayoutInflater;
import com.netflix.mediaclient.Log;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.WindowManager$LayoutParams;
import android.view.Window;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.view.View;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import android.widget.RatingBar$OnRatingBarChangeListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class RatingDialogFrag extends NetflixDialogFrag implements RatingBar$OnRatingBarChangeListener, MdxMiniPlayerFrag$MdxMiniPlayerDialog
{
    public static final String INTENT_NAME = "ui_rating";
    public static final String PARAM_RATING = "rating";
    public static final String PARAM_RATING_USER = "rating_user";
    public static final String PARAM_VIDEO_ID = "videoId";
    public static final String PARAM_VIDEO_TITLE = "videoTitle";
    private static final String TAG = "RatingDialogFrag";
    private final View anchor;
    private boolean autoDismiss;
    private boolean mIsUserRating;
    private float mRating;
    private RatingBar mRatingBar;
    private ViewGroup mRatingGroup;
    private TextView mTitle;
    private String mVideoId;
    private String mVideoTitle;
    private final int resId;
    
    RatingDialogFrag(final View anchor, final int resId) {
        this.autoDismiss = true;
        this.resId = resId;
        this.anchor = anchor;
    }
    
    private void alignViewsToAnchor() {
        if (this.anchor != null && this.mRatingGroup != null) {
            final Window window = this.getDialog().getWindow();
            window.setGravity(51);
            final WindowManager$LayoutParams attributes = window.getAttributes();
            final int[] array = new int[2];
            this.anchor.getLocationOnScreen(array);
            attributes.x = array[0];
            attributes.y = (int)(array[1] - this.anchor.getResources().getDimension(2131362004));
            window.setAttributes(attributes);
        }
    }
    
    private void findViews(final View view, final int n) {
        this.mRatingBar = (RatingBar)view.findViewById(n);
        this.mTitle = (TextView)view.findViewById(2131165561);
        this.mRatingGroup = (ViewGroup)view.findViewById(2131165450);
    }
    
    @SuppressLint({ "InlinedApi" })
    public static RatingDialogFrag newInstance(final RatingDialogFrag$Rating ratingDialogFrag$Rating, final String s, final String s2, final View view, int n) {
        if (s == null) {
            throw new IllegalArgumentException("Playable ID can not be null!");
        }
        if (n <= 0) {
            n = 2130903162;
        }
        final RatingDialogFrag ratingDialogFrag = new RatingDialogFrag(view, n);
        ratingDialogFrag.setStyle(1, 0);
        final Bundle arguments = new Bundle();
        ratingDialogFrag.setArguments(arguments);
        arguments.putFloat("rating", ratingDialogFrag$Rating.value);
        arguments.putBoolean("rating_user", ratingDialogFrag$Rating.user);
        arguments.putString("videoId", s);
        arguments.putString("videoTitle", s2);
        return ratingDialogFrag;
    }
    
    private void setupRatingsBar() {
        if (this.mRatingBar != null) {
            this.mRatingBar.setOnRatingBarChangeListener((RatingBar$OnRatingBarChangeListener)this);
            this.mRatingBar.setRating(this.mRating);
            this.mRatingBar.setVisibility(0);
        }
    }
    
    private void setupTitle() {
        if (this.mTitle != null) {
            this.mTitle.setText((CharSequence)this.getString(2131493243, new Object[] { this.mVideoTitle }));
        }
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
        if (Log.isLoggable()) {
            Log.v("RatingDialogFrag", String.format("mVideoId: %s, mVideoTitle: %s, mRating: %f, mIsUserRating: %b", this.mVideoId, this.mVideoTitle, this.mRating, this.mIsUserRating));
        }
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(this.resId, viewGroup, false);
        int n = 2131165451;
        if (this.mIsUserRating) {
            n = 2131165452;
        }
        this.findViews(inflate, n);
        this.setupRatingsBar();
        this.setupTitle();
        this.alignViewsToAnchor();
        return inflate;
    }
    
    public void onRatingChanged(final RatingBar ratingBar, final float n, final boolean b) {
        if (Log.isLoggable()) {
            Log.d("RatingDialogFrag", "User changed rating: " + b + ", new rating: " + n);
        }
        if (b) {
            final Activity activity = this.getActivity();
            if (activity == null) {
                Log.e("RatingDialogFrag", "Activity is NULL, we can update rating!");
                return;
            }
            final Intent intent = new Intent("ui_rating");
            intent.addCategory("LocalIntentNflxUi");
            intent.putExtra("rating", n);
            intent.putExtra("videoId", this.mVideoId);
            LocalBroadcastManager.getInstance((Context)activity).sendBroadcast(intent);
            if (this.autoDismiss) {
                this.dismissAllowingStateLoss();
                this.getFragmentManager().beginTransaction().remove((Fragment)this).commit();
            }
        }
    }
    
    public void setAutoDismiss(final boolean autoDismiss) {
        this.autoDismiss = autoDismiss;
    }
}
