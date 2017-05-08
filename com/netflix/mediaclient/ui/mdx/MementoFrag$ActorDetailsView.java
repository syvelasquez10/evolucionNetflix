// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import java.util.Iterator;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.ui.kubrick.BarkerUtils;
import android.widget.ImageView;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.os.Handler;
import android.os.Bundle;
import com.netflix.mediaclient.ui.details.RoleDetailsFrag;
import android.support.design.widget.TabLayout$OnTabSelectedListener;
import com.viewpagerindicator.android.osp.ViewPager$PageTransformer;
import android.support.v4.view.PagerAdapter;
import com.viewpagerindicator.android.osp.ViewPager$OnPageChangeListener;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import android.support.design.widget.TabLayout;
import com.netflix.model.branches.MementoVideoSwatch;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.android.osp.ViewPager;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import java.util.List;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.widget.TextView;
import com.netflix.model.branches.FalkorActorStill;
import android.view.View;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.DeviceUtils;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.android.widget.FocalPointImageView;
import com.netflix.model.branches.FalkorPerson;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.RelativeLayout;

class MementoFrag$ActorDetailsView extends RelativeLayout
{
    private int imageTint;
    final /* synthetic */ MementoFrag this$0;
    
    public MementoFrag$ActorDetailsView(final MementoFrag this$0, final Context context) {
        this.this$0 = this$0;
        super(context);
        this.imageTint = this.this$0.tintColors[0];
        this.init();
    }
    
    private void init() {
        LayoutInflater.from(this.getContext()).inflate(this.getlayoutId(), (ViewGroup)this);
    }
    
    private void updateImage(final int n) {
        final FalkorPerson falkorPerson = this.this$0.actors.get(n);
        final FocalPointImageView focalPointImageView = (FocalPointImageView)this.findViewById(2131689973);
        final View viewById = this.findViewById(2131689974);
        if (focalPointImageView != null) {
            final FalkorActorStill still = this.this$0.getStill(this.this$0.videoId, falkorPerson.detail.getId());
            focalPointImageView.setImageDrawable(null);
            if (still != null) {
                focalPointImageView.setCutomCroppingEnabled(true);
                if (DeviceUtils.isPortrait((Context)this.this$0.getActivity())) {
                    focalPointImageView.setCropPointXOffsetPercent(still.getStillXFocus());
                }
                NetflixActivity.getImageLoader((Context)this.this$0.getActivity()).showImg(focalPointImageView, still.getStillUrl(), IClientLogging$AssetType.boxArt, "MementoActorDetailsView", BrowseExperience.getImageLoaderConfig(), true);
                viewById.setVisibility(0);
                focalPointImageView.clearColorFilter();
                this.this$0.tintIndex = -1;
            }
            else {
                focalPointImageView.setImageResource(2130837597);
                focalPointImageView.setCutomCroppingEnabled(false);
                this.this$0.tintIndex++;
                if (this.this$0.tintIndex >= this.this$0.tintColors.length) {
                    this.this$0.tintIndex = 0;
                }
                focalPointImageView.setColorFilter(this.this$0.tintColors[this.this$0.tintIndex]);
                this.imageTint = this.this$0.tintColors[this.this$0.tintIndex];
                viewById.setVisibility(4);
            }
            focalPointImageView.setOnClickListener((View$OnClickListener)new MementoFrag$ActorDetailsView$1(this, falkorPerson));
        }
    }
    
    public int getImageTint() {
        return this.imageTint;
    }
    
    protected int getlayoutId() {
        return 2130903194;
    }
    
    void updateDetails(final int n) {
        this.updateTitle(n);
        this.updateImage(n);
    }
    
    void updateTitle(final int n) {
        final FalkorPerson falkorPerson = this.this$0.actors.get(n);
        final TextView textView = (TextView)this.findViewById(2131689977);
        if (textView != null) {
            textView.setText((CharSequence)falkorPerson.detail.name);
        }
    }
}
