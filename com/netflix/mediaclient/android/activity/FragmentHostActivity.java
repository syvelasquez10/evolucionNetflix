// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup$MarginLayoutParams;
import com.netflix.mediaclient.android.app.LoadingStatus;
import com.netflix.mediaclient.ui.details.IHandleBackPress;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.util.DeviceUtils;
import android.app.FragmentTransaction;
import android.view.View;
import com.netflix.mediaclient.ui.mdx.MiniPlayerFactory;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.ui.kubrick.BarkerUtils;
import android.content.Context;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.os.Bundle;
import android.view.ViewGroup;
import android.app.Fragment;
import android.widget.LinearLayout;

public abstract class FragmentHostActivity extends NetflixActivity
{
    public static final String MINIPLAYER_FRAG_TAG = "mini_player";
    public static final String PRIMARY_FRAG_TAG = "primary";
    public static final String SECONDARY_FRAG_TAG = "secondary";
    static final float STANDARD_PRIMARY_FRAG_WEIGHT = 0.6f;
    static final float STANDARD_SECONDARY_FRAG_WEIGHT = 1.0f;
    private static final String TAG = "FragmentHostActivity";
    private LinearLayout contentHost;
    private Fragment miniPlayerFrag;
    private Fragment primaryFrag;
    private ViewGroup primaryFragContainer;
    private Fragment secondaryFrag;
    private ViewGroup secondaryFragContainer;
    
    private void setupMiniPlayerFrag(final Bundle bundle) {
        final View viewById = this.findViewById(2131689972);
        if (viewById == null) {
            return;
        }
        if (viewById != null && PersistentConfig.inAnyMementoTest((Context)this)) {
            viewById.getLayoutParams().width = BarkerUtils.getDetailsPageContentWidth((Context)this);
        }
        if (!BrowseExperience.shouldShowMemento((Context)this) || (BrowseExperience.shouldShowMemento((Context)this) && bundle == null)) {
            final FragmentTransaction beginTransaction = this.getFragmentManager().beginTransaction();
            this.miniPlayerFrag = MiniPlayerFactory.createMiniPlayer((Context)this);
            if (this.miniPlayerFrag != null) {
                beginTransaction.replace(2131689972, this.miniPlayerFrag, "mini_player");
            }
            beginTransaction.commit();
            return;
        }
        this.miniPlayerFrag = this.getFragmentManager().findFragmentByTag("mini_player");
    }
    
    protected void configureLinearLayout() {
        final LinearLayout contentHost = this.contentHost;
        int orientation;
        if (DeviceUtils.getBasicScreenOrientation((Context)this) == 2) {
            orientation = 0;
        }
        else {
            orientation = 1;
        }
        contentHost.setOrientation(orientation);
        final LinearLayout$LayoutParams layoutParams = (LinearLayout$LayoutParams)this.primaryFragContainer.getLayoutParams();
        layoutParams.weight = 0.6f;
        this.primaryFragContainer.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        if (this.secondaryFrag != null) {
            final LinearLayout$LayoutParams layoutParams2 = (LinearLayout$LayoutParams)this.secondaryFragContainer.getLayoutParams();
            layoutParams2.weight = 1.0f;
            this.secondaryFragContainer.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
        }
    }
    
    protected abstract Fragment createPrimaryFrag();
    
    protected Fragment createSecondaryFrag() {
        return null;
    }
    
    protected ViewGroup getContentHost() {
        return (ViewGroup)this.contentHost;
    }
    
    protected int getContentLayoutId() {
        return 2130903134;
    }
    
    public Fragment getPrimaryFrag() {
        return this.primaryFrag;
    }
    
    protected ViewGroup getPrimaryFragContainer() {
        return this.primaryFragContainer;
    }
    
    protected Fragment getSecondaryFrag() {
        return this.secondaryFrag;
    }
    
    protected ViewGroup getSecondaryFragContainer() {
        return this.secondaryFragContainer;
    }
    
    @Override
    protected boolean handleBackPressed() {
        return this.getMdxMiniPlayerFrag() instanceof IHandleBackPress && this.getMdxMiniPlayerFrag().handleBackPressed();
    }
    
    protected boolean hasEmbeddedToolbar() {
        return true;
    }
    
    @Override
    public boolean isLoadingData() {
        final boolean loadingData = ((LoadingStatus)this.primaryFrag).isLoadingData();
        if (this.secondaryFrag != null) {
            return ((LoadingStatus)this.secondaryFrag).isLoadingData() | loadingData;
        }
        return loadingData;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(this.getContentLayoutId());
        this.contentHost = (LinearLayout)this.findViewById(2131689753);
        this.primaryFragContainer = (ViewGroup)this.findViewById(2131689755);
        if (!this.hasEmbeddedToolbar()) {
            final ViewGroup$LayoutParams layoutParams = this.primaryFragContainer.getLayoutParams();
            if (layoutParams instanceof ViewGroup$MarginLayoutParams) {
                ((ViewGroup$MarginLayoutParams)layoutParams).topMargin = 0;
            }
            else if (Log.isLoggable()) {
                Log.e("FragmentHostActivity", "Can't remove margin from layout of non-supported type: " + layoutParams);
            }
        }
        this.secondaryFragContainer = (ViewGroup)this.findViewById(2131689756);
        this.setupMiniPlayerFrag(bundle);
        if (bundle == null) {
            this.primaryFrag = this.createPrimaryFrag();
            this.secondaryFrag = this.createSecondaryFrag();
            if (Log.isLoggable()) {
                Log.d("FragmentHostActivity", "Creating primary fragment of type: " + this.primaryFrag);
                Log.d("FragmentHostActivity", "Creating secondary fragment of type: " + this.secondaryFrag);
            }
            final FragmentTransaction beginTransaction = this.getFragmentManager().beginTransaction();
            beginTransaction.add(2131689755, this.primaryFrag, "primary");
            if (this.secondaryFrag != null) {
                beginTransaction.add(2131689756, this.secondaryFrag, "secondary");
            }
            beginTransaction.commit();
        }
        else {
            this.primaryFrag = this.getFragmentManager().findFragmentByTag("primary");
            this.secondaryFrag = this.getFragmentManager().findFragmentByTag("secondary");
        }
        if (this.contentHost != null) {
            this.configureLinearLayout();
        }
        if (this.secondaryFragContainer != null) {
            final ViewGroup secondaryFragContainer = this.secondaryFragContainer;
            int visibility;
            if (this.secondaryFrag == null) {
                visibility = 8;
            }
            else {
                visibility = 0;
            }
            secondaryFragContainer.setVisibility(visibility);
        }
    }
    
    @Override
    public void setLoadingStatusCallback(final LoadingStatus$LoadingStatusCallback loadingStatusCallback) {
        super.setLoadingStatusCallback(loadingStatusCallback);
        if (this.primaryFrag != null) {
            ((LoadingStatus)this.primaryFrag).setLoadingStatusCallback(loadingStatusCallback);
        }
        if (this.secondaryFrag != null) {
            ((LoadingStatus)this.secondaryFrag).setLoadingStatusCallback(loadingStatusCallback);
        }
    }
    
    protected void setPrimaryFrag(final Fragment primaryFrag) {
        this.primaryFrag = primaryFrag;
    }
}
