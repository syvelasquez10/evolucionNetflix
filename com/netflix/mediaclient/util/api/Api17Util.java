// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.api;

import android.widget.RelativeLayout$LayoutParams;

public class Api17Util
{
    public static void removeRelativeLayoutParamsRule(final RelativeLayout$LayoutParams relativeLayout$LayoutParams, final int n) {
        relativeLayout$LayoutParams.addRule(n, 0);
    }
}
