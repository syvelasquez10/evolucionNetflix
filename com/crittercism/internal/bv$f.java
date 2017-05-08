// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

public final class bv$f implements bu
{
    public String a;
    
    public bv$f() {
        this.a = null;
        while (true) {
            try {
                final String a = ((TelephonyManager)bv.b.getSystemService("phone")).getNetworkOperatorName();
                this.a = a;
                dw.d("carrier == " + this.a);
            }
            catch (Exception ex) {
                final String a = Build.BRAND;
                continue;
            }
            break;
        }
    }
    
    @Override
    public final String a() {
        return "carrier";
    }
}
