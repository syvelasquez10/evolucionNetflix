// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.content.Context;
import android.telephony.TelephonyManager;

public final class bx$o implements bw
{
    public Integer a;
    
    public bx$o() {
        this.a = 0;
        bx.b;
        try {
            final String networkOperator = ((TelephonyManager)bx.b.getSystemService("phone")).getNetworkOperator();
            if (networkOperator != null) {
                this.a = Integer.parseInt(networkOperator.substring(0, 3));
            }
            new StringBuilder("mobileCountryCode == ").append(this.a);
            dy.b();
        }
        catch (Exception ex) {}
    }
    
    @Override
    public final String a() {
        return "mobile_country_code";
    }
}
