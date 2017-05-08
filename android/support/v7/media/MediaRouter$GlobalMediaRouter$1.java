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
import java.lang.ref.WeakReference;
import android.support.v4.media.session.MediaSessionCompat;
import java.util.ArrayList;
import android.support.v4.hardware.display.DisplayManagerCompat;
import android.content.Context;
import android.support.v4.media.session.MediaSessionCompat$OnActiveChangeListener;

class MediaRouter$GlobalMediaRouter$1 implements MediaSessionCompat$OnActiveChangeListener
{
    final /* synthetic */ MediaRouter$GlobalMediaRouter this$0;
    
    MediaRouter$GlobalMediaRouter$1(final MediaRouter$GlobalMediaRouter this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onActiveChanged() {
        if (this.this$0.mRccMediaSession != null) {
            if (!this.this$0.mRccMediaSession.isActive()) {
                this.this$0.removeRemoteControlClient(this.this$0.mRccMediaSession.getRemoteControlClient());
                return;
            }
            this.this$0.addRemoteControlClient(this.this$0.mRccMediaSession.getRemoteControlClient());
        }
    }
}
