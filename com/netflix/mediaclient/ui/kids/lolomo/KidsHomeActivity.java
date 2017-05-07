// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.ui.home.HomeActivity;

public class KidsHomeActivity extends HomeActivity
{
    @Override
    public boolean isForKids() {
        return true;
    }
    
    @Override
    public boolean shouldApplyPaddingToSlidingPanel() {
        return true;
    }
}
