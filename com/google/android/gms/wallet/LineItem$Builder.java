// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

public final class LineItem$Builder
{
    final /* synthetic */ LineItem asH;
    
    private LineItem$Builder(final LineItem asH) {
        this.asH = asH;
    }
    
    public LineItem build() {
        return this.asH;
    }
    
    public LineItem$Builder setCurrencyCode(final String asl) {
        this.asH.asl = asl;
        return this;
    }
    
    public LineItem$Builder setDescription(final String description) {
        this.asH.description = description;
        return this;
    }
    
    public LineItem$Builder setQuantity(final String asE) {
        this.asH.asE = asE;
        return this;
    }
    
    public LineItem$Builder setRole(final int asG) {
        this.asH.asG = asG;
        return this;
    }
    
    public LineItem$Builder setTotalPrice(final String ask) {
        this.asH.ask = ask;
        return this;
    }
    
    public LineItem$Builder setUnitPrice(final String asF) {
        this.asH.asF = asF;
        return this;
    }
}
