// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.util;

import android.text.TextUtils;
import android.os.SystemClock;
import com.google.android.exoplayer.upstream.Loader$Loadable;
import java.io.IOException;
import com.google.android.exoplayer.upstream.UriDataSource;
import com.google.android.exoplayer.upstream.UriLoadable$Parser;
import com.google.android.exoplayer.upstream.Loader;
import android.os.Handler;
import com.google.android.exoplayer.upstream.UriLoadable;
import com.google.android.exoplayer.upstream.Loader$Callback;

class ManifestFetcher$2 implements Runnable
{
    final /* synthetic */ ManifestFetcher this$0;
    
    ManifestFetcher$2(final ManifestFetcher this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.eventListener.onManifestRefreshed();
    }
}
