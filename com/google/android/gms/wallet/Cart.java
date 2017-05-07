// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import java.util.Collection;
import java.util.List;
import android.os.Parcel;
import java.util.ArrayList;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class Cart implements SafeParcelable
{
    public static final Parcelable$Creator<Cart> CREATOR;
    private final int BR;
    String ask;
    String asl;
    ArrayList<LineItem> asm;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    Cart() {
        this.BR = 1;
        this.asm = new ArrayList<LineItem>();
    }
    
    Cart(final int br, final String ask, final String asl, final ArrayList<LineItem> asm) {
        this.BR = br;
        this.ask = ask;
        this.asl = asl;
        this.asm = asm;
    }
    
    public static Builder newBuilder() {
        final Cart cart = new Cart();
        cart.getClass();
        return new Builder();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getCurrencyCode() {
        return this.asl;
    }
    
    public ArrayList<LineItem> getLineItems() {
        return this.asm;
    }
    
    public String getTotalPrice() {
        return this.ask;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
    
    public final class Builder
    {
        public Builder addLineItem(final LineItem lineItem) {
            Cart.this.asm.add(lineItem);
            return this;
        }
        
        public Cart build() {
            return Cart.this;
        }
        
        public Builder setCurrencyCode(final String asl) {
            Cart.this.asl = asl;
            return this;
        }
        
        public Builder setLineItems(final List<LineItem> list) {
            Cart.this.asm.clear();
            Cart.this.asm.addAll(list);
            return this;
        }
        
        public Builder setTotalPrice(final String ask) {
            Cart.this.ask = ask;
            return this;
        }
    }
}
