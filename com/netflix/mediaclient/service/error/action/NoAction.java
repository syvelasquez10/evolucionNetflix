// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error.action;

import com.netflix.mediaclient.Log;
import android.app.Activity;

public class NoAction extends BaseAction
{
    public NoAction(final Activity activity) {
        super(activity);
    }
    
    @Override
    public void run() {
        Log.d("ErrorManager", "User pressed OK");
    }
}
