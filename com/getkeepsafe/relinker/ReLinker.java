// 
// Decompiled by Procyon v0.5.30
// 

package com.getkeepsafe.relinker;

import android.content.Context;

public class ReLinker
{
    public static void loadLibrary(final Context context, final String s) {
        loadLibrary(context, s, null, null);
    }
    
    public static void loadLibrary(final Context context, final String s, final String s2, final ReLinker$LoadListener reLinker$LoadListener) {
        new ReLinkerInstance().loadLibrary(context, s, s2, reLinker$LoadListener);
    }
}
