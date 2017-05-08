// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import java.util.Iterator;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.kubrick.BarkerUtils;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.os.Handler;
import android.os.Bundle;
import com.netflix.mediaclient.ui.details.RoleDetailsFrag;
import android.support.design.widget.TabLayout$OnTabSelectedListener;
import android.support.v4.view.PagerAdapter;
import com.viewpagerindicator.android.osp.ViewPager$OnPageChangeListener;
import android.view.View;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import android.support.design.widget.TabLayout;
import com.netflix.model.branches.FalkorActorStill;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.android.osp.ViewPager;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.model.branches.FalkorPerson;
import java.util.List;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.widget.TextView;
import android.view.View$OnClickListener;
import android.widget.ImageView;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.model.branches.MementoVideoSwatch;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.RelativeLayout;

class MementoFrag$RelatedTitleView extends RelativeLayout
{
    final /* synthetic */ MementoFrag this$0;
    
    public MementoFrag$RelatedTitleView(final MementoFrag this$0, final Context context) {
        this.this$0 = this$0;
        super(context);
        this.init();
    }
    
    private void init() {
        LayoutInflater.from(this.getContext()).inflate(this.getlayoutId(), (ViewGroup)this);
    }
    
    private void updateInsetImage(final int n) {
        final MementoVideoSwatch mementoVideoSwatch = this.this$0.relatedTitles.get(n);
        final AdvancedImageView advancedImageView = (AdvancedImageView)this.findViewById(2131689953);
        if (advancedImageView != null && mementoVideoSwatch != null) {
            NetflixActivity.getImageLoader((Context)this.this$0.getActivity()).showImg(advancedImageView, mementoVideoSwatch.boxArtUrl, IClientLogging$AssetType.boxArt, "MementoRelatedTitleView", BrowseExperience.getImageLoaderConfig(), true);
            this.adjustInsetHeight(advancedImageView);
            if (advancedImageView != null) {
                advancedImageView.setOnClickListener((View$OnClickListener)new MementoFrag$RelatedTitleView$3(this, mementoVideoSwatch));
            }
        }
    }
    
    private void updateMainImage(final int n) {
        final MementoVideoSwatch mementoVideoSwatch = this.this$0.relatedTitles.get(n);
        final AdvancedImageView advancedImageView = (AdvancedImageView)this.findViewById(2131689951);
        if (advancedImageView != null && mementoVideoSwatch != null) {
            NetflixActivity.getImageLoader((Context)this.this$0.getActivity()).showImg(advancedImageView, mementoVideoSwatch.storyArtUrl, IClientLogging$AssetType.boxArt, "MementoRelatedTitleView", BrowseExperience.getImageLoaderConfig(), true);
            advancedImageView.setOnClickListener((View$OnClickListener)new MementoFrag$RelatedTitleView$2(this, mementoVideoSwatch));
        }
    }
    
    protected void adjustInsetHeight(final ImageView imageView) {
        imageView.getLayoutParams().width = (int)(imageView.getLayoutParams().height / 1.43f);
    }
    
    protected int getlayoutId() {
        return 2130903185;
    }
    
    void updateDetails(final int n) {
        this.updateMainImage(n);
        this.updateInsetImage(n);
        this.updateMoreInfo(n);
    }
    
    void updateMoreInfo(final int n) {
        final TextView textView = (TextView)this.findViewById(2131689952);
        final MementoVideoSwatch mementoVideoSwatch = this.this$0.relatedTitles.get(n);
        if (textView != null && mementoVideoSwatch != null) {
            textView.setText((CharSequence)textView.getResources().getString(2131231106, new Object[] { mementoVideoSwatch.collectionName }));
            textView.setTag(2131689496, (Object)mementoVideoSwatch.collectionName);
            textView.setTag(2131689495, (Object)mementoVideoSwatch.collectionId);
            textView.setOnClickListener((View$OnClickListener)new MementoFrag$RelatedTitleView$1(this, textView));
        }
    }
}
