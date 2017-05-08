// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.os.Bundle;
import android.support.v4.media.RatingCompat;
import android.net.Uri;
import android.os.Parcelable;
import android.content.Intent;
import android.os.Message;
import android.view.KeyEvent;
import android.os.Looper;
import android.os.Handler;

class MediaSessionCompat$MediaSessionImplBase$MessageHandler extends Handler
{
    private static final int KEYCODE_MEDIA_PAUSE = 127;
    private static final int KEYCODE_MEDIA_PLAY = 126;
    private static final int MSG_ADJUST_VOLUME = 2;
    private static final int MSG_COMMAND = 1;
    private static final int MSG_CUSTOM_ACTION = 20;
    private static final int MSG_FAST_FORWARD = 16;
    private static final int MSG_MEDIA_BUTTON = 21;
    private static final int MSG_NEXT = 14;
    private static final int MSG_PAUSE = 12;
    private static final int MSG_PLAY = 7;
    private static final int MSG_PLAY_MEDIA_ID = 8;
    private static final int MSG_PLAY_SEARCH = 9;
    private static final int MSG_PLAY_URI = 10;
    private static final int MSG_PREPARE = 3;
    private static final int MSG_PREPARE_MEDIA_ID = 4;
    private static final int MSG_PREPARE_SEARCH = 5;
    private static final int MSG_PREPARE_URI = 6;
    private static final int MSG_PREVIOUS = 15;
    private static final int MSG_RATE = 19;
    private static final int MSG_REWIND = 17;
    private static final int MSG_SEEK_TO = 18;
    private static final int MSG_SET_VOLUME = 22;
    private static final int MSG_SKIP_TO_ITEM = 11;
    private static final int MSG_STOP = 13;
    final /* synthetic */ MediaSessionCompat$MediaSessionImplBase this$0;
    
    public MediaSessionCompat$MediaSessionImplBase$MessageHandler(final MediaSessionCompat$MediaSessionImplBase this$0, final Looper looper) {
        this.this$0 = this$0;
        super(looper);
    }
    
    private void onMediaButtonEvent(final KeyEvent keyEvent, final MediaSessionCompat$Callback mediaSessionCompat$Callback) {
        if (keyEvent != null && keyEvent.getAction() == 0) {
            long actions;
            if (this.this$0.mState == null) {
                actions = 0L;
            }
            else {
                actions = this.this$0.mState.getActions();
            }
            switch (keyEvent.getKeyCode()) {
                default: {}
                case 79:
                case 85: {
                    boolean b;
                    if (this.this$0.mState != null && this.this$0.mState.getState() == 3) {
                        b = true;
                    }
                    else {
                        b = false;
                    }
                    final boolean b2 = (0x204L & actions) != 0x0L;
                    boolean b3;
                    if ((actions & 0x202L) != 0x0L) {
                        b3 = true;
                    }
                    else {
                        b3 = false;
                    }
                    if (b && b3) {
                        mediaSessionCompat$Callback.onPause();
                        return;
                    }
                    if (!b && b2) {
                        mediaSessionCompat$Callback.onPlay();
                        return;
                    }
                    break;
                }
                case 126: {
                    if ((actions & 0x4L) != 0x0L) {
                        mediaSessionCompat$Callback.onPlay();
                        return;
                    }
                    break;
                }
                case 127: {
                    if ((actions & 0x2L) != 0x0L) {
                        mediaSessionCompat$Callback.onPause();
                        return;
                    }
                    break;
                }
                case 87: {
                    if ((actions & 0x20L) != 0x0L) {
                        mediaSessionCompat$Callback.onSkipToNext();
                        return;
                    }
                    break;
                }
                case 88: {
                    if ((actions & 0x10L) != 0x0L) {
                        mediaSessionCompat$Callback.onSkipToPrevious();
                        return;
                    }
                    break;
                }
                case 86: {
                    if ((actions & 0x1L) != 0x0L) {
                        mediaSessionCompat$Callback.onStop();
                        return;
                    }
                    break;
                }
                case 90: {
                    if ((actions & 0x40L) != 0x0L) {
                        mediaSessionCompat$Callback.onFastForward();
                        return;
                    }
                    break;
                }
                case 89: {
                    if ((actions & 0x8L) != 0x0L) {
                        mediaSessionCompat$Callback.onRewind();
                        return;
                    }
                    break;
                }
            }
        }
    }
    
    public void handleMessage(final Message message) {
        final MediaSessionCompat$Callback mCallback = this.this$0.mCallback;
        if (mCallback != null) {
            switch (message.what) {
                default: {}
                case 1: {
                    final MediaSessionCompat$MediaSessionImplBase$Command mediaSessionCompat$MediaSessionImplBase$Command = (MediaSessionCompat$MediaSessionImplBase$Command)message.obj;
                    mCallback.onCommand(mediaSessionCompat$MediaSessionImplBase$Command.command, mediaSessionCompat$MediaSessionImplBase$Command.extras, mediaSessionCompat$MediaSessionImplBase$Command.stub);
                }
                case 21: {
                    final KeyEvent keyEvent = (KeyEvent)message.obj;
                    final Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                    intent.putExtra("android.intent.extra.KEY_EVENT", (Parcelable)keyEvent);
                    if (!mCallback.onMediaButtonEvent(intent)) {
                        this.onMediaButtonEvent(keyEvent, mCallback);
                        return;
                    }
                    break;
                }
                case 3: {
                    mCallback.onPrepare();
                }
                case 4: {
                    mCallback.onPrepareFromMediaId((String)message.obj, message.getData());
                }
                case 5: {
                    mCallback.onPrepareFromSearch((String)message.obj, message.getData());
                }
                case 6: {
                    mCallback.onPrepareFromUri((Uri)message.obj, message.getData());
                }
                case 7: {
                    mCallback.onPlay();
                }
                case 8: {
                    mCallback.onPlayFromMediaId((String)message.obj, message.getData());
                }
                case 9: {
                    mCallback.onPlayFromSearch((String)message.obj, message.getData());
                }
                case 10: {
                    mCallback.onPlayFromUri((Uri)message.obj, message.getData());
                }
                case 11: {
                    mCallback.onSkipToQueueItem((long)message.obj);
                }
                case 12: {
                    mCallback.onPause();
                }
                case 13: {
                    mCallback.onStop();
                }
                case 14: {
                    mCallback.onSkipToNext();
                }
                case 15: {
                    mCallback.onSkipToPrevious();
                }
                case 16: {
                    mCallback.onFastForward();
                }
                case 17: {
                    mCallback.onRewind();
                }
                case 18: {
                    mCallback.onSeekTo((long)message.obj);
                }
                case 19: {
                    mCallback.onSetRating((RatingCompat)message.obj);
                }
                case 20: {
                    mCallback.onCustomAction((String)message.obj, message.getData());
                }
                case 2: {
                    this.this$0.adjustVolume((int)message.obj, 0);
                }
                case 22: {
                    this.this$0.setVolumeTo((int)message.obj, 0);
                }
            }
        }
    }
    
    public void post(final int n) {
        this.post(n, null);
    }
    
    public void post(final int n, final Object o) {
        this.obtainMessage(n, o).sendToTarget();
    }
    
    public void post(final int n, final Object o, final int n2) {
        this.obtainMessage(n, n2, 0, o).sendToTarget();
    }
    
    public void post(final int n, final Object o, final Bundle data) {
        final Message obtainMessage = this.obtainMessage(n, o);
        obtainMessage.setData(data);
        obtainMessage.sendToTarget();
    }
}
