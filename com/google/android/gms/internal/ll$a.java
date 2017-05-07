// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.IntentSender$SendIntentException;
import com.google.android.gms.common.ConnectionResult;
import android.app.PendingIntent;
import android.app.PendingIntent$CanceledException;
import android.util.Log;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

public final class ll$a extends lm$a
{
    private final int Lm;
    private Activity nr;
    
    public ll$a(final int lm, final Activity nr) {
        this.Lm = lm;
        this.nr = nr;
    }
    
    private void setActivity(final Activity nr) {
        this.nr = nr;
    }
    
    public void g(final int n, final Bundle bundle) {
        Label_0056: {
            if (n != 1) {
                break Label_0056;
            }
            final Intent intent = new Intent();
            intent.putExtras(bundle);
            final PendingIntent pendingResult = this.nr.createPendingResult(this.Lm, intent, 1073741824);
            if (pendingResult != null) {
                try {
                    pendingResult.send(1);
                    return;
                }
                catch (PendingIntent$CanceledException ex) {
                    Log.w("AddressClientImpl", "Exception settng pending result", (Throwable)ex);
                    return;
                }
                break Label_0056;
            }
            return;
        }
        PendingIntent pendingIntent = null;
        if (bundle != null) {
            pendingIntent = (PendingIntent)bundle.getParcelable("com.google.android.gms.identity.intents.EXTRA_PENDING_INTENT");
        }
        final ConnectionResult connectionResult = new ConnectionResult(n, pendingIntent);
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this.nr, this.Lm);
                return;
            }
            catch (IntentSender$SendIntentException ex2) {
                Log.w("AddressClientImpl", "Exception starting pending intent", (Throwable)ex2);
                return;
            }
        }
        try {
            final PendingIntent pendingResult2 = this.nr.createPendingResult(this.Lm, new Intent(), 1073741824);
            if (pendingResult2 != null) {
                pendingResult2.send(1);
            }
        }
        catch (PendingIntent$CanceledException ex3) {
            Log.w("AddressClientImpl", "Exception setting pending result", (Throwable)ex3);
        }
    }
}
