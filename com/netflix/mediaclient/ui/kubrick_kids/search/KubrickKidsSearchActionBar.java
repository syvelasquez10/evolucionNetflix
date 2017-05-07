// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.search;

import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.SearchActionBar;

public class KubrickKidsSearchActionBar extends SearchActionBar
{
    public KubrickKidsSearchActionBar(final NetflixActivity netflixActivity) {
        super(netflixActivity);
    }
    
    @Override
    protected int getActiveSearchIconResId() {
        return 2130837728;
    }
    
    @Override
    protected int getLayoutId() {
        return 2130903065;
    }
    
    @Override
    protected Integer getSearchCloseButtonTint() {
        return -16777216;
    }
    
    @Override
    protected int getSearchViewBgResId() {
        return 2130837871;
    }
    
    @Override
    protected int getSearchViewRightBgResId() {
        return 2130837873;
    }
    
    @Override
    protected int getSearchViewTextColorResId() {
        return 2131230821;
    }
    
    @Override
    protected Integer getSearchVoiceButtonTint() {
        return -16777216;
    }
}
