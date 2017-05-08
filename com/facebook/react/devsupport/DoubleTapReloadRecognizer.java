// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.devsupport;

import android.os.Handler;
import android.widget.EditText;
import android.view.View;

public class DoubleTapReloadRecognizer
{
    private boolean mDoRefresh;
    
    public DoubleTapReloadRecognizer() {
        this.mDoRefresh = false;
    }
    
    public boolean didDoubleTapR(final int n, final View view) {
        if (n == 46 && !(view instanceof EditText)) {
            if (this.mDoRefresh) {
                this.mDoRefresh = false;
                return true;
            }
            this.mDoRefresh = true;
            new Handler().postDelayed((Runnable)new DoubleTapReloadRecognizer$1(this), 200L);
        }
        return false;
    }
}
