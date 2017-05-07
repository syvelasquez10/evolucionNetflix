// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import org.json.JSONObject;
import com.netflix.mediaclient.media.JPlayer.JPlayer;
import android.content.Context;
import android.view.Surface;
import com.netflix.mediaclient.javabridge.ui.IMedia;

public interface MediaPlayerHelper
{
    void prepare(final IMedia p0, final Surface p1, final Context p2);
    
    void prepareJPlayer(final IMedia p0, final Surface p1, final JPlayer.JplayerListener p2, final boolean p3, final JSONObject p4);
    
    void release();
}
