// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.ui.common.SimilarItemsGridViewAdapter;
import android.support.v7.widget.RecyclerView$Adapter;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import com.netflix.mediaclient.android.app.Status;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.support.v7.widget.RecyclerView$LayoutManager;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationEdgePadding;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import com.netflix.model.branches.FalkorVideo;
import java.util.HashMap;
import com.netflix.model.branches.FalkorActorStill;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import com.netflix.mediaclient.servicemgr.AddToListData$StateListener;
import java.util.Map;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.app.LoadingStatus;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.widget.AdvancedImageView$ImageLoaderInfo;
import android.widget.ImageView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.graphics.drawable.Drawable;
import com.netflix.model.branches.FalkorPerson;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.ui.kubrick.BarkerUtils;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.FocalPointImageView;
import com.netflix.mediaclient.android.widget.CircleImageView;
import android.widget.RelativeLayout;

class RoleDetailsFrag$ActorDetailsView extends RelativeLayout
{
    CircleImageView actorHeadshotImg;
    FocalPointImageView actorStillImg;
    TextView alias;
    TextView born;
    TextView bornLabel;
    TextView knownFor;
    TextView knownForLabel;
    TextView name;
    View onNetFlixLabel;
    TextView spouse;
    TextView spouseLabel;
    final /* synthetic */ RoleDetailsFrag this$0;
    
    public RoleDetailsFrag$ActorDetailsView(final RoleDetailsFrag this$0, final Context context) {
        this.this$0 = this$0;
        super(context);
        this.init();
    }
    
    public RoleDetailsFrag$ActorDetailsView(final RoleDetailsFrag this$0, final Context context, final AttributeSet set) {
        this.this$0 = this$0;
        super(context, set);
        this.init();
    }
    
    public RoleDetailsFrag$ActorDetailsView(final RoleDetailsFrag this$0, final Context context, final AttributeSet set, final int n) {
        this.this$0 = this$0;
        super(context, set, n);
        this.init();
    }
    
    private void findViews() {
        this.actorStillImg = (FocalPointImageView)this.findViewById(2131690221);
        this.actorHeadshotImg = (CircleImageView)this.findViewById(2131690222);
        this.knownFor = (TextView)this.findViewById(2131690226);
        this.knownForLabel = (TextView)this.findViewById(2131690225);
        this.spouse = (TextView)this.findViewById(2131690230);
        this.spouseLabel = (TextView)this.findViewById(2131690229);
        this.name = (TextView)this.findViewById(2131690224);
        this.born = (TextView)this.findViewById(2131690228);
        this.bornLabel = (TextView)this.findViewById(2131690227);
        this.onNetFlixLabel = this.findViewById(2131690223);
        this.this$0.caret = this.findViewById(2131690231);
    }
    
    private void init() {
        LayoutInflater.from(this.getContext()).inflate(this.getlayoutId(), (ViewGroup)this, true);
        this.findViews();
        this.setupCaret();
    }
    
    private void setupCaret() {
        if (this.this$0.caret != null) {
            this.this$0.caret.setTranslationX((float)(-(BarkerUtils.getDetailsPageContentWidth((Context)this.this$0.getActivity()) / 2 - this.this$0.getActivity().getResources().getDimensionPixelOffset(2131361852))));
            this.this$0.caret.setOnClickListener((View$OnClickListener)new RoleDetailsFrag$ActorDetailsView$1(this));
        }
    }
    
    private void updateActorHeadshotmage(final FalkorPerson falkorPerson) {
        if (this.actorHeadshotImg != null) {
            if (falkorPerson.detail.getHeadshotImageUrl() == null) {
                this.actorHeadshotImg.setImageDrawable(null);
                return;
            }
            NetflixActivity.getImageLoader((Context)this.this$0.getActivity()).showImg(this.actorHeadshotImg, falkorPerson.detail.getHeadshotImageUrl(), IClientLogging$AssetType.boxArt, "RoleDetailsFrag.ActorDetailsView.actorHeadshotImg", BrowseExperience.getImageLoaderConfig(), true);
        }
    }
    
