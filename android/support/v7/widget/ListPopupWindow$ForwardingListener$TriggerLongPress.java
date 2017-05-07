// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.os.SystemClock;
import android.view.ViewConfiguration;
import android.view.View;
import android.view.View$OnTouchListener;

class ListPopupWindow$ForwardingListener$TriggerLongPress implements Runnable
{
    final /* synthetic */ ListPopupWindow$ForwardingListener this$0;
    
    private ListPopupWindow$ForwardingListener$TriggerLongPress(final ListPopupWindow$ForwardingListener this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.onLongPress();
    }
}
