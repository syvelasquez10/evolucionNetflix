// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.model.moments;

import com.google.android.gms.internal.nv;
import java.util.HashSet;
import com.google.android.gms.internal.nt;
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
        private String BL;
        private final Set<Integer> alR;
        private String amE;
        private nt amM;
        private nt amN;
        private String uO;
        
        public Builder() {
            this.alR = new HashSet<Integer>();
        }
        
        public Moment build() {
            return new nv(this.alR, this.BL, this.amM, this.amE, this.amN, this.uO);
        }
        
        public Builder setId(final String bl) {
            this.BL = bl;
            this.alR.add(2);
            return this;
        }
        
        public Builder setResult(final ItemScope itemScope) {
            this.amM = (nt)itemScope;
            this.alR.add(4);
            return this;
        }
        
        public Builder setStartDate(final String amE) {
            this.amE = amE;
            this.alR.add(5);
            return this;
        }
        
        public Builder setTarget(final ItemScope itemScope) {
            this.amN = (nt)itemScope;
            this.alR.add(6);
            return this;
        }
        
        public Builder setType(final String uo) {
            this.uO = uo;
            this.alR.add(7);
            return this;
        }
    }
}
