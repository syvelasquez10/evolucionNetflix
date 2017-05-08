// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko;

import com.netflix.mediaclient.ui.iko.wordparty.moments.WPInteractiveMomentsManager;
import com.netflix.mediaclient.ui.iko.kong.moments.KongInteractiveMomentsManager;
import com.netflix.mediaclient.util.StringUtils;

public class InteractiveMomentsFactory
{
    public static final String KONG = "kong";
    public static final String WORD_PARTY = "wordparty";
    
    public static InteractiveMomentsManager getManager(final String s) {
        if (!StringUtils.isEmpty(s)) {
            if ("kong".equalsIgnoreCase(s)) {
                return (InteractiveMomentsManager)new KongInteractiveMomentsManager();
            }
            if ("wordparty".equalsIgnoreCase(s)) {
                return (InteractiveMomentsManager)new WPInteractiveMomentsManager();
            }
        }
        return null;
    }
}
