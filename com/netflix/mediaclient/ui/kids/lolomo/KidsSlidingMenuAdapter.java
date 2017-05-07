// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.UIViewLogging;
import android.view.View$OnClickListener;
import android.view.ViewStub;
import android.widget.TextView;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
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
        this.itemTextColor = netflixActivity.getResources().getColor(2131296413);
        this.selectedTextColor = netflixActivity.getResources().getColor(2131296414);
        this.home.setTextColor(this.itemTextColor);
        ViewUtils.clearShadow(this.home);
        this.home.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, netflixActivity.getResources().getDimensionPixelSize(2131361978)));
        this.content.setBackgroundResource(2131296412);
    }
    
    @Override
    protected void applySelectionStyle(final View view) {
        final Holder holder = (Holder)view.getTag();
        ViewUtils.setTextViewToBold(holder.tv);
        holder.tv.setTextColor(this.selectedTextColor);
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        super.onManagerReady(serviceManager, status);
        if (KidsUtils.shouldShowKidsEntryInMenu(this.activity)) {
            final TextView textViewToBold = (TextView)((ViewStub)this.content.findViewById(2131165398)).inflate().findViewById(2131165400);
            textViewToBold.setText(2131492952);
            textViewToBold.setTextColor(this.activity.getResources().getColor(2131296352));
            textViewToBold.setBackgroundResource(2130837722);
            ViewUtils.setTextViewToBold(textViewToBold);
            ViewUtils.clearShadow(textViewToBold);
            textViewToBold.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, this.activity.getResources().getDimensionPixelSize(2131361978)));
            textViewToBold.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    KidsSlidingMenuAdapter.this.activity.startActivity(KidsUtils.createExitKidsIntent(KidsSlidingMenuAdapter.this.activity, UIViewLogging.UIViewCommandName.slidingMenuKidsExit));
                }
            });
        }
    }
    
    @Override
    protected void removeSelectionStyle(final View view) {
        final Holder holder = (Holder)view.getTag();
        ViewUtils.setTextViewToNormal(holder.tv);
        holder.tv.setTextColor(this.itemTextColor);
    }
    
    @Override
    protected boolean shouldShowChangeProfilesItem() {
        return false;
    }
    
    @Override
    protected void updateAdapterViews(final Holder holder, final GenreList list) {
        super.updateAdapterViews(holder, list);
        holder.tv.setTextColor(this.itemTextColor);
        ViewUtils.clearShadow(holder.tv);
        holder.tv.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, this.activity.getResources().getDimensionPixelSize(2131361978)));
    }
}
