// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.util.Log;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

final class l$8 implements DialogInterface$OnClickListener
{
    final /* synthetic */ l a;
    
    l$8(final l a) {
        this.a = a;
    }
    
    public final void onClick(final DialogInterface dialogInterface, final int n) {
        try {
            l.i();
        }
        catch (Exception ex) {
            Log.w("CrittercismInstance", "MAYBE LATER button failed.  Email support@crittercism.com.");
        }
    }
}
