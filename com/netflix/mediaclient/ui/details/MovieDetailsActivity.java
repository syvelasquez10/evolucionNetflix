// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.content.Intent;
import java.util.Iterator;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.app.Activity;
import com.netflix.mediaclient.util.AndroidUtils;
import android.annotation.TargetApi;
import android.transition.Transition;
import com.netflix.mediaclient.util.gfx.ScaleTransition;
import com.netflix.mediaclient.util.gfx.SlideTransition;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.app.FragmentTransaction;
import android.app.Fragment;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.content.Context;
import com.netflix.mediaclient.util.Coppola1Utils;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView$LayoutManager;
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
            RecyclerView$LayoutManager layoutManager = null;
            if (this.getPrimaryFrag() instanceof ILayoutManager) {
                layoutManager = ((ILayoutManager)this.getPrimaryFrag()).getLayoutManager();
            }
            final MovieDetailsActivity$BackStackData movieDetailsActivity$BackStackData = new MovieDetailsActivity$BackStackData(this.getVideoId(), this.getPlayContext(), layoutManager);
            if (Log.isLoggable()) {
                Log.v("MovieDetailsActivity", "Adding curr video to back stack: " + movieDetailsActivity$BackStackData);
            }
            this.backStack.add(movieDetailsActivity$BackStackData);
        }
        this.setVideoId(this.getIntent().getStringExtra("extra_video_id"));
        this.setPlayContext((PlayContext)this.getIntent().getParcelableExtra("extra_playcontext"));
        this.setAction((DetailsActivity$Action)this.getIntent().getSerializableExtra("extra_action"), this.getIntent().getStringExtra("extra_action_token"));
    }
    
    private void showNewDetailsFrag(final Parcelable layoutManagerSavedState) {
        if (this.manager != null) {
            final Fragment primaryFrag = this.getPrimaryFrag();
            this.setPrimaryFrag(this.createPrimaryFrag());
            ((MovieDetailsFrag)this.getPrimaryFrag()).setLayoutManagerSavedState(layoutManagerSavedState);
            final FragmentTransaction beginTransaction = this.getFragmentManager().beginTransaction();
            this.addBackStackTransitionAnimation(primaryFrag, layoutManagerSavedState != null);
            beginTransaction.replace(2131689743, this.getPrimaryFrag(), "primary");
            if (!Coppola1Utils.shouldInjectPlayerFragment((Context)this)) {
                beginTransaction.setTransition(4099);
            }
            beginTransaction.commit();
            this.getFragmentManager().executePendingTransactions();
            ((ManagerStatusListener)this.getPrimaryFrag()).onManagerReady(this.manager, CommonStatus.OK);
        }
    }
    
    @TargetApi(21)
    protected void addBackStackTransitionAnimation(final Fragment fragment, final boolean b) {
        if (AnimationUtils.isTransitionAnimationSupported()) {
            final SlideTransition exitTransition = new SlideTransition();
            if (this.getPrimaryFrag() != null) {
                final Fragment primaryFrag = this.getPrimaryFrag();
                Object enterTransition;
                if (b) {
                    enterTransition = new ScaleTransition();
                }
                else {
                    enterTransition = exitTransition;
                }
                primaryFrag.setEnterTransition((Transition)enterTransition);
            }
            if (fragment != null && b) {
                fragment.setExitTransition((Transition)exitTransition);
            }
        }
    }
    
    @Override
    protected Fragment createPrimaryFrag() {
        return MovieDetailsFrag.create(this.getVideoId());
    }
    
    @Override
    public boolean destroyed() {
        return AndroidUtils.isActivityFinishedOrDestroyed(this);
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
            if (Log.isLoggable()) {
                Log.v("MovieDetailsActivity", "Back stack data: " + movieDetailsActivity$BackStackData);
            }
            this.setVideoId(movieDetailsActivity$BackStackData.videoId);
            this.setPlayContext(movieDetailsActivity$BackStackData.playContext);
            this.showNewDetailsFrag(movieDetailsActivity$BackStackData.layoutManagerState);
            return true;
        }
        Log.v("MovieDetailsActivity", "No more videos in back stack, finishing...");
        return false;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        if (bundle != null && bundle.containsKey("extra_back_stack")) {
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
            this.showNewDetailsFrag(null);
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
        this.showNewDetailsFrag(null);
    }
    
    @Override
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelableArrayList("extra_back_stack", (ArrayList)this.backStack);
    }
}
