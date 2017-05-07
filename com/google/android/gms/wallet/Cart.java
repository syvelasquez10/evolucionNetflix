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
    String abc;
    String abd;
    ArrayList<LineItem> abe;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    Cart() {
        this.xH = 1;
        this.abe = new ArrayList<LineItem>();
    }
    
    Cart(final int xh, final String abc, final String abd, final ArrayList<LineItem> abe) {
        this.xH = xh;
        this.abc = abc;
        this.abd = abd;
        this.abe = abe;
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
        return this.abd;
    }
    
    public ArrayList<LineItem> getLineItems() {
        return this.abe;
    }
    
    public String getTotalPrice() {
        return this.abc;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
    
    public final class Builder
    {
        public Builder addLineItem(final LineItem lineItem) {
            Cart.this.abe.add(lineItem);
            return this;
        }
        
        public Cart build() {
            return Cart.this;
        }
        
        public Builder setCurrencyCode(final String abd) {
            Cart.this.abd = abd;
            return this;
        }
        
        public Builder setLineItems(final List<LineItem> list) {
            Cart.this.abe.clear();
            Cart.this.abe.addAll(list);
            return this;
        }
        
        public Builder setTotalPrice(final String abc) {
            Cart.this.abc = abc;
            return this;
        }
    }
}
