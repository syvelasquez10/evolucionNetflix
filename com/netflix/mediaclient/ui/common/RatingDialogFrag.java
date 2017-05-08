// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.app.Fragment;
import com.netflix.mediaclient.android.app.Status;
import android.view.LayoutInflater;
import android.annotation.SuppressLint;
import java.io.Serializable;
import android.os.Bundle;
import android.view.View;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.falkor.PQL;
import com.netflix.mediaclient.servicemgr.interface_.Ratable;
import com.netflix.mediaclient.Log;
import android.view.WindowManager$LayoutParams;
import android.view.Window;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.widget.TextView;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.details.NetflixRatingBar;
import com.netflix.mediaclient.ui.details.NetflixRatingBar$RatingBarDataProvider;
import com.netflix.mediaclient.ui.mdx.CastPlayerHelper$CastPlayerDialog;
import com.netflix.mediaclient.ui.details.NetflixRatingBar$OnNetflixRatingBarChangeListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class RatingDialogFrag extends NetflixDialogFrag implements NetflixRatingBar$OnNetflixRatingBarChangeListener, CastPlayerHelper$CastPlayerDialog
{
    private static final String PARAM_AUTO_DISMISS = "autoDismiss";
    private static final String PARAM_LAYOUT_ID = "layoutId";
    private static final String PARAM_PARENT_X = "parentX";
    private static final String PARAM_PARENT_Y = "parentY";
    private static final String PARAM_VIDEO_ID = "videoId";
    private static final String PARAM_VIDEO_TITLE = "videoTitle";
    private static final String PARAM_VIDEO_TYPE = "videoType";
    private static final String TAG = "RatingDialogFrag";
    private boolean mAutoDismiss;
    private int mLayoutId;
    private int mParentXLoc;
    private int mParentYLoc;
    private NetflixRatingBar$RatingBarDataProvider mProvider;
    private NetflixRatingBar mRatingBar;
    private ViewGroup mRatingGroup;
    private TextView mTitle;
    private String mVideoId;
    private String mVideoTitle;
    private VideoType mVideoType;
    
    private void alignViewsToAnchor() {
        if (this.mRatingGroup != null) {
            final Window window = this.getDialog().getWindow();
            window.setGravity(8388659);
            final WindowManager$LayoutParams attributes = window.getAttributes();
            attributes.x = this.mParentXLoc;
            attributes.y = (int)(this.mParentYLoc - this.getResources().getDimension(2131427360));
            window.setAttributes(attributes);
        }
    }
    
    private void completeInitIfPossible() {
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager == null) {
            Log.v("RatingDialogFrag", "Can't complete init - service manager is null");
            return;
        }
        if (this.mRatingBar == null) {
            Log.v("RatingDialogFrag", "Can't complete init - rating bar is null");
            return;
        }
        Log.v("RatingDialogFrag", "Updating ratings bar with ratable");
        this.mRatingBar.update(this.mProvider, (Ratable)serviceManager.getBrowse().getModelProxy().getValue(PQL.create(this.mVideoType.getValue(), this.mVideoId, "summary")));
    }
    
    @SuppressLint({ "InlinedApi" })
    public static RatingDialogFrag create(final String s, final VideoType videoType, final String s2, final View view, final int n, final boolean b) {
        if (s == null) {
            throw new IllegalArgumentException("Playable ID can not be null!");
        }
        if (videoType != VideoType.MOVIE && videoType != VideoType.SHOW) {
            throw new IllegalArgumentException("VideoType must be a show or a movie to set rating!");
        }
        final RatingDialogFrag ratingDialogFrag = new RatingDialogFrag();
        ratingDialogFrag.setStyle(1, 0);
        final Bundle arguments = new Bundle();
        ratingDialogFrag.setArguments(arguments);
        arguments.putString("videoId", s);
        arguments.putSerializable("videoType", (Serializable)videoType);
        arguments.putString("videoTitle", s2);
        arguments.putInt("layoutId", n);
        arguments.putBoolean("autoDismiss", b);
        if (view != null) {
            final int[] array = new int[2];
            view.getLocationOnScreen(array);
            arguments.putInt("parentX", array[0]);
            arguments.putInt("parentY", array[1]);
        }
        return ratingDialogFrag;
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);
        arguments = this.getArguments();
        this.mVideoId = arguments.getString("videoId");
        this.mVideoType = (VideoType)arguments.getSerializable("videoType");
        this.mVideoTitle = arguments.getString("videoTitle");
        this.mAutoDismiss = arguments.getBoolean("autoDismiss");
        this.mLayoutId = arguments.getInt("layoutId");
        this.mParentXLoc = arguments.getInt("parentX");
        this.mParentYLoc = arguments.getInt("parentY");
        this.mProvider = new RatingDialogFrag$1(this);
        if (Log.isLoggable()) {
            Log.v("RatingDialogFrag", String.format("onCreate - mVideoId: %s, mVideoType: %s, mVideoTitle: %s", this.mVideoId, this.mVideoType, this.mVideoTitle));
            Log.v("RatingDialogFrag", String.format("onCreate - mLayoutId: %d, mParentXLoc: %d, mParentYLoc: %d", this.mLayoutId, this.mParentXLoc, this.mParentYLoc));
        }
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(this.mLayoutId, viewGroup, false);
        this.mRatingBar = (NetflixRatingBar)inflate.findViewById(2131821406);
        this.mTitle = (TextView)inflate.findViewById(2131821405);
        this.mRatingGroup = (ViewGroup)inflate.findViewById(2131821407);
        this.mRatingBar.setOnNetflixRatingBarChangeListener(this);
        if (this.mTitle != null) {
            this.mTitle.setText((CharSequence)this.getString(2131296737, new Object[] { this.mVideoTitle }));
        }
        this.alignViewsToAnchor();
        this.completeInitIfPossible();
        return inflate;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        super.onManagerReady(serviceManager, status);
        this.completeInitIfPossible();
    }
    
    @Override
    public void onRatingChanged(final NetflixRatingBar netflixRatingBar, final float n, final boolean b) {
        if (Log.isLoggable()) {
            Log.d("RatingDialogFrag", "User changed rating: " + b + ", new rating: " + n + ", auto dismiss: " + this.mAutoDismiss);
        }
        if (b && this.mAutoDismiss) {
            this.dismissAllowingStateLoss();
            this.getFragmentManager().beginTransaction().remove((Fragment)this).commit();
        }
    }
}
