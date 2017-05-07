// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import android.os.Parcel;
import com.google.android.gms.internal.ee;
import com.google.android.gms.internal.dh;
import org.json.JSONException;
import org.json.JSONObject;
import android.net.Uri;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class WebImage implements SafeParcelable
{
    public static final Parcelable$Creator<WebImage> CREATOR;
    private final int kg;
    private final Uri oA;
    private final int v;
    private final int w;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    WebImage(final int kg, final Uri oa, final int w, final int v) {
        this.kg = kg;
        this.oA = oa;
        this.w = w;
        this.v = v;
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
        this(f(jsonObject), jsonObject.optInt("width", 0), jsonObject.optInt("height", 0));
    }
    
    private static Uri f(final JSONObject jsonObject) {
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
    
    public JSONObject aP() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("url", (Object)this.oA.toString());
            jsonObject.put("width", this.w);
            jsonObject.put("height", this.v);
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
            if (!dh.a(this.oA, webImage.oA) || this.w != webImage.w || this.v != webImage.v) {
                return false;
            }
        }
        return true;
    }
    
    public int getHeight() {
        return this.v;
    }
    
    public Uri getUrl() {
        return this.oA;
    }
    
    int getVersionCode() {
        return this.kg;
    }
    
    public int getWidth() {
        return this.w;
    }
    
    @Override
    public int hashCode() {
        return ee.hashCode(this.oA, this.w, this.v);
    }
    
    @Override
    public String toString() {
        return String.format("Image %dx%d %s", this.w, this.v, this.oA.toString());
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
}
