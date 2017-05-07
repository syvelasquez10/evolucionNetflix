// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import android.content.Context;
import com.google.android.gms.common.internal.n;
import android.os.Parcel;
import com.google.android.gms.internal.kv;
import com.google.android.gms.common.internal.m;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DataSource implements SafeParcelable
{
    public static final Parcelable$Creator<DataSource> CREATOR;
    public static final int TYPE_DERIVED = 1;
    public static final int TYPE_RAW = 0;
    private final int BR;
    private final int FD;
    private final DataType SF;
    private final Device SI;
    private final a SJ;
    private final String SK;
    private final boolean SL;
    private final String SM;
    private final String mName;
    
    static {
        CREATOR = (Parcelable$Creator)new g();
    }
    
    DataSource(final int br, final DataType sf, final String mName, final int fd, final Device si, final a sj, final String sk, final boolean sl) {
        this.BR = br;
        this.SF = sf;
        this.FD = fd;
        this.mName = mName;
        this.SI = si;
        this.SJ = sj;
        this.SK = sk;
        this.SL = sl;
        this.SM = this.iI();
    }
    
    private DataSource(final DataSource$Builder dataSource$Builder) {
        this.BR = 3;
        this.SF = dataSource$Builder.SF;
        this.FD = dataSource$Builder.FD;
        this.mName = dataSource$Builder.mName;
        this.SI = dataSource$Builder.SI;
        this.SJ = dataSource$Builder.SJ;
        this.SK = dataSource$Builder.SK;
        this.SL = dataSource$Builder.SL;
        this.SM = this.iI();
    }
    
    private boolean a(final DataSource dataSource) {
        return this.SF.equals(dataSource.SF) && this.FD == dataSource.FD && m.equal(this.mName, dataSource.mName) && m.equal(this.SI, dataSource.SI) && m.equal(this.SK, dataSource.SK) && m.equal(this.SJ, dataSource.SJ);
    }
    
    private String getTypeString() {
        switch (this.FD) {
            default: {
                throw new IllegalArgumentException("invalid type value");
            }
            case 0: {
                return "raw";
            }
            case 1: {
                return "derived";
            }
        }
    }
    
    private String iI() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getTypeString());
        sb.append(":").append(this.SF.getName());
        if (this.SJ != null) {
            sb.append(":").append(this.SJ.getPackageName());
        }
        if (this.SI != null) {
            sb.append(":").append(this.SI.getStreamIdentifier());
        }
        if (this.SK != null) {
            sb.append(":").append(this.SK);
        }
        return sb.toString();
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof DataSource && this.a((DataSource)o));
    }
    
    public String getAppPackageName() {
        if (this.SJ == null) {
            return null;
        }
        return this.SJ.getPackageName();
    }
    
    public DataType getDataType() {
        return this.SF;
    }
    
    public Device getDevice() {
        return this.SI;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public String getStreamIdentifier() {
        return this.SM;
    }
    
    public String getStreamName() {
        return this.SK;
    }
    
    public int getType() {
        return this.FD;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return this.SM.hashCode();
    }
    
    public a iH() {
        return this.SJ;
    }
    
    public boolean iJ() {
        return this.SL;
    }
    
    public DataSource iK() {
        Device im;
        if (this.SI == null) {
            im = null;
        }
        else {
            im = this.SI.iM();
        }
        a ia;
        if (this.SJ == null) {
            ia = null;
        }
        else {
            ia = this.SJ.iA();
        }
        return new DataSource(3, this.SF, this.mName, this.FD, im, ia, kv.bq(this.SK), this.SL);
    }
    
    public String toDebugString() {
        final StringBuilder sb = new StringBuilder();
        String s;
        if (this.FD == 0) {
            s = "r";
        }
        else {
            s = "d";
        }
        final StringBuilder append = sb.append(s).append(":").append(this.SF.iL());
        String string;
        if (this.SJ == null) {
            string = "";
        }
        else if (this.SJ.equals(a.Sp)) {
            string = ":gms";
        }
        else {
            string = ":" + this.SJ.getPackageName();
        }
        final StringBuilder append2 = append.append(string);
        String string2;
        if (this.SI != null) {
            string2 = ":" + this.SI.getModel();
        }
        else {
            string2 = "";
        }
        final StringBuilder append3 = append2.append(string2);
        String string3;
        if (this.SK != null) {
            string3 = ":" + this.SK;
        }
        else {
            string3 = "";
        }
        return append3.append(string3).toString();
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DataSource{");
        sb.append(this.getTypeString());
        if (this.mName != null) {
            sb.append(":").append(this.mName);
        }
        if (this.SJ != null) {
            sb.append(":").append(this.SJ);
        }
        if (this.SI != null) {
            sb.append(":").append(this.SI);
        }
        if (this.SK != null) {
            sb.append(":").append(this.SK);
        }
        sb.append(":").append(this.SF);
        return sb.append("}").toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        g.a(kv.c(this), parcel, n);
    }
}
