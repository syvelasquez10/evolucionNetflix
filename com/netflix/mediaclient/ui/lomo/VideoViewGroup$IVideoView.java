// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.ui.common.PlayContextProvider;

public interface VideoViewGroup$IVideoView<T> extends PlayContextProvider
{
    void hide();
    
    void update(final T p0, final Trackable p1, final int p2, final boolean p3, final boolean p4);
}
