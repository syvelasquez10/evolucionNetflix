// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.app.AlertDialog;
import android.widget.CompoundButton;
import android.widget.CompoundButton$OnCheckedChangeListener;

class SendAsFacebookMessageDialog$1 implements CompoundButton$OnCheckedChangeListener
{
    final /* synthetic */ SendAsFacebookMessageDialog this$0;
    
    SendAsFacebookMessageDialog$1(final SendAsFacebookMessageDialog this$0) {
        this.this$0 = this$0;
    }
    
    public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
        ((AlertDialog)this.this$0.getDialog()).getButton(-2).setEnabled(!b);
    }
}
