// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.voip;

import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.content.Context;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.servicemgr.IVoip$Call;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.AndroidUtils;
import android.media.ToneGenerator;
import android.os.Handler;
import com.netflix.mediaclient.util.TimeFormatterHelper;
import android.support.design.widget.FloatingActionButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.media.AudioManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View$OnTouchListener;

class DialerScreen$1 implements View$OnTouchListener
{
    final /* synthetic */ DialerScreen this$0;
    
    DialerScreen$1(final DialerScreen this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            default: {
                return false;
            }
            case 0: {
                this.this$0.handleButtonTounch(view, true);
                return false;
            }
            case 1: {
                this.this$0.handleButtonTounch(view, false);
                return false;
            }
        }
    }
}
