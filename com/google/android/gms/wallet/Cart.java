// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

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
    
    public static Cart$Builder newBuilder() {
        final Cart cart = new Cart();
        cart.getClass();
        return new Cart$Builder(cart, null);
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
}
