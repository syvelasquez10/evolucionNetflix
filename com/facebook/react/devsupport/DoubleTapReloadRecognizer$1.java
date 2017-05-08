// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.devsupport;

import android.os.Handler;
import android.widget.EditText;
import android.view.View;

class DoubleTapReloadRecognizer$1 implements Runnable
{
    final /* synthetic */ DoubleTapReloadRecognizer this$0;
    
    DoubleTapReloadRecognizer$1(final DoubleTapReloadRecognizer this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.mDoRefresh = false;
    }
}
