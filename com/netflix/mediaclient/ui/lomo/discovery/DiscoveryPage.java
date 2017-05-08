// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo.discovery;

import android.view.ViewTreeObserver$OnPreDrawListener;
import android.view.ViewGroup$MarginLayoutParams;
import com.netflix.mediaclient.util.Coppola2Utils;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.interface_.Discovery;
import android.view.View$OnClickListener;
import android.view.LayoutInflater;
import android.util.AttributeSet;
import android.content.Context;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.lomo.CwDiscoveryView;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.View;
import android.widget.LinearLayout;

public class DiscoveryPage extends LinearLayout
{
    private static final String TAG = "DiscoveryPage";
    private View allCWVew;
    private AdvancedImageView pivot1Boxart;
    private TextView pivot1Title;
    private AdvancedImageView pivot2Boxart;
    private TextView pivot2Title;
    private CwDiscoveryView playableView;
    private ViewGroup topPlayableLayout;
    
    public DiscoveryPage(final Context context) {
        super(context);
        this.init(context);
    }
    
    public DiscoveryPage(final Context context, final AttributeSet set) {
        super(context, set);
        this.init(context);
    }
    
    public DiscoveryPage(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init(context);
    }
    
    private void init(final Context context) {
        this.setOrientation(1);
        ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130903108, (ViewGroup)this);
        this.topPlayableLayout = (ViewGroup)this.findViewById(2131689769);
        this.pivot1Boxart = (AdvancedImageView)this.findViewById(2131689770);
        this.pivot2Boxart = (AdvancedImageView)this.findViewById(2131689772);
        this.pivot1Title = (TextView)this.findViewById(2131689771);
        this.pivot2Title = (TextView)this.findViewById(2131689773);
        this.allCWVew = this.findViewById(2131689774);
        this.playableView = new CwDiscoveryView(context);
        this.topPlayableLayout.addView((View)this.playableView);
        this.setOnClickListener((View$OnClickListener)new DiscoveryPage$1(this));
    }
    
    public void updatePage(final Discovery discovery, final int n, final View$OnClickListener onClickListener, final View$OnClickListener onClickListener2, final View$OnClickListener onClickListener3, final Trackable trackable) {
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.pivot1Boxart, discovery.getPivot1BoxartUrl(), IClientLogging$AssetType.merchStill, discovery.getPivot1Title(), ImageLoader$StaticImgConfig.DARK, true, 1);
        this.pivot1Title.setText((CharSequence)discovery.getPivot1Title());
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.pivot2Boxart, discovery.getPivot2BoxartUrl(), IClientLogging$AssetType.merchStill, discovery.getPivot2Title(), ImageLoader$StaticImgConfig.DARK, true, 1);
        this.pivot2Title.setText((CharSequence)discovery.getPivot2Title());
        this.playableView.update(discovery.getFalkorVideo(), trackable, n, true, false);
        if (this.allCWVew != null) {
            if (Coppola2Utils.shouldHideContinueWatchingLink(this.getContext())) {
                this.allCWVew.getLayoutParams().height = 0;
                ((ViewGroup$MarginLayoutParams)this.allCWVew.getLayoutParams()).topMargin = 0;
            }
            else {
                this.allCWVew.setOnClickListener(onClickListener);
            }
        }
        this.pivot1Boxart.setOnClickListener(onClickListener2);
        this.pivot2Boxart.setOnClickListener(onClickListener3);
        this.playableView.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)new DiscoveryPage$2(this, n));
    }
}
