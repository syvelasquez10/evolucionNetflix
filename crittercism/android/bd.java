// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public final class bd extends BroadcastReceiver
{
    private az a;
    private String b;
    private b c;
    
    public bd(final Context context, final az a) {
        this.a = a;
        final d d = new d(context);
        this.b = d.b();
        this.c = d.a();
    }
    
    public final void onReceive(final Context context, final Intent intent) {
        new StringBuilder("CrittercismReceiver: INTENT ACTION = ").append(intent.getAction());
        dy.b();
        final d d = new d(context);
        final b a = d.a();
        if (this.c != a && a != crittercism.android.b.c) {
            if (a == crittercism.android.b.d) {
                this.a.a(new ce(ce$a.b));
            }
            else if (this.c == crittercism.android.b.d || this.c == crittercism.android.b.c) {
                this.a.a(new ce(ce$a.a));
            }
            this.c = a;
        }
        final String b = d.b();
        if (!b.equals(this.b)) {
            if (this.b.equals("unknown") || this.b.equals("disconnected")) {
                if (!b.equals("unknown") && !b.equals("disconnected")) {
                    this.a.a(new ce(ce$a.c, b));
                }
            }
            else if (b.equals("disconnected")) {
                this.a.a(new ce(ce$a.d, this.b));
            }
            else if (!b.equals("unknown")) {
                this.a.a(new ce(ce$a.e, this.b, b));
            }
            this.b = b;
        }
    }
}
