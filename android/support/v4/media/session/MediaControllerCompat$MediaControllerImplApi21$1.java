// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import java.util.ArrayList;
import android.app.PendingIntent;
import android.os.Build$VERSION;
import android.support.v4.media.MediaMetadataCompat;
import android.view.KeyEvent;
import android.content.Context;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import android.os.RemoteException;
import android.util.Log;
import android.support.v4.app.BundleCompat;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

class MediaControllerCompat$MediaControllerImplApi21$1 extends ResultReceiver
{
    final /* synthetic */ MediaControllerCompat$MediaControllerImplApi21 this$0;
    
    MediaControllerCompat$MediaControllerImplApi21$1(final MediaControllerCompat$MediaControllerImplApi21 this$0, final Handler handler) {
        this.this$0 = this$0;
        super(handler);
    }
    
    protected void onReceiveResult(final int n, final Bundle bundle) {
        if (bundle != null) {
            this.this$0.mExtraBinder = IMediaSession$Stub.asInterface(BundleCompat.getBinder(bundle, "android.support.v4.media.session.EXTRA_BINDER"));
            if (this.this$0.mPendingCallbacks != null) {
                for (final MediaControllerCompat$Callback mediaControllerCompat$Callback : this.this$0.mPendingCallbacks) {
                    final MediaControllerCompat$MediaControllerImplApi21$ExtraCallback mediaControllerCompat$MediaControllerImplApi21$ExtraCallback = new MediaControllerCompat$MediaControllerImplApi21$ExtraCallback(this.this$0, mediaControllerCompat$Callback);
                    this.this$0.mCallbackMap.put(mediaControllerCompat$Callback, mediaControllerCompat$MediaControllerImplApi21$ExtraCallback);
                    mediaControllerCompat$Callback.mHasExtraCallback = true;
                    try {
                        this.this$0.mExtraBinder.registerCallbackListener(mediaControllerCompat$MediaControllerImplApi21$ExtraCallback);
                        continue;
                    }
                    catch (RemoteException ex) {
                        Log.e("MediaControllerCompat", "Dead object in registerCallback. " + ex);
                    }
                    break;
                }
                this.this$0.mPendingCallbacks = null;
            }
        }
    }
}
