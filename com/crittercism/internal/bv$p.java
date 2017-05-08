// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.content.Context;
import android.telephony.TelephonyManager;

public final class bv$p implements bu
{
    public Integer a;
    
    public bv$p() {
        this.a = 0;
        try {
            final String networkOperator = ((TelephonyManager)bv.b.getSystemService("phone")).getNetworkOperator();
            if (networkOperator != null) {
                this.a = Integer.parseInt(networkOperator.substring(3));
            }
            dw.d("mobileNetworkCode == " + this.a);
        }
        catch (Exception ex) {}
    }
    
    @Override
    public final String a() {
        return "mobile_network_code";
    }
}
