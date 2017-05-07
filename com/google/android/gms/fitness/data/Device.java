// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.internal.kv;
import android.os.Build$VERSION;
import android.os.Build;
import com.google.android.gms.common.internal.m;
import android.telephony.TelephonyManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Log;
import android.content.Context;
import com.google.android.gms.common.internal.n;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class Device implements SafeParcelable
{
    public static final Parcelable$Creator<Device> CREATOR;
    public static final int TYPE_CHEST_STRAP = 4;
    public static final int TYPE_PHONE = 1;
    public static final int TYPE_SCALE = 5;
    public static final int TYPE_TABLET = 2;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_WATCH = 3;
    private final int BR;
    private final int FD;
    private final String SQ;
    private final String SR;
    private final String SS;
    private final String Sq;
    
    static {
        CREATOR = (Parcelable$Creator)new i();
    }
    
    Device(final int br, final String s, final String s2, final String s3, final String s4, final int fd) {
        this.BR = br;
        this.SQ = n.i(s);
        this.SR = n.i(s2);
        this.Sq = "";
        this.SS = n.i(s4);
        this.FD = fd;
    }
    
    public Device(final String s, final String s2, final String s3, final int n) {
        this(1, s, s2, "", s3, n);
    }
    
    public Device(final String s, final String s2, final String s3, final String s4, final int n) {
        this(s, s2, s4, n);
    }
    
    private static int M(final Context context) {
        int n = 0;
        switch (O(context)) {
            default: {
                if (Q(context)) {
                    n = 1;
                    return n;
                }
                return 2;
            }
            case 8:
            case 9: {
                return n;
            }
            case 10: {
                n = n;
                if (N(context)) {
                    return 3;
                }
                return n;
            }
        }
    }
    
    public static boolean N(final Context context) {
        return (context.getResources().getConfiguration().uiMode & 0xF) == 0x6;
    }
    
    private static int O(final Context context) {
        return P(context) % 1000 / 100 + 5;
    }
    
    private static int P(final Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        }
        catch (PackageManager$NameNotFoundException ex) {
            Log.w("Fitness", "Could not find package info for Google Play Services");
            return -1;
        }
    }
    
    private static boolean Q(final Context context) {
        return ((TelephonyManager)context.getSystemService("phone")).getPhoneType() != 0;
    }
    
    private boolean a(final Device device) {
        return m.equal(this.SQ, device.SQ) && m.equal(this.SR, device.SR) && m.equal(this.Sq, device.Sq) && m.equal(this.SS, device.SS) && this.FD == device.FD;
    }
    
    public static Device getLocalDevice(final Context context) {
        return new Device(Build.MANUFACTURER, Build.MODEL, Build$VERSION.RELEASE, Build.SERIAL, M(context));
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof Device && this.a((Device)o));
    }
    
    public String getManufacturer() {
        return this.SQ;
    }
    
    public String getModel() {
        return this.SR;
    }
    
    String getStreamIdentifier() {
        return String.format("%s:%s:%s", this.SQ, this.SR, this.SS);
    }
    
    public int getType() {
        return this.FD;
    }
    
    public String getUid() {
        return this.SS;
    }
    
    public String getVersion() {
        return this.Sq;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.SQ, this.SR, this.Sq, this.SS, this.FD);
    }
    
    Device iM() {
        return new Device(kv.bq(this.SQ), kv.bq(this.SR), kv.bq(this.Sq), this.SS, this.FD);
    }
    
    public String iN() {
        if (kv.iU()) {
            return this.SS;
        }
        return kv.bq(this.SS);
    }
    
    @Override
    public String toString() {
        return String.format("Device{%s:%s:%s}", this.getStreamIdentifier(), this.Sq, this.FD);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        i.a(this, parcel, n);
    }
}
