// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko;

import com.netflix.mediaclient.ui.iko.kong.moments.KongInteractiveMomentsManager;
import com.netflix.mediaclient.util.StringUtils;

public class InteractiveMomentsFactory
{
    public static final String KONG = "kong";
    
    public static InteractiveMomentsManager getManager(final String s) {
        if (!StringUtils.isEmpty(s) && "kong".equalsIgnoreCase(s)) {
            return new KongInteractiveMomentsManager();
        }
        return null;
    }
}
