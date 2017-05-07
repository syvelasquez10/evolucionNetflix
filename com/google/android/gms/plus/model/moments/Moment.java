// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.model.moments;

import com.google.android.gms.internal.id;
import java.util.HashSet;
import com.google.android.gms.internal.ib;
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
        private String AI;
        private final Set<Integer> Eq;
        private String Fe;
        private ib Fm;
        private ib Fn;
        private String uS;
        
        public Builder() {
            this.Eq = new HashSet<Integer>();
        }
        
        public Moment build() {
            return new id(this.Eq, this.uS, this.Fm, this.Fe, this.Fn, this.AI);
        }
        
        public Builder setId(final String us) {
            this.uS = us;
            this.Eq.add(2);
            return this;
        }
        
        public Builder setResult(final ItemScope itemScope) {
            this.Fm = (ib)itemScope;
            this.Eq.add(4);
            return this;
        }
        
        public Builder setStartDate(final String fe) {
            this.Fe = fe;
            this.Eq.add(5);
            return this;
        }
        
        public Builder setTarget(final ItemScope itemScope) {
            this.Fn = (ib)itemScope;
            this.Eq.add(6);
            return this;
        }
        
        public Builder setType(final String ai) {
            this.AI = ai;
            this.Eq.add(7);
            return this;
        }
    }
}
