// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public final class bb extends BroadcastReceiver
{
    private ax a;
    private String b;
    private b c;
    
    public bb(final Context context, final ax a) {
        this.a = a;
        final d d = new d(context);
        this.b = d.b();
        this.c = d.a();
    }
    
    public final void onReceive(final Context context, final Intent intent) {
        dw.d("CrittercismReceiver: INTENT ACTION = " + intent.getAction());
        final d d = new d(context);
        final b a = d.a();
        if (this.c != a && a != com.crittercism.internal.b.c) {
            if (a == com.crittercism.internal.b.d) {
                this.a.a(new cc(cc$a.b));
            }
            else if (this.c == com.crittercism.internal.b.d || this.c == com.crittercism.internal.b.c) {
                this.a.a(new cc(cc$a.a));
            }
            this.c = a;
        }
        final String b = d.b();
        if (!b.equals(this.b)) {
            if (this.b.equals("unknown") || this.b.equals("disconnected")) {
                if (!b.equals("unknown") && !b.equals("disconnected")) {
                    this.a.a(new cc(cc$a.c, b));
                }
            }
            else if (b.equals("disconnected")) {
                this.a.a(new cc(cc$a.d, this.b));
            }
            else if (!b.equals("unknown")) {
                this.a.a(new cc(cc$a.e, this.b, b));
            }
            this.b = b;
        }
    }
}
