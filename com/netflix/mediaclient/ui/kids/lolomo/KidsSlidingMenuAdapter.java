// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.servicemgr.GenreList;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.UIViewLogging;
import android.view.View;
import android.view.View$OnClickListener;
import android.view.ViewStub;
import android.widget.TextView;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.util.ViewUtils;
import android.support.v4.widget.DrawerLayout;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.home.SlidingMenuAdapter;

public class KidsSlidingMenuAdapter extends SlidingMenuAdapter
{
    private final int itemTextColor;
    
    public KidsSlidingMenuAdapter(final NetflixActivity netflixActivity, final DrawerLayout drawerLayout) {
        super(netflixActivity, drawerLayout);
        this.itemTextColor = netflixActivity.getResources().getColor(2131296363);
        this.home.setTextColor(this.itemTextColor);
        ViewUtils.setTextViewToBold(this.home);
        ViewUtils.clearShadow(this.home);
        this.content.setBackgroundResource(2131296362);
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final int n) {
        super.onManagerReady(serviceManager, n);
        if (KidsUtils.shouldShowKidsEntryInMenu(this.activity)) {
            final TextView textViewToBold = (TextView)((ViewStub)this.content.findViewById(2131165375)).inflate().findViewById(2131165377);
            textViewToBold.setText(2131492947);
            textViewToBold.setTextColor(this.activity.getResources().getColor(2131296307));
            textViewToBold.setBackgroundResource(2130837741);
            ViewUtils.setTextViewToBold(textViewToBold);
            ViewUtils.clearShadow(textViewToBold);
            textViewToBold.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    KidsSlidingMenuAdapter.this.activity.startActivity(KidsUtils.createExitKidsIntent(KidsSlidingMenuAdapter.this.activity, UIViewLogging.UIViewCommandName.slidingMenuKidsExit));
                }
            });
        }
    }
    
    @Override
    protected boolean shouldShowChangeProfilesItem() {
        return false;
    }
    
    @Override
    protected void updateAdapterViews(final Holder holder, final GenreList list) {
        super.updateAdapterViews(holder, list);
        holder.tv.setTextColor(this.itemTextColor);
        ViewUtils.setTextViewToBold(holder.tv);
        ViewUtils.clearShadow(holder.tv);
    }
}
