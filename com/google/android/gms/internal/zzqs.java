// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzx;
import java.util.List;
import java.io.UnsupportedEncodingException;
import com.google.android.gms.tagmanager.zzbg;
import java.net.URLEncoder;

public class zzqs
{
    private String zzaPw;
    
    public zzqs() {
        this.zzaPw = "https://www.google-analytics.com";
    }
    
    private String zzff(final String s) {
        try {
            return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20");
        }
        catch (UnsupportedEncodingException ex) {
            zzbg.e("Cannot encode the string: " + s);
            return "";
        }
    }
    
    public void zzfj(final String zzaPw) {
        this.zzaPw = zzaPw;
        zzbg.zzaD("The Ctfe server endpoint was changed to: " + zzaPw);
    }
    
    public String zzt(final List<zzqi> list) {
        return this.zzaPw + "/gtm/android?" + this.zzu(list);
    }
    
    String zzu(final List<zzqi> list) {
        boolean b = true;
        if (list.size() > 1) {
            b = false;
        }
        zzx.zzZ(b);
        if (list.isEmpty()) {
            return "";
        }
        final zzqi zzqi = list.get(0);
        String trim;
        if (!zzqi.zzBt().trim().equals("")) {
            trim = zzqi.zzBt().trim();
        }
        else {
            trim = "-1";
        }
        final StringBuilder sb = new StringBuilder();
        if (zzqi.zzBq() != null) {
            sb.append(zzqi.zzBq());
        }
        else {
            sb.append("id");
        }
        sb.append("=").append(this.zzff(zzqi.getContainerId())).append("&").append("pv").append("=").append(this.zzff(trim));
        if (zzqi.zzBs()) {
            sb.append("&gtm_debug=x");
        }
        return sb.toString();
    }
}
