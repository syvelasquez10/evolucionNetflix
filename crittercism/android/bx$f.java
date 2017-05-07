// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

public final class bx$f implements bw
{
    public String a;
    
    public bx$f() {
        this.a = null;
        bx.b;
        while (true) {
            try {
                final String a = ((TelephonyManager)bx.b.getSystemService("phone")).getNetworkOperatorName();
                this.a = a;
                new StringBuilder("carrier == ").append(this.a);
                dy.b();
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
