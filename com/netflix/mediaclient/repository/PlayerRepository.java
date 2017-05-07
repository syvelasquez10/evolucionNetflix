// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.repository;

import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;

public class PlayerRepository
{
    private boolean displayDebugData;
    
    PlayerRepository(final Context context) {
        this.displayDebugData = false;
        this.displayDebugData = PreferenceUtils.getBooleanPref(context, "ui.playeroverlay", false);
    }
    
    public boolean isDisplayDebugData() {
        return this.displayDebugData;
    }
    
    public void setDisplayDebugData(final boolean displayDebugData) {
        this.displayDebugData = displayDebugData;
    }
}
