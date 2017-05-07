// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import android.content.Context;
import android.webkit.WebView;

class WebDialog$3 extends WebView
{
    final /* synthetic */ WebDialog this$0;
    
    WebDialog$3(final WebDialog this$0, final Context context) {
        this.this$0 = this$0;
        super(context);
    }
    
    public void onWindowFocusChanged(final boolean b) {
        try {
            super.onWindowFocusChanged(b);
        }
        catch (NullPointerException ex) {}
    }
}
