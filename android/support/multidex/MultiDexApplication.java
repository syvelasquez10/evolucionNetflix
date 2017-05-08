// 
// Decompiled by Procyon v0.5.30
// 

package android.support.multidex;

import android.content.Context;
import android.app.Application;

public class MultiDexApplication extends Application
{
    protected void attachBaseContext(final Context context) {
        super.attachBaseContext(context);
        MultiDex.install((Context)this);
    }
}
