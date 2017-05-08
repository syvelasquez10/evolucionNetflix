// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

final class ax$13 implements DialogInterface$OnClickListener
{
    final /* synthetic */ ax a;
    
    ax$13(final ax a) {
        this.a = a;
    }
    
    public final void onClick(final DialogInterface dialogInterface, final int n) {
        try {
            ax.C().E();
        }
        catch (Exception ex) {
            dw.b("NO button failed.  Email support@crittercism.com.");
        }
    }
}
