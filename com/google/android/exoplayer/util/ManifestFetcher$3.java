// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.util;

import android.text.TextUtils;
import android.os.SystemClock;
import com.google.android.exoplayer.upstream.Loader$Loadable;
import com.google.android.exoplayer.upstream.UriDataSource;
import com.google.android.exoplayer.upstream.UriLoadable$Parser;
import com.google.android.exoplayer.upstream.Loader;
import android.os.Handler;
import com.google.android.exoplayer.upstream.UriLoadable;
import com.google.android.exoplayer.upstream.Loader$Callback;
import java.io.IOException;

class ManifestFetcher$3 implements Runnable
{
    final /* synthetic */ ManifestFetcher this$0;
    final /* synthetic */ IOException val$e;
    
    ManifestFetcher$3(final ManifestFetcher this$0, final IOException val$e) {
        this.this$0 = this$0;
        this.val$e = val$e;
    }
    
    @Override
    public void run() {
        this.this$0.eventListener.onManifestError(this.val$e);
    }
}
