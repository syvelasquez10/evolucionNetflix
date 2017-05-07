// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import org.json.JSONObject;
import com.netflix.mediaclient.media.JPlayer.JPlayer;
import android.content.Context;
import android.view.Surface;
import com.netflix.mediaclient.javabridge.ui.IMedia;

public final class DefaultMediaPlayerHelper implements MediaPlayerHelper
{
    @Override
    public void prepare(final IMedia media, final Surface surface, final Context context) {
    }
    
    @Override
    public void prepareJPlayer(final IMedia media, final Surface surface, final JPlayer.JplayerListener jplayerListener, final boolean b, final JSONObject jsonObject) {
    }
    
    @Override
    public void release() {
    }
}