    private void updateActorStillImage(final FalkorPerson falkorPerson) {
        final View viewById = this.findViewById(2131689947);
        if (this.actorStillImg != null) {
            this.adjustHeight(this.actorStillImg);
            this.actorStillImg.setImageLoaderInfo(null);
            if (this.this$0.still == null) {
                this.actorStillImg.setImageResource(2130837576);
                this.actorStillImg.setCutomCroppingEnabled(false);
                this.actorStillImg.setColorFilter(this.this$0.imageTint);
                viewById.setVisibility(4);
                return;
            }
            this.actorStillImg.setImageDrawable(null);
            this.actorStillImg.setCutomCroppingEnabled(true);
            if (DeviceUtils.isPortrait((Context)this.this$0.getActivity())) {
                this.actorStillImg.setCropPointXOffsetPercent(this.this$0.still.getStillXFocus());
            }
            NetflixActivity.getImageLoader((Context)this.this$0.getActivity()).showImg(this.actorStillImg, this.this$0.still.getStillUrl(), IClientLogging$AssetType.boxArt, "RoleDetailsFrag.ActorDetailsView.actorStillImg", BrowseExperience.getImageLoaderConfig(), true);
            viewById.setVisibility(0);
            this.actorStillImg.clearColorFilter();
        }
    }
    
    private void updateBorn(final FalkorPerson falkorPerson) {
        if (this.born != null) {
            if (!StringUtils.isNotEmpty(falkorPerson.detail.getBorn())) {
                this.born.setVisibility(8);
                this.bornLabel.setVisibility(8);
                return;
            }
            this.born.setText((CharSequence)falkorPerson.detail.getBorn());
            this.born.setVisibility(0);
            this.bornLabel.setVisibility(0);
        }
    }
    
    private void updateKnownFor(final FalkorPerson falkorPerson) {
        if (this.knownFor != null) {
            if (!StringUtils.isNotEmpty(falkorPerson.detail.getKnownFor())) {
                this.knownForLabel.setVisibility(8);
                this.knownFor.setVisibility(8);
                return;
            }
            this.knownFor.setText((CharSequence)falkorPerson.detail.getKnownFor());
            this.knownForLabel.setVisibility(0);
            this.knownFor.setVisibility(0);
        }
    }
    
    private void updateName(final FalkorPerson falkorPerson) {
        if (this.name != null) {
            this.name.setText((CharSequence)falkorPerson.detail.getName());
        }
    }
    
    private void updateSpouse(final FalkorPerson falkorPerson) {
        if (this.spouse != null) {
            if (!StringUtils.isNotEmpty(falkorPerson.detail.getSpouse())) {
                this.spouse.setVisibility(8);
                this.spouseLabel.setVisibility(8);
                return;
            }
            this.spouse.setText((CharSequence)falkorPerson.detail.getSpouse());
            this.spouse.setVisibility(0);
            this.spouseLabel.setVisibility(0);
        }
    }
    
    protected void adjustHeight(final ImageView imageView) {
        if (this.this$0.stillImageHeight < 1) {
            final float n = this.this$0.getActivity().getResources().getDimensionPixelOffset(2131362169);
            final float n2 = 1;
            final ViewGroup$LayoutParams layoutParams = imageView.getLayoutParams();
            final float n3 = (BarkerUtils.getDetailsPageContentWidth((Context)this.this$0.getActivity()) - n * (n2 + 1.0f)) / 1;
            float n4;
            if (DeviceUtils.isPortrait((Context)this.this$0.getActivity())) {
                n4 = 0.75f;
            }
            else {
                n4 = 0.5625f;
            }
            layoutParams.height = (int)(n4 * n3);
            return;
        }
        imageView.getLayoutParams().height = this.this$0.stillImageHeight;
    }
    
    protected int getlayoutId() {
        return 2130903248;
    }
    
    void updateDetails(final FalkorPerson falkorPerson, final boolean b) {
        if (falkorPerson == null) {
            return;
        }
        this.updateName(falkorPerson);
        this.updateSpouse(falkorPerson);
        this.updateBorn(falkorPerson);
        this.updateKnownFor(falkorPerson);
        this.updateActorStillImage(falkorPerson);
        this.updateActorHeadshotmage(falkorPerson);
    }
}
