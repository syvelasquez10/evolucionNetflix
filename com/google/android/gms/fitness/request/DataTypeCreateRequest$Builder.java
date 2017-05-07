// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.common.internal.n;
import java.util.ArrayList;
import com.google.android.gms.fitness.data.Field;
import java.util.List;

public class DataTypeCreateRequest$Builder
{
    private List<Field> SN;
    private String mName;
    
    public DataTypeCreateRequest$Builder() {
        this.SN = new ArrayList<Field>();
    }
    
    public DataTypeCreateRequest$Builder addField(final Field field) {
        if (!this.SN.contains(field)) {
            this.SN.add(field);
        }
        return this;
    }
    
    public DataTypeCreateRequest$Builder addField(final String s, final int n) {
        n.b(s != null || !s.isEmpty(), (Object)"Invalid name specified");
        return this.addField(new Field(s, n));
    }
    
    public DataTypeCreateRequest build() {
        final boolean b = true;
        n.a(this.mName != null, (Object)"Must set the name");
        n.a(!this.SN.isEmpty() && b, (Object)"Must specify the data fields");
        return new DataTypeCreateRequest(this, null);
    }
    
    public DataTypeCreateRequest$Builder setName(final String mName) {
        this.mName = mName;
        return this;
    }
}
