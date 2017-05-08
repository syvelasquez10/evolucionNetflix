// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.ui.common.PlayContextProvider;

public interface VideoViewGroup$IVideoView<T> extends PlayContextProvider
{
    public static final int EXTRA_IS_HORIZONTAL_KEY = 1;
    public static final int EXTRA_USE_HIGH_RES_IMAGE_KEY = 2;
    
    String getImageUrl(final T p0, final boolean p1);
    
    void hide();
    
    void update(final T p0, final Trackable p1, final int p2, final boolean p3, final boolean p4);
}
