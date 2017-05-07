// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.content.Context;
import android.app.ActivityManager;
import android.app.ActivityManager$RunningTaskInfo;

public final class bx$a implements bw
{
    private String a;
    
    public bx$a() {
        String replace = null;
        this.a = null;
        bx.c;
        bx.b;
        if (bx.c.b) {
            replace = ((ActivityManager)bx.b.getSystemService("activity")).getRunningTasks(1).get(0).topActivity.flattenToShortString().replace("/", "");
        }
        this.a = replace;
    }
    
    @Override
    public final String a() {
        return "activity";
    }
}
