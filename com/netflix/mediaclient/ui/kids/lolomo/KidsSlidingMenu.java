// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.view.View$OnClickListener;
import android.view.ViewStub;
import android.widget.TextView;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.home.OldSlidingMenu$Holder;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.util.ViewUtils;
import android.support.v4.widget.DrawerLayout;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.home.OldSlidingMenu;

public class KidsSlidingMenu extends OldSlidingMenu
{
    private final int itemTextColor;
    private final int selectedTextColor;
    
    public KidsSlidingMenu(final NetflixActivity netflixActivity, final DrawerLayout drawerLayout) {
        super(netflixActivity, drawerLayout);
        this.itemTextColor = netflixActivity.getResources().getColor(2131230868);
        this.selectedTextColor = netflixActivity.getResources().getColor(2131230869);
        this.home.setTextColor(this.itemTextColor);
        ViewUtils.removeShadow(this.home);
        this.home.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, netflixActivity.getResources().getDimensionPixelSize(2131296438)));
        this.content.setBackgroundResource(2131230867);
    }
    
    @Override
    protected void applySelectionStyle(final View view) {
        final OldSlidingMenu$Holder oldSlidingMenu$Holder = (OldSlidingMenu$Holder)view.getTag();
        ViewUtils.setTextViewToBold(oldSlidingMenu$Holder.tv);
        oldSlidingMenu$Holder.tv.setTextColor(this.selectedTextColor);
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        super.onManagerReady(serviceManager, status);
        if (KubrickUtils.shouldShowKidsEntryInMenu(this.activity)) {
            final TextView textViewToBold = (TextView)((ViewStub)this.content.findViewById(2131427688)).inflate().findViewById(2131427690);
            textViewToBold.setText(2131492976);
            textViewToBold.setTextColor(this.activity.getResources().getColor(2131230822));
            textViewToBold.setBackgroundResource(2130837753);
            ViewUtils.setTextViewToBold(textViewToBold);
            ViewUtils.removeShadow(textViewToBold);
            textViewToBold.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, this.activity.getResources().getDimensionPixelSize(2131296438)));
            textViewToBold.setOnClickListener((View$OnClickListener)new KidsSlidingMenu$1(this));
        }
    }
    
    @Override
    protected void removeSelectionStyle(final View view) {
        final OldSlidingMenu$Holder oldSlidingMenu$Holder = (OldSlidingMenu$Holder)view.getTag();
        ViewUtils.setTextViewToNormal(oldSlidingMenu$Holder.tv);
        oldSlidingMenu$Holder.tv.setTextColor(this.itemTextColor);
    }
    
    @Override
    protected boolean shouldShowChangeProfilesItem() {
        return false;
    }
    
    @Override
    protected void updateAdapterViews(final OldSlidingMenu$Holder oldSlidingMenu$Holder, final GenreList list) {
        super.updateAdapterViews(oldSlidingMenu$Holder, list);
        oldSlidingMenu$Holder.tv.setTextColor(this.itemTextColor);
        ViewUtils.removeShadow(oldSlidingMenu$Holder.tv);
        oldSlidingMenu$Holder.tv.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, this.activity.getResources().getDimensionPixelSize(2131296438)));
    }
}
