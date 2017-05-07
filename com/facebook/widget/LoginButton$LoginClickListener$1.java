// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import android.content.DialogInterface;
import com.facebook.Session;
import android.content.DialogInterface$OnClickListener;

class LoginButton$LoginClickListener$1 implements DialogInterface$OnClickListener
{
    final /* synthetic */ LoginButton$LoginClickListener this$1;
    final /* synthetic */ Session val$openSession;
    
    LoginButton$LoginClickListener$1(final LoginButton$LoginClickListener this$1, final Session val$openSession) {
        this.this$1 = this$1;
        this.val$openSession = val$openSession;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.val$openSession.closeAndClearTokenInformation();
    }
}
