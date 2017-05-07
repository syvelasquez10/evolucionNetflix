// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.Iterator;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class nh implements SafeParcelable
{
    public static final nj CREATOR;
    public final long akw;
    public final byte[] akx;
    public final Bundle aky;
    public final String tag;
    public final int versionCode;
    
    static {
        CREATOR = new nj();
    }
    
    nh(final int versionCode, final long akw, final String tag, final byte[] akx, final Bundle aky) {
        this.versionCode = versionCode;
        this.akw = akw;
        this.tag = tag;
        this.akx = akx;
        this.aky = aky;
    }
    
    public nh(final long akw, final String tag, final byte[] akx, final String... array) {
        this.versionCode = 1;
        this.akw = akw;
        this.tag = tag;
        this.akx = akx;
        this.aky = f(array);
    }
    
    private static Bundle f(final String... array) {
        Bundle bundle = null;
        if (array != null) {
            if (array.length % 2 != 0) {
                throw new IllegalArgumentException("extras must have an even number of elements");
            }
            final int n = array.length / 2;
            if (n != 0) {
                final Bundle bundle2 = new Bundle(n);
                int n2 = 0;
                while (true) {
                    bundle = bundle2;
                    if (n2 >= n) {
                        break;
                    }
                    bundle2.putString(array[n2 * 2], array[n2 * 2 + 1]);
                    ++n2;
                }
            }
        }
        return bundle;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("tag=").append(this.tag).append(",");
        sb.append("eventTime=").append(this.akw).append(",");
        if (this.aky != null && !this.aky.isEmpty()) {
            sb.append("keyValues=");
            for (final String s : this.aky.keySet()) {
                sb.append("(").append(s).append(",");
                sb.append(this.aky.getString(s)).append(")");
                sb.append(" ");
            }
        }
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        nj.a(this, parcel, n);
    }
}
