// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.content.Intent;
import java.util.Iterator;
import android.os.Parcelable;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.app.Fragment;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

public class MovieDetailsActivity extends DetailsActivity implements ManagerStatusListener
{
    private static final String EXTRA_BACK_STACK = "extra_back_stack";
    private static final String TAG = "MovieDetailsActivity";
    private final ArrayList<MovieDetailsActivity$BackStackData> backStack;
    private ServiceManager manager;
    private boolean showDetailsFragInFuture;
    
    public MovieDetailsActivity() {
        this.backStack = new ArrayList<MovieDetailsActivity$BackStackData>();
    }
    
    private void handleNewVideoId() {
        if (StringUtils.isNotEmpty(this.getVideoId())) {
            final MovieDetailsActivity$BackStackData movieDetailsActivity$BackStackData = new MovieDetailsActivity$BackStackData(this.getVideoId(), this.getPlayContext(), ((MovieDetailsFrag)this.getPrimaryFrag()).getScrollYOffset());
            if (Log.isLoggable()) {
                Log.v("MovieDetailsActivity", "Adding curr video to back stack: " + movieDetailsActivity$BackStackData);
            }
            this.backStack.add(movieDetailsActivity$BackStackData);
        }
        this.setVideoId(this.getIntent().getStringExtra("extra_video_id"));
        this.setPlayContext((PlayContext)this.getIntent().getParcelableExtra("extra_playcontext"));
        this.setAction((DetailsActivity$Action)this.getIntent().getSerializableExtra("extra_action"), this.getIntent().getStringExtra("extra_action_token"));
    }
    
    private void showNewDetailsFrag(final int scrollYOffset) {
        if (this.manager != null) {
            this.setPrimaryFrag(this.createPrimaryFrag());
            ((MovieDetailsFrag)this.getPrimaryFrag()).setScrollYOffset(scrollYOffset);
            this.getFragmentManager().beginTransaction().replace(2131624200, this.getPrimaryFrag(), "primary").setTransition(4099).commit();
            this.getFragmentManager().executePendingTransactions();
            ((ManagerStatusListener)this.getPrimaryFrag()).onManagerReady(this.manager, CommonStatus.OK);
        }
    }
    
    @Override
    protected Fragment createPrimaryFrag() {
        return MovieDetailsFrag.create(this.getVideoId());
    }
    
    @Override
    public VideoType getVideoType() {
        return VideoType.MOVIE;
    }
    
    @Override
    protected boolean handleBackPressed() {
        Log.v("MovieDetailsActivity", "Back pressed, backStack size: " + this.backStack.size());
        if (this.backStack.size() > 0) {
            final MovieDetailsActivity$BackStackData movieDetailsActivity$BackStackData = this.backStack.remove(this.backStack.size() - 1);
            this.setVideoId(movieDetailsActivity$BackStackData.videoId);
            this.setPlayContext(movieDetailsActivity$BackStackData.playContext);
            if (Log.isLoggable()) {
                Log.v("MovieDetailsActivity", "Video id from back stack: " + movieDetailsActivity$BackStackData);
            }
            this.showNewDetailsFrag(movieDetailsActivity$BackStackData.scrollYOffset);
            return true;
        }
        Log.v("MovieDetailsActivity", "No more videos in back stack, finishing...");
        return false;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        if (bundle != null) {
            final Iterator<Parcelable> iterator = bundle.getParcelableArrayList("extra_back_stack").iterator();
            while (iterator.hasNext()) {
                this.backStack.add((MovieDetailsActivity$BackStackData)iterator.next());
            }
        }
        this.handleNewVideoId();
        super.onCreate(bundle);
    }
    
    @Override
    public void onManagerReady(final ServiceManager manager, final Status status) {
        super.onManagerReady(manager, status);
        this.manager = manager;
        if (this.showDetailsFragInFuture && manager != null) {
            this.showNewDetailsFrag(0);
            this.showDetailsFragInFuture = false;
        }
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        super.onManagerUnavailable(serviceManager, status);
        this.manager = null;
    }
    
    @Override
    protected void onNewIntent(final Intent intent) {
        Log.d("MovieDetailsActivity", "onNewIntent: ", intent);
        super.onNewIntent(intent);
        this.setIntent(intent);
        this.handleNewVideoId();
        if (this.manager == null) {
            this.showDetailsFragInFuture = true;
            return;
        }
        this.showNewDetailsFrag(0);
    }
    
    @Override
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelableArrayList("extra_back_stack", (ArrayList)this.backStack);
    }
}
