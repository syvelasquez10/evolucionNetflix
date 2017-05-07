// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import com.google.android.gms.common.internal.n;

public class Subscription$a
{
    private DataType SF;
    private DataSource Sh;
    private long Ti;
    private int Tj;
    
    public Subscription$a() {
        this.Ti = -1L;
        this.Tj = 2;
    }
    
    public Subscription$a b(final DataSource sh) {
        this.Sh = sh;
        return this;
    }
    
    public Subscription$a b(final DataType sf) {
        this.SF = sf;
        return this;
    }
    
    public Subscription iR() {
        final boolean b = false;
        n.a(this.Sh != null || this.SF != null, (Object)"Must call setDataSource() or setDataType()");
        boolean b2 = false;
        Label_0059: {
            if (this.SF != null && this.Sh != null) {
                b2 = b;
                if (!this.SF.equals(this.Sh.getDataType())) {
                    break Label_0059;
                }
            }
            b2 = true;
        }
        n.a(b2, (Object)"Specified data type is incompatible with specified data source");
        return new Subscription(this, null);
    }
}
