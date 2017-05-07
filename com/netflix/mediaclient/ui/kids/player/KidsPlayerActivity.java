// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.player;

import android.os.Bundle;
import com.netflix.mediaclient.ui.player.PlayerActivity;

public class KidsPlayerActivity extends PlayerActivity
{
    @Override
    public boolean isForKids() {
        return true;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setRequestedOrientation(6);
    }
    
    @Override
    protected boolean shouldShowKidsBackground() {
        return false;
    }
}
