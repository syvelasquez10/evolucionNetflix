// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag;
import android.content.Context;

final class PlaybackLauncher$1 implements Runnable
{
    final /* synthetic */ Context val$context;
    
    PlaybackLauncher$1(final Context val$context) {
        this.val$context = val$context;
    }
    
    @Override
    public void run() {
        MdxMiniPlayerFrag.sendShowAndDisableIntent(this.val$context);
    }
}
