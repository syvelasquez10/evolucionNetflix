// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Collections;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Map;
import java.util.List;
import android.net.Uri;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLng;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class hd extends gr implements SafeParcelable
{
    public static final he CREATOR;
    private final Bundle AM;
    private final hf AN;
    private final LatLng AO;
    private final float AP;
    private final LatLngBounds AQ;
    private final String AR;
    private final Uri AS;
    private final boolean AT;
    private final float AU;
    private final int AV;
    private final long AW;
    private final List<gx> AX;
    private final Map<gx, String> AY;
    private final TimeZone AZ;
    private Locale Ba;
    private hh Bb;
    final int kg;
    private final String uS;
    
    static {
        CREATOR = new he();
    }
    
    hd(final int kg, final String us, final List<gx> list, final Bundle am, final hf an, final LatLng ao, final float ap, final LatLngBounds aq, final String ar, final Uri as, final boolean at, final float au, final int av, final long aw) {
        this.kg = kg;
        this.uS = us;
        this.AX = Collections.unmodifiableList((List<? extends gx>)list);
        this.AM = am;
        this.AN = an;
        this.AO = ao;
        this.AP = ap;
        this.AQ = aq;
        this.AR = ar;
        this.AS = as;
        this.AT = at;
        this.AU = au;
        this.AV = av;
        this.AW = aw;
        final HashMap<gx, String> hashMap = new HashMap<gx, String>();
        for (final String s : am.keySet()) {
            hashMap.put(gx.aq(s), am.getString(s));
        }
        this.AY = (Map<gx, String>)Collections.unmodifiableMap((Map<?, ?>)hashMap);
        this.AZ = TimeZone.getTimeZone(this.AR);
        this.Ba = null;
        this.Bb = null;
    }
    
    private void ar(final String s) {
        if (this.Bb != null) {
            this.Bb.a(new a.a(this.uS, s));
        }
    }
    
    public List<gx> dW() {
        this.ar("getTypes");
        return this.AX;
    }
    
    public LatLng dX() {
        this.ar("getLatLng");
        return this.AO;
    }
    
    public float dY() {
        this.ar("getLevelNumber");
        return this.AP;
    }
    
    public LatLngBounds dZ() {
        this.ar("getViewport");
        return this.AQ;
    }
    
    public int describeContents() {
        final he creator = hd.CREATOR;
        return 0;
    }
    
    public Uri ea() {
        this.ar("getWebsiteUri");
        return this.AS;
    }
    
    public boolean eb() {
        this.ar("isPermanentlyClosed");
        return this.AT;
    }
    
    public int ec() {
        this.ar("getPriceLevel");
        return this.AV;
    }
    
    public long ed() {
        return this.AW;
    }
    
    public Bundle ee() {
        return this.AM;
    }
    
    public hf ef() {
        return this.AN;
    }
    
    public String eg() {
        return this.AR;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof hd)) {
                return false;
            }
            final hd hd = (hd)o;
            if (!this.uS.equals(hd.uS) || !ee.equal(this.Ba, hd.Ba) || this.AW != hd.AW) {
                return false;
            }
        }
        return true;
    }
    
    public String getId() {
        this.ar("getId");
        return this.uS;
    }
    
    public float getRating() {
        this.ar("getRating");
        return this.AU;
    }
    
    @Override
    public int hashCode() {
        return ee.hashCode(this.uS, this.Ba, this.AW);
    }
    
    @Override
    public String toString() {
        return ee.e(this).a("id", this.uS).a("localization", this.AN).a("locale", this.Ba).a("latlng", this.AO).a("levelNumber", this.AP).a("viewport", this.AQ).a("timeZone", this.AR).a("websiteUri", this.AS).a("isPermanentlyClosed", this.AT).a("priceLevel", this.AV).a("timestampSecs", this.AW).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final he creator = hd.CREATOR;
        he.a(this, parcel, n);
    }
    
    public static final class a implements SafeParcelable
    {
        public static final ha CREATOR;
        private final String Bc;
        private final String Bd;
        private final int Be;
        final int kg;
        private final String mTag;
        
        static {
            CREATOR = new ha();
        }
        
        a(final int kg, final String bc, final String mTag, final String bd, final int be) {
            this.kg = kg;
            this.Bc = bc;
            this.mTag = mTag;
            this.Bd = bd;
            this.Be = be;
        }
        
        public int describeContents() {
            final ha creator = hd.a.CREATOR;
            return 0;
        }
        
        public String eh() {
            return this.Bc;
        }
        
        public String ei() {
            return this.Bd;
        }
        
        public int ej() {
            return this.Be;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (this != o) {
                if (!(o instanceof hd.a)) {
                    return false;
                }
                final hd.a a = (hd.a)o;
                if (!this.Bc.equals(a.Bc) || !ee.equal(this.mTag, a.mTag)) {
                    return false;
                }
            }
            return true;
        }
        
        public String getTag() {
            return this.mTag;
        }
        
        @Override
        public int hashCode() {
            return ee.hashCode(this.Bc, this.mTag, this.Bd, this.Be);
        }
        
        @Override
        public String toString() {
            return ee.e(this).a("placeId", this.Bc).a("tag", this.mTag).a("callingAppPackageName", this.Bd).a("callingAppVersionCode", this.Be).toString();
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final ha creator = hd.a.CREATOR;
            ha.a(this, parcel, n);
        }
        
        public static class a
        {
            private final String Bc;
            private String Bd;
            private int Be;
            private final String mTag;
            
            public a(final String bc, final String mTag) {
                this.Bc = bc;
                this.mTag = mTag;
            }
            
            public a as(final String bd) {
                this.Bd = bd;
                return this;
            }
            
            public a bd(final int be) {
                this.Be = be;
                return this;
            }
            
            public hd.a ek() {
                return new hd.a(0, this.Bc, this.mTag, this.Bd, this.Be);
            }
        }
    }
}
