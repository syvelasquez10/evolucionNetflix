// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

final class ax$12 implements DialogInterface$OnClickListener
{
    final /* synthetic */ String a;
    final /* synthetic */ ax b;
    
    ax$12(final ax b, final String a) {
        this.b = b;
        this.a = a;
    }
    
    public final void onClick(final DialogInterface dialogInterface, final int n) {
        try {
            ax.C().b(this.a);
        }
        catch (Exception ex) {
            dw.b("YES button failed.  Email support@crittercism.com.");
        }
    }
}
