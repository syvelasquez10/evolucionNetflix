// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.os.Bundle;
import java.util.List;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.util.Log;
import android.support.v4.media.session.MediaSessionCompat;
import android.os.Message;
import android.os.Messenger;
import java.lang.ref.WeakReference;
import android.os.Handler;

class MediaBrowserCompat$CallbackHandler extends Handler
{
    private final WeakReference<MediaBrowserCompat$MediaBrowserServiceCallbackImpl> mCallbackImplRef;
    private WeakReference<Messenger> mCallbacksMessengerRef;
    
    MediaBrowserCompat$CallbackHandler(final MediaBrowserCompat$MediaBrowserServiceCallbackImpl mediaBrowserCompat$MediaBrowserServiceCallbackImpl) {
        this.mCallbackImplRef = new WeakReference<MediaBrowserCompat$MediaBrowserServiceCallbackImpl>(mediaBrowserCompat$MediaBrowserServiceCallbackImpl);
    }
    
    public void handleMessage(final Message message) {
        if (this.mCallbacksMessengerRef == null || this.mCallbacksMessengerRef.get() == null || this.mCallbackImplRef.get() == null) {
            return;
        }
        final Bundle data = message.getData();
        data.setClassLoader(MediaSessionCompat.class.getClassLoader());
        switch (message.what) {
            default: {
                Log.w("MediaBrowserCompat", "Unhandled message: " + message + "\n  Client version: " + 1 + "\n  Service version: " + message.arg1);
            }
            case 1: {
                this.mCallbackImplRef.get().onServiceConnected(this.mCallbacksMessengerRef.get(), data.getString("data_media_item_id"), (MediaSessionCompat$Token)data.getParcelable("data_media_session_token"), data.getBundle("data_root_hints"));
            }
            case 2: {
                this.mCallbackImplRef.get().onConnectionFailed(this.mCallbacksMessengerRef.get());
            }
            case 3: {
                this.mCallbackImplRef.get().onLoadChildren(this.mCallbacksMessengerRef.get(), data.getString("data_media_item_id"), data.getParcelableArrayList("data_media_item_list"), data.getBundle("data_options"));
            }
        }
    }
    
    void setCallbacksMessenger(final Messenger messenger) {
        this.mCallbacksMessengerRef = new WeakReference<Messenger>(messenger);
    }
}
