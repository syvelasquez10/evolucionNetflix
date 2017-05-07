// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LineItem implements SafeParcelable
{
    public static final Parcelable$Creator<LineItem> CREATOR;
    private final int BR;
    String asE;
    String asF;
    int asG;
    String ask;
    String asl;
    String description;
    
    static {
        CREATOR = (Parcelable$Creator)new i();
    }
    
    LineItem() {
        this.BR = 1;
        this.asG = 0;
    }
    
    LineItem(final int br, final String description, final String asE, final String asF, final String ask, final int asG, final String asl) {
        this.BR = br;
        this.description = description;
        this.asE = asE;
        this.asF = asF;
        this.ask = ask;
        this.asG = asG;
        this.asl = asl;
    }
    
    public static Builder newBuilder() {
        final LineItem lineItem = new LineItem();
        lineItem.getClass();
        return new Builder();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getCurrencyCode() {
        return this.asl;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getQuantity() {
        return this.asE;
    }
    
    public int getRole() {
        return this.asG;
    }
    
    public String getTotalPrice() {
        return this.ask;
    }
    
    public String getUnitPrice() {
        return this.asF;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        i.a(this, parcel, n);
    }
    
    public final class Builder
    {
        public LineItem build() {
            return LineItem.this;
        }
        
        public Builder setCurrencyCode(final String asl) {
            LineItem.this.asl = asl;
            return this;
        }
        
        public Builder setDescription(final String description) {
            LineItem.this.description = description;
            return this;
        }
        
        public Builder setQuantity(final String asE) {
            LineItem.this.asE = asE;
            return this;
        }
        
        public Builder setRole(final int asG) {
            LineItem.this.asG = asG;
            return this;
        }
        
        public Builder setTotalPrice(final String ask) {
            LineItem.this.ask = ask;
            return this;
        }
        
        public Builder setUnitPrice(final String asF) {
            LineItem.this.asF = asF;
            return this;
        }
    }
    
    public interface Role
    {
        public static final int REGULAR = 0;
        public static final int SHIPPING = 2;
        public static final int TAX = 1;
    }
}
