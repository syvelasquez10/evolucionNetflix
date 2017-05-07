// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api;

import android.os.Parcel;
import java.io.UnsupportedEncodingException;
import android.util.Log;
import org.json.JSONObject;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class GoogleAuthApiRequest implements SafeParcelable
{
    public static final GoogleAuthApiRequestCreator CREATOR;
    public static final String DEFAULT_SCOPE_PREFIX = "oauth2:";
    public static final int HTTP_METHOD_DELETE = 3;
    public static final int HTTP_METHOD_GET = 0;
    public static final int HTTP_METHOD_HEAD = 4;
    public static final int HTTP_METHOD_OPTIONS = 5;
    public static final int HTTP_METHOD_PATCH = 7;
    public static final int HTTP_METHOD_POST = 1;
    public static final int HTTP_METHOD_PUT = 2;
    public static final int HTTP_METHOD_TRACE = 6;
    public static final int VERSION_CODE = 1;
    byte[] DA;
    long DB;
    String Dt;
    Bundle Du;
    String Dv;
    List<String> Dw;
    String Dx;
    int Dy;
    Bundle Dz;
    String name;
    String version;
    final int versionCode;
    String yR;
    
    static {
        CREATOR = new GoogleAuthApiRequestCreator();
    }
    
    GoogleAuthApiRequest(final int versionCode, final String name, final String version, final String dt, final String yr, final Bundle du, final String dv, final List<String> dw, final String dx, final int dy, final Bundle dz, final byte[] da, final long db) {
        this.versionCode = versionCode;
        this.name = name;
        this.version = version;
        this.Dt = dt;
        this.yR = yr;
        this.Du = du;
        this.Dv = dv;
        this.Dw = dw;
        this.Dx = dx;
        this.Dy = dy;
        this.Dz = dz;
        this.DA = da;
        this.DB = db;
    }
    
    public GoogleAuthApiRequest(final String name, final String s, final String dt) {
        this.versionCode = 1;
        this.name = name;
        this.ay(s);
        this.Dt = dt;
        this.Du = new Bundle();
        this.Dw = new ArrayList<String>();
        this.Dx = "oauth2:";
        this.Dz = new Bundle();
        this.DA = new byte[0];
    }
    
    public GoogleAuthApiRequest(final String name, final String s, final String path, final int n) {
        this.versionCode = 1;
        this.name = name;
        this.ay(s);
        this.setPath(path);
        this.T(n);
        this.Du = new Bundle();
        this.Dw = new ArrayList<String>();
        this.Dx = "oauth2:";
        this.Dz = new Bundle();
        this.DA = new byte[0];
    }
    
    private void T(final int dy) {
        if (dy < 0 || dy > 7) {
            throw new IllegalArgumentException("Invalid HTTP method.");
        }
        this.Dy = dy;
    }
    
    private void ay(final String s) {
        String string = s;
        if (s.charAt(0) >= '0') {
            string = s;
            if (s.charAt(0) <= '9') {
                string = "v" + s;
            }
        }
        this.version = string;
    }
    
    private void setPath(String substring) {
        String substring2 = substring;
        if (substring.charAt(0) == '/') {
            substring2 = substring.substring(1);
        }
        substring = substring2;
        if (substring2.charAt(substring2.length() - 1) == '/') {
            substring = substring2.substring(0, substring2.length() - 1);
        }
        this.yR = substring;
    }
    
    public void addParameter(final String s, final String s2) {
        if (!this.Du.containsKey(s)) {
            final ArrayList<String> list = new ArrayList<String>();
            list.add(s2);
            this.Du.putStringArrayList(s, (ArrayList)list);
            return;
        }
        this.Du.getStringArrayList(s).add(s2);
    }
    
    public void addScope(final String s) {
        this.Dw.add(s);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getAccountName() {
        return this.Dv;
    }
    
    public String getApiId() {
        return this.Dt;
    }
    
    public String getFullScope() {
        final String scope = this.getScope();
        if (scope == null) {
            return null;
        }
        return this.Dx + scope;
    }
    
    public String getHeader(final String s) {
        return this.Dz.getString(s);
    }
    
    public Bundle getHeaders() {
        return this.Dz;
    }
    
    public Map<String, String> getHeadersAsMap() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        for (final String s : this.Dz.keySet()) {
            hashMap.put(s, this.Dz.getString(s));
        }
        return hashMap;
    }
    
    public byte[] getHttpBody() {
        return this.DA;
    }
    
    public JSONObject getHttpBodyAsJson() {
        try {
            return new JSONObject(new String(this.DA, "UTF-8"));
        }
        catch (UnsupportedEncodingException ex) {
            Log.e("GoogleAuthApiRequest", "Unsupported encoding error.");
            return null;
        }
    }
    
    public int getHttpMethod() {
        return this.Dy;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Bundle getParameters() {
        return this.Du;
    }
    
    public Map<String, List<String>> getParametersAsMap() {
        final HashMap<String, ArrayList> hashMap = (HashMap<String, ArrayList>)new HashMap<String, List<String>>();
        for (final String s : this.Du.keySet()) {
            hashMap.put(s, (List<String>)this.Du.getStringArrayList(s));
        }
        return (Map<String, List<String>>)hashMap;
    }
    
    public String getPath() {
        return this.yR;
    }
    
    public String getScope() {
        if (this.Dw.size() == 0) {
            return null;
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.Dw.size(); ++i) {
            sb.append(this.Dw.get(i));
            if (i != this.Dw.size() - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
    
    public long getTimeout() {
        return this.DB;
    }
    
    public String getVersion() {
        return this.version;
    }
    
    public void putHeader(final String s, final String s2) {
        this.Dz.putString(s, s2);
    }
    
    public void setAccountName(final String dv) {
        this.Dv = dv;
    }
    
    public void setHttpBody(final String s) {
        try {
            this.DA = s.getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            Log.e("GoogleAuthApiRequest", "Unsupported encoding error.");
        }
    }
    
    public void setTimeout(final long db) {
        this.DB = db;
    }
    
    @Override
    public String toString() {
        return "{ API: " + this.name + "/" + this.version + ", Scope: " + this.getFullScope() + ", Account: " + this.getAccountName() + " }";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        GoogleAuthApiRequestCreator.a(this, parcel, n);
    }
}
