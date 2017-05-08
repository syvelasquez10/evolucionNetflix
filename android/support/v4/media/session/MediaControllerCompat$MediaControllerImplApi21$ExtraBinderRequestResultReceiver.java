// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import java.util.ArrayList;
import android.app.PendingIntent;
import android.os.Build$VERSION;
import android.support.v4.media.MediaMetadataCompat;
import android.view.KeyEvent;
import java.util.Iterator;
import android.util.Log;
import android.os.RemoteException;
import android.content.Context;
import java.util.List;
import java.util.HashMap;
import android.support.v4.app.BundleCompat;
import android.os.Bundle;
import android.os.Handler;
import java.lang.ref.WeakReference;
import android.os.ResultReceiver;

class MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver extends ResultReceiver
{
    private WeakReference<MediaControllerCompat$MediaControllerImplApi21> mMediaControllerImpl;
    
    public MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver(final MediaControllerCompat$MediaControllerImplApi21 mediaControllerCompat$MediaControllerImplApi21, final Handler handler) {
        super(handler);
        this.mMediaControllerImpl = new WeakReference<MediaControllerCompat$MediaControllerImplApi21>(mediaControllerCompat$MediaControllerImplApi21);
    }
    
    protected void onReceiveResult(final int n, final Bundle bundle) {
        final MediaControllerCompat$MediaControllerImplApi21 mediaControllerCompat$MediaControllerImplApi21 = this.mMediaControllerImpl.get();
        if (mediaControllerCompat$MediaControllerImplApi21 == null || bundle == null) {
            return;
        }
        mediaControllerCompat$MediaControllerImplApi21.mExtraBinder = IMediaSession$Stub.asInterface(BundleCompat.getBinder(bundle, "android.support.v4.media.session.EXTRA_BINDER"));
        mediaControllerCompat$MediaControllerImplApi21.processPendingCallbacks();
    }
}
