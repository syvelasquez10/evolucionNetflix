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
    String Gj;
    String Gk;
    ArrayList<LineItem> Gl;
    private final int kg;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    Cart() {
        this.kg = 1;
        this.Gl = new ArrayList<LineItem>();
    }
    
    Cart(final int kg, final String gj, final String gk, final ArrayList<LineItem> gl) {
        this.kg = kg;
        this.Gj = gj;
        this.Gk = gk;
        this.Gl = gl;
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
        return this.Gk;
    }
    
    public ArrayList<LineItem> getLineItems() {
        return this.Gl;
    }
    
    public String getTotalPrice() {
        return this.Gj;
    }
    
    public int getVersionCode() {
        return this.kg;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
    
    public final class Builder
    {
        public Builder addLineItem(final LineItem lineItem) {
            Cart.this.Gl.add(lineItem);
            return this;
        }
        
        public Cart build() {
            return Cart.this;
        }
        
        public Builder setCurrencyCode(final String gk) {
            Cart.this.Gk = gk;
            return this;
        }
        
        public Builder setLineItems(final List<LineItem> list) {
            Cart.this.Gl.clear();
            Cart.this.Gl.addAll(list);
            return this;
        }
        
        public Builder setTotalPrice(final String gj) {
            Cart.this.Gj = gj;
            return this;
        }
    }
}
