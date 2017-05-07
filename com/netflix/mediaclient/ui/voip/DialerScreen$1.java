// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.voip;

import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import android.app.Activity;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.IVoip$Call;
import com.netflix.mediaclient.Log;
import android.media.ToneGenerator;
import android.widget.ImageView;
import android.os.Handler;
import com.netflix.mediaclient.util.TimeFormatterHelper;
import android.support.design.widget.FloatingActionButton;
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
                return true;
            }
            case 0: {
                this.this$0.handleButtonTounch(view, true);
                return true;
            }
            case 1: {
                this.this$0.handleButtonTounch(view, false);
                return true;
            }
        }
    }
}
