// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import android.content.Context;
import com.google.android.gms.common.internal.n;

public final class DataSource$Builder
{
    private int FD;
    private DataType SF;
    private Device SI;
    private a SJ;
    private String SK;
    private boolean SL;
    private String mName;
    
    public DataSource$Builder() {
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
    
    public DataSource$Builder setAppPackageName(final Context context) {
        return this.setAppPackageName(context.getPackageName());
    }
    
    public DataSource$Builder setAppPackageName(final String s) {
        this.SJ = new a(s, null, null);
        return this;
    }
    
    public DataSource$Builder setDataType(final DataType sf) {
        this.SF = sf;
        return this;
    }
    
    public DataSource$Builder setDevice(final Device si) {
        this.SI = si;
        return this;
    }
    
    public DataSource$Builder setName(final String mName) {
        this.mName = mName;
        return this;
    }
    
    public DataSource$Builder setObfuscated(final boolean sl) {
        this.SL = sl;
        return this;
    }
    
    public DataSource$Builder setStreamName(final String sk) {
        n.b(sk != null, (Object)"Must specify a valid stream name");
        this.SK = sk;
        return this;
    }
    
    public DataSource$Builder setType(final int fd) {
        this.FD = fd;
        return this;
    }
}
