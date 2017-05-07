// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import android.os.Parcel;
import com.google.android.gms.internal.fo;
import org.json.JSONException;
import org.json.JSONObject;
import android.net.Uri;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class WebImage implements SafeParcelable
{
    public static final Parcelable$Creator<WebImage> CREATOR;
    private final Uri Cu;
    private final int kr;
    private final int ks;
    private final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    WebImage(final int xh, final Uri cu, final int kr, final int ks) {
        this.xH = xh;
        this.Cu = cu;
        this.kr = kr;
        this.ks = ks;
    }
    
    public WebImage(final Uri uri) throws IllegalArgumentException {
        this(uri, 0, 0);
    }
    
    public WebImage(final Uri uri, final int n, final int n2) throws IllegalArgumentException {
        this(1, uri, n, n2);
        if (uri == null) {
            throw new IllegalArgumentException("url cannot be null");
        }
        if (n < 0 || n2 < 0) {
            throw new IllegalArgumentException("width and height must not be negative");
        }
    }
    
    public WebImage(final JSONObject jsonObject) throws IllegalArgumentException {
        this(d(jsonObject), jsonObject.optInt("width", 0), jsonObject.optInt("height", 0));
    }
    
    private static Uri d(final JSONObject jsonObject) {
        Uri parse = null;
        if (!jsonObject.has("url")) {
            return parse;
        }
        try {
            parse = Uri.parse(jsonObject.getString("url"));
            return parse;
        }
        catch (JSONException ex) {
            return null;
        }
    }
    
    public JSONObject dB() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("url", (Object)this.Cu.toString());
            jsonObject.put("width", this.kr);
            jsonObject.put("height", this.ks);
            return jsonObject;
        }
        catch (JSONException ex) {
            return jsonObject;
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || !(o instanceof WebImage)) {
                return false;
            }
            final WebImage webImage = (WebImage)o;
            if (!fo.equal(this.Cu, webImage.Cu) || this.kr != webImage.kr || this.ks != webImage.ks) {
                return false;
            }
        }
        return true;
    }
    
    public int getHeight() {
        return this.ks;
    }
    
    public Uri getUrl() {
        return this.Cu;
    }
    
    int getVersionCode() {
        return this.xH;
    }
    
    public int getWidth() {
        return this.kr;
    }
    
    @Override
    public int hashCode() {
        return fo.hashCode(this.Cu, this.kr, this.ks);
    }
    
    @Override
    public String toString() {
        return String.format("Image %dx%d %s", this.kr, this.ks, this.Cu.toString());
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
}
