// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.util.Log;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

final class l$6 implements DialogInterface$OnClickListener
{
    final /* synthetic */ String a;
    final /* synthetic */ l b;
    
    l$6(final l b, final String a) {
        this.b = b;
        this.a = a;
    }
    
    public final void onClick(final DialogInterface dialogInterface, final int n) {
        try {
            l.i().b(this.a);
        }
        catch (Exception ex) {
            Log.w("CrittercismInstance", "YES button failed.  Email support@crittercism.com.");
        }
    }
}
