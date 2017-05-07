// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import android.content.DialogInterface;
import android.content.DialogInterface$OnCancelListener;

class WebDialog$1 implements DialogInterface$OnCancelListener
{
    final /* synthetic */ WebDialog this$0;
    
    WebDialog$1(final WebDialog this$0) {
        this.this$0 = this$0;
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        this.this$0.dismiss();
    }
}
