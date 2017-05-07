// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.content.Context;
import android.view.ViewConfiguration;
import android.view.VelocityTracker;
import android.view.GestureDetector$OnGestureListener;
import android.view.GestureDetector$OnDoubleTapListener;
import android.view.MotionEvent;
import android.os.Message;
import android.os.Handler;

class GestureDetectorCompat$GestureDetectorCompatImplBase$GestureHandler extends Handler
{
    final /* synthetic */ GestureDetectorCompat$GestureDetectorCompatImplBase this$0;
    
    GestureDetectorCompat$GestureDetectorCompatImplBase$GestureHandler(final GestureDetectorCompat$GestureDetectorCompatImplBase this$0) {
        this.this$0 = this$0;
    }
    
    GestureDetectorCompat$GestureDetectorCompatImplBase$GestureHandler(final GestureDetectorCompat$GestureDetectorCompatImplBase this$0, final Handler handler) {
        this.this$0 = this$0;
        super(handler.getLooper());
    }
    
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {
                throw new RuntimeException("Unknown message " + message);
            }
            case 1: {
                this.this$0.mListener.onShowPress(this.this$0.mCurrentDownEvent);
                break;
            }
            case 2: {
                this.this$0.dispatchLongPress();
            }
            case 3: {
                if (this.this$0.mDoubleTapListener == null) {
                    break;
                }
                if (!this.this$0.mStillDown) {
                    this.this$0.mDoubleTapListener.onSingleTapConfirmed(this.this$0.mCurrentDownEvent);
                    return;
                }
                this.this$0.mDeferConfirmSingleTap = true;
            }
        }
    }
}
