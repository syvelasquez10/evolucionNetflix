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
    String abc;
    String abd;
    String abv;
    String abw;
    int abx;
    String description;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new i();
    }
    
    LineItem() {
        this.xH = 1;
        this.abx = 0;
    }
    
    LineItem(final int xh, final String description, final String abv, final String abw, final String abc, final int abx, final String abd) {
        this.xH = xh;
        this.description = description;
        this.abv = abv;
        this.abw = abw;
        this.abc = abc;
        this.abx = abx;
        this.abd = abd;
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
        return this.abd;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getQuantity() {
        return this.abv;
    }
    
    public int getRole() {
        return this.abx;
    }
    
    public String getTotalPrice() {
        return this.abc;
    }
    
    public String getUnitPrice() {
        return this.abw;
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        i.a(this, parcel, n);
    }
    
    public final class Builder
    {
        public LineItem build() {
            return LineItem.this;
        }
        
        public Builder setCurrencyCode(final String abd) {
            LineItem.this.abd = abd;
            return this;
        }
        
        public Builder setDescription(final String description) {
            LineItem.this.description = description;
            return this;
        }
        
        public Builder setQuantity(final String abv) {
            LineItem.this.abv = abv;
            return this;
        }
        
        public Builder setRole(final int abx) {
            LineItem.this.abx = abx;
            return this;
        }
        
        public Builder setTotalPrice(final String abc) {
            LineItem.this.abc = abc;
            return this;
        }
        
        public Builder setUnitPrice(final String abw) {
            LineItem.this.abw = abw;
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
