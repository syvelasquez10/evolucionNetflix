// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;

public class ah$a
{
    private DataType SF;
    private DataSource Sh;
    
    public ah$a d(final DataSource sh) {
        this.Sh = sh;
        return this;
    }
    
    public ah$a d(final DataType sf) {
        this.SF = sf;
        return this;
    }
    
    public ah jE() {
        if (this.SF != null && this.Sh != null) {
            throw new IllegalArgumentException("Cannot specify both dataType and dataSource");
        }
        return new ah(this, null);
    }
}
