// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import java.util.List;
import java.util.Collections;
import android.util.Log;
import java.util.Iterator;
import java.util.Locale;
import android.support.v4.app.ActivityManagerCompat;
import android.app.ActivityManager;
import android.support.v4.media.session.MediaSessionCompat$OnActiveChangeListener;
import java.lang.ref.WeakReference;
import android.support.v4.media.session.MediaSessionCompat;
import java.util.ArrayList;
import android.support.v4.hardware.display.DisplayManagerCompat;
import android.content.Context;
import android.support.v4.media.VolumeProviderCompat;

class MediaRouter$GlobalMediaRouter$MediaSessionRecord$1 extends VolumeProviderCompat
{
    final /* synthetic */ MediaRouter$GlobalMediaRouter$MediaSessionRecord this$1;
    
    MediaRouter$GlobalMediaRouter$MediaSessionRecord$1(final MediaRouter$GlobalMediaRouter$MediaSessionRecord this$1, final int n, final int n2, final int n3) {
        this.this$1 = this$1;
        super(n, n2, n3);
    }
    
    @Override
    public void onAdjustVolume(final int n) {
        this.this$1.this$0.mCallbackHandler.post((Runnable)new MediaRouter$GlobalMediaRouter$MediaSessionRecord$1$2(this, n));
    }
    
    @Override
    public void onSetVolumeTo(final int n) {
        this.this$1.this$0.mCallbackHandler.post((Runnable)new MediaRouter$GlobalMediaRouter$MediaSessionRecord$1$1(this, n));
    }
}
