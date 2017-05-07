// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.content.Context;
import android.telephony.TelephonyManager;

public final class bx$p implements bw
{
    public Integer a;
    
    public bx$p() {
        this.a = 0;
        bx.b;
        try {
            final String networkOperator = ((TelephonyManager)bx.b.getSystemService("phone")).getNetworkOperator();
            if (networkOperator != null) {
                this.a = Integer.parseInt(networkOperator.substring(3));
            }
            new StringBuilder("mobileNetworkCode == ").append(this.a);
            dy.b();
        }
        catch (Exception ex) {}
    }
    
    @Override
    public final String a() {
        return "mobile_network_code";
    }
}
