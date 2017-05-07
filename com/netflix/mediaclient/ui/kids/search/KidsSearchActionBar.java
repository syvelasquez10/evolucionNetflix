// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.search;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.SearchActionBar;

public class KidsSearchActionBar extends SearchActionBar
{
    public KidsSearchActionBar(final NetflixActivity netflixActivity) {
        super(netflixActivity);
        this.systemActionBar.setBackgroundDrawable((Drawable)new ColorDrawable(netflixActivity.getResources().getColor(2131296362)));
        this.searchView.setQueryHint((CharSequence)this.getActivity().getString(2131493206));
    }
    
    @Override
    public void onManagerReady() {
        this.configureBackButtonIfNecessary(true);
    }
}
