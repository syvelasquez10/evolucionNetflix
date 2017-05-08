// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.cxxbridge;

import android.content.Context;

public abstract class JSBundleLoader
{
    public static JSBundleLoader createAssetLoader(final Context context, final String s) {
        return new JSBundleLoader$1(context, s);
    }
    
    public static JSBundleLoader createCachedBundleFromNetworkLoader(final String s, final String s2) {
        return new JSBundleLoader$3(s2, s);
    }
    
    public abstract String getSourceUrl();
    
    public abstract void loadScript(final CatalystInstanceImpl p0);
}
