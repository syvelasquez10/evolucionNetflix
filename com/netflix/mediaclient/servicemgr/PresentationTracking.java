// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;

public interface PresentationTracking
{
    public static final String TAG = "nf_presentation";
    
    void reportPresentation(final Trackable p0, final List<String> p1, final int p2, final UiLocation p3);
}
