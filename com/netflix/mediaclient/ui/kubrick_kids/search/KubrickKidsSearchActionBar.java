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
        return 2130837736;
    }
    
    @Override
    protected int getLayoutId() {
        return 2130903066;
    }
    
    @Override
    protected Integer getSearchCloseButtonTint() {
        return this.activity.getResources().getColor(2131558478);
    }
    
    @Override
    protected int getSearchViewBgResId() {
        return 2130837891;
    }
    
    @Override
    protected int getSearchViewRightBgResId() {
        return 2130837893;
    }
    
    @Override
    protected int getSearchViewTextColorResId() {
        return 2131558416;
    }
    
    @Override
    protected Integer getSearchVoiceButtonTint() {
        return this.activity.getResources().getColor(2131558478);
    }
}
