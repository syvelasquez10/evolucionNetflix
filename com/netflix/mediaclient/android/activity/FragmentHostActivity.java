// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import android.app.FragmentTransaction;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup$MarginLayoutParams;
import android.os.Bundle;
import com.netflix.mediaclient.android.app.LoadingStatus;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.ViewGroup;
import android.app.Fragment;
import android.widget.LinearLayout;

public abstract class FragmentHostActivity extends NetflixActivity
{
    public static final String PRIMARY_FRAG_TAG = "primary";
    public static final String SECONDARY_FRAG_TAG = "secondary";
    static final float STANDARD_PRIMARY_FRAG_WEIGHT = 0.6f;
    static final float STANDARD_SECONDARY_FRAG_WEIGHT = 1.0f;
    private static final String TAG = "FragmentHostActivity";
    private LinearLayout contentHost;
    private Fragment primaryFrag;
    private ViewGroup primaryFragContainer;
    private Fragment secondaryFrag;
    private ViewGroup secondaryFragContainer;
    
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
        return 2130903112;
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
        this.contentHost = (LinearLayout)this.findViewById(2131624155);
        this.primaryFragContainer = (ViewGroup)this.findViewById(2131624157);
        if (!this.hasEmbeddedToolbar()) {
            final ViewGroup$LayoutParams layoutParams = this.primaryFragContainer.getLayoutParams();
            if (layoutParams instanceof ViewGroup$MarginLayoutParams) {
                ((ViewGroup$MarginLayoutParams)layoutParams).topMargin = 0;
            }
            else if (Log.isLoggable()) {
                Log.e("FragmentHostActivity", "Can't remove margin from layout of non-supported type: " + layoutParams);
            }
        }
        this.secondaryFragContainer = (ViewGroup)this.findViewById(2131624158);
        if (bundle == null) {
            this.primaryFrag = this.createPrimaryFrag();
            this.secondaryFrag = this.createSecondaryFrag();
            if (Log.isLoggable()) {
                Log.d("FragmentHostActivity", "Creating primary fragment of type: " + this.primaryFrag);
                Log.d("FragmentHostActivity", "Creating secondary fragment of type: " + this.secondaryFrag);
            }
            final FragmentTransaction beginTransaction = this.getFragmentManager().beginTransaction();
            beginTransaction.add(2131624157, this.primaryFrag, "primary");
            if (this.secondaryFrag != null) {
                beginTransaction.add(2131624158, this.secondaryFrag, "secondary");
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
