// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import android.view.View$OnClickListener;
import android.view.ViewStub;
import android.widget.TextView;
import com.netflix.mediaclient.ui.kubrick_kids.KubrickKidsUtils;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.home.SlidingMenuAdapter$Holder;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.util.ViewUtils;
import android.support.v4.widget.DrawerLayout;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.home.SlidingMenuAdapter;

public class KidsSlidingMenuAdapter extends SlidingMenuAdapter
{
    private final int itemTextColor;
    private final int selectedTextColor;
    
    public KidsSlidingMenuAdapter(final NetflixActivity netflixActivity, final DrawerLayout drawerLayout) {
        super(netflixActivity, drawerLayout);
        this.itemTextColor = netflixActivity.getResources().getColor(2131296400);
        this.selectedTextColor = netflixActivity.getResources().getColor(2131296401);
        this.home.setTextColor(this.itemTextColor);
        ViewUtils.clearShadow(this.home);
        this.home.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, netflixActivity.getResources().getDimensionPixelSize(2131361985)));
        this.content.setBackgroundResource(2131296399);
    }
    
    @Override
    protected void applySelectionStyle(final View view) {
        final SlidingMenuAdapter$Holder slidingMenuAdapter$Holder = (SlidingMenuAdapter$Holder)view.getTag();
        ViewUtils.setTextViewToBold(slidingMenuAdapter$Holder.tv);
        slidingMenuAdapter$Holder.tv.setTextColor(this.selectedTextColor);
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        super.onManagerReady(serviceManager, status);
        if (KidsUtils.shouldShowKidsEntryInMenu(this.activity) || KubrickKidsUtils.shouldShowKidsEntryInMenu(this.activity)) {
            final TextView textViewToBold = (TextView)((ViewStub)this.content.findViewById(2131165383)).inflate().findViewById(2131165385);
            textViewToBold.setText(2131492961);
            textViewToBold.setTextColor(this.activity.getResources().getColor(2131296358));
            textViewToBold.setBackgroundResource(2130837736);
            ViewUtils.setTextViewToBold(textViewToBold);
            ViewUtils.clearShadow(textViewToBold);
            textViewToBold.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, this.activity.getResources().getDimensionPixelSize(2131361985)));
            textViewToBold.setOnClickListener((View$OnClickListener)new KidsSlidingMenuAdapter$1(this));
        }
    }
    
    @Override
    protected void removeSelectionStyle(final View view) {
        final SlidingMenuAdapter$Holder slidingMenuAdapter$Holder = (SlidingMenuAdapter$Holder)view.getTag();
        ViewUtils.setTextViewToNormal(slidingMenuAdapter$Holder.tv);
        slidingMenuAdapter$Holder.tv.setTextColor(this.itemTextColor);
    }
    
    @Override
    protected boolean shouldShowChangeProfilesItem() {
        return false;
    }
    
    @Override
    protected void updateAdapterViews(final SlidingMenuAdapter$Holder slidingMenuAdapter$Holder, final GenreList list) {
        super.updateAdapterViews(slidingMenuAdapter$Holder, list);
        slidingMenuAdapter$Holder.tv.setTextColor(this.itemTextColor);
        ViewUtils.clearShadow(slidingMenuAdapter$Holder.tv);
        slidingMenuAdapter$Holder.tv.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, this.activity.getResources().getDimensionPixelSize(2131361985)));
    }
}
