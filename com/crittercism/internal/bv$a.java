// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.content.Context;
import android.app.ActivityManager;
import android.app.ActivityManager$RunningTaskInfo;

public final class bv$a implements bu
{
    private String a;
    
    public bv$a() {
        String replace = null;
        this.a = null;
        if (bv.c.b) {
            replace = ((ActivityManager)bv.b.getSystemService("activity")).getRunningTasks(1).get(0).topActivity.flattenToShortString().replace("/", "");
        }
        this.a = replace;
    }
    
    @Override
    public final String a() {
        return "activity";
    }
}
