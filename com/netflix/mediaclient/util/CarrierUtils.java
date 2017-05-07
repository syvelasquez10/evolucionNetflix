// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.Log;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.content.Context;
import com.netflix.mediaclient.NetflixApplication;
import android.telephony.PhoneStateListener;

public class CarrierUtils extends PhoneStateListener
{
    private static final String TAG = "nf-carrier";
    private static CarrierUtils instance;
    private String carrier;
    private NetflixApplication context;
    private boolean listening;
    
    public static CarrierUtils getInstance() {
        synchronized (CarrierUtils.class) {
            if (CarrierUtils.instance == null) {
                CarrierUtils.instance = new CarrierUtils();
            }
            return CarrierUtils.instance;
        }
    }
    
    public void destroy(final Context context) {
        synchronized (this) {
            final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
            if (telephonyManager != null && this.listening) {
                telephonyManager.listen((PhoneStateListener)this, 0);
                this.listening = false;
            }
            this.context = null;
            CarrierUtils.instance = null;
        }
    }
    
    public String getCarrier() {
        synchronized (this) {
            if (this.context != null) {
                this.updateCarrier(this.context);
            }
            return this.carrier;
        }
    }
    
    public void onServiceStateChanged(final ServiceState serviceState) {
        if (serviceState != null) {
            final String operatorAlphaShort = serviceState.getOperatorAlphaShort();
            Log.d("nf-carrier", "onServiceStateChanged: New carrier: " + operatorAlphaShort + ", old carrier: " + this.carrier);
            if (operatorAlphaShort == null || operatorAlphaShort.indexOf("N/A") > -1) {
                Log.w("nf-carrier", "onServiceStateChanged: Invalid carrier name, keep old carrier data");
            }
            else {
                this.carrier = operatorAlphaShort;
            }
        }
        else {
            Log.w("nf-carrier", "onServiceStateChanged: null!");
        }
        super.onServiceStateChanged(serviceState);
    }
    
    public void updateCarrier(final NetflixApplication context) {
        // monitorenter(this)
        if (context == null) {
            try {
                throw new IllegalArgumentException("Context is null!");
            }
            finally {
            }
            // monitorexit(this)
        }
        this.context = context;
        final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
        if (telephonyManager != null) {
            this.carrier = telephonyManager.getNetworkOperatorName();
            if (this.carrier == null) {
                this.carrier = "";
                Log.w("nf-carrier", "Carrier not received!");
            }
            else {
                Log.d("nf-carrier", "Carrier: " + this.carrier);
            }
            if (!this.listening) {
                telephonyManager.listen((PhoneStateListener)this, 1);
                this.listening = true;
            }
        }
        else {
            Log.w("nf-carrier", "Telephony manager not found!");
        }
    }
    // monitorexit(this)
}
