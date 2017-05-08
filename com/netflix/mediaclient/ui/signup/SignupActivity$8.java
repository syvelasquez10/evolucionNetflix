// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View$OnTouchListener;

class SignupActivity$8 implements View$OnTouchListener
{
    final /* synthetic */ SignupActivity this$0;
    
    SignupActivity$8(final SignupActivity this$0) {
        this.this$0 = this$0;
    }
    
    @SuppressLint({ "ClickableViewAccessibility" })
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
            case 1: {
                if (!view.hasFocus()) {
                    view.requestFocus();
                    break;
                }
                break;
            }
        }
        return false;
    }
}
