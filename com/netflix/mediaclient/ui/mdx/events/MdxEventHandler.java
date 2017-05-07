// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx.events;

import android.content.Intent;
import com.netflix.mediaclient.ui.mdx.RemotePlaybackListener;

public interface MdxEventHandler
{
    String getAction();
    
    void handle(final RemotePlaybackListener p0, final Intent p1);
}
