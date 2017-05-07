// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.model.moments;

import com.google.android.gms.internal.ie;
import java.util.HashSet;
import com.google.android.gms.internal.ic;
import java.util.Set;
import com.google.android.gms.common.data.Freezable;

public interface Moment extends Freezable<Moment>
{
    String getId();
    
    ItemScope getResult();
    
    String getStartDate();
    
    ItemScope getTarget();
    
    String getType();
    
    boolean hasId();
    
    boolean hasResult();
    
    boolean hasStartDate();
    
    boolean hasTarget();
    
    boolean hasType();
    
    public static class Builder
    {
        private String Rd;
        private final Set<Integer> UJ;
        private ic VE;
        private ic VF;
        private String Vw;
        private String wp;
        
        public Builder() {
            this.UJ = new HashSet<Integer>();
        }
        
        public Moment build() {
            return new ie(this.UJ, this.wp, this.VE, this.Vw, this.VF, this.Rd);
        }
        
        public Builder setId(final String wp) {
            this.wp = wp;
            this.UJ.add(2);
            return this;
        }
        
        public Builder setResult(final ItemScope itemScope) {
            this.VE = (ic)itemScope;
            this.UJ.add(4);
            return this;
        }
        
        public Builder setStartDate(final String vw) {
            this.Vw = vw;
            this.UJ.add(5);
            return this;
        }
        
        public Builder setTarget(final ItemScope itemScope) {
            this.VF = (ic)itemScope;
            this.UJ.add(6);
            return this;
        }
        
        public Builder setType(final String rd) {
            this.Rd = rd;
            this.UJ.add(7);
            return this;
        }
    }
}
