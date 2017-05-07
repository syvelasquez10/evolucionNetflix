// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import android.app.Activity;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.view.View;
import android.view.View$OnClickListener;
import android.view.ViewStub;
import android.widget.TextView;
import android.support.v4.widget.DrawerLayout;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.home.SlidingMenuAdapter;

public class KidsSlidingMenuAdapter extends SlidingMenuAdapter
{
    public KidsSlidingMenuAdapter(final NetflixActivity netflixActivity, final DrawerLayout drawerLayout) {
        super(netflixActivity, drawerLayout);
        final TextView textView = (TextView)((ViewStub)this.content.findViewById(2131165371)).inflate().findViewById(2131165373);
        textView.setText(2131492947);
        textView.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                netflixActivity.startActivity(KidsUtils.createExitKidsIntent(netflixActivity));
            }
        });
    }
    
    @Override
    protected boolean shouldShowChangeProfilesItem() {
        return false;
    }
}
