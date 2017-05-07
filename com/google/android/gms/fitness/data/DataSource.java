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
    
    private DataSource(final Builder builder) {
        this.BR = 3;
        this.SF = builder.SF;
        this.FD = builder.FD;
        this.mName = builder.mName;
        this.SI = builder.SI;
        this.SJ = builder.SJ;
        this.SK = builder.SK;
        this.SL = builder.SL;
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
    
    public static final class Builder
    {
        private int FD;
        private DataType SF;
        private Device SI;
        private a SJ;
        private String SK;
        private boolean SL;
        private String mName;
        
        public Builder() {
            this.FD = -1;
            this.SK = "";
            this.SL = false;
        }
        
        public DataSource build() {
            final boolean b = true;
            n.a(this.SF != null, (Object)"Must set data type");
            n.a(this.FD >= 0 && b, (Object)"Must set data source type");
            return new DataSource(this, null);
        }
        
        public Builder setAppPackageName(final Context context) {
            return this.setAppPackageName(context.getPackageName());
        }
        
        public Builder setAppPackageName(final String s) {
            this.SJ = new a(s, null, null);
            return this;
        }
        
        public Builder setDataType(final DataType sf) {
            this.SF = sf;
            return this;
        }
        
        public Builder setDevice(final Device si) {
            this.SI = si;
            return this;
        }
        
        public Builder setName(final String mName) {
            this.mName = mName;
            return this;
        }
        
        public Builder setObfuscated(final boolean sl) {
            this.SL = sl;
            return this;
        }
        
        public Builder setStreamName(final String sk) {
            n.b(sk != null, (Object)"Must specify a valid stream name");
            this.SK = sk;
            return this;
        }
        
        public Builder setType(final int fd) {
            this.FD = fd;
            return this;
        }
    }
}
