// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import java.util.Collection;
import java.util.List;

public final class Cart$Builder
{
    final /* synthetic */ Cart asn;
    
    private Cart$Builder(final Cart asn) {
        this.asn = asn;
    }
    
    public Cart$Builder addLineItem(final LineItem lineItem) {
        this.asn.asm.add(lineItem);
        return this;
    }
    
    public Cart build() {
        return this.asn;
    }
    
    public Cart$Builder setCurrencyCode(final String asl) {
        this.asn.asl = asl;
        return this;
    }
    
    public Cart$Builder setLineItems(final List<LineItem> list) {
        this.asn.asm.clear();
        this.asn.asm.addAll(list);
        return this;
    }
    
    public Cart$Builder setTotalPrice(final String ask) {
        this.asn.ask = ask;
        return this;
    }
}
