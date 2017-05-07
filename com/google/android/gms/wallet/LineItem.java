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
    String Gj;
    String Gk;
    String Gw;
    String Gx;
    int Gy;
    String description;
    private final int kg;
    
    static {
        CREATOR = (Parcelable$Creator)new f();
    }
    
    LineItem() {
        this.kg = 1;
        this.Gy = 0;
    }
    
    LineItem(final int kg, final String description, final String gw, final String gx, final String gj, final int gy, final String gk) {
        this.kg = kg;
        this.description = description;
        this.Gw = gw;
        this.Gx = gx;
        this.Gj = gj;
        this.Gy = gy;
        this.Gk = gk;
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
        return this.Gk;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getQuantity() {
        return this.Gw;
    }
    
    public int getRole() {
        return this.Gy;
    }
    
    public String getTotalPrice() {
        return this.Gj;
    }
    
    public String getUnitPrice() {
        return this.Gx;
    }
    
    public int getVersionCode() {
        return this.kg;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        f.a(this, parcel, n);
    }
    
    public final class Builder
    {
        public LineItem build() {
            return LineItem.this;
        }
        
        public Builder setCurrencyCode(final String gk) {
            LineItem.this.Gk = gk;
            return this;
        }
        
        public Builder setDescription(final String description) {
            LineItem.this.description = description;
            return this;
        }
        
        public Builder setQuantity(final String gw) {
            LineItem.this.Gw = gw;
            return this;
        }
        
        public Builder setRole(final int gy) {
            LineItem.this.Gy = gy;
            return this;
        }
        
        public Builder setTotalPrice(final String gj) {
            LineItem.this.Gj = gj;
            return this;
        }
        
        public Builder setUnitPrice(final String gx) {
            LineItem.this.Gx = gx;
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
