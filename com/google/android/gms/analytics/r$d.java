// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.util.Iterator;
import com.google.android.gms.internal.hb;
import java.util.List;
import java.util.Map;

class r$d
{
    private final Map<String, String> yP;
    private final long yQ;
    private final String yR;
    private final List<hb> yS;
    
    public r$d(final Map<String, String> yp, final long yq, final String yr, final List<hb> ys) {
        this.yP = yp;
        this.yQ = yq;
        this.yR = yr;
        this.yS = ys;
    }
    
    public Map<String, String> en() {
        return this.yP;
    }
    
    public long eo() {
        return this.yQ;
    }
    
    public List<hb> ep() {
        return this.yS;
    }
    
    public String getPath() {
        return this.yR;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("PATH: ");
        sb.append(this.yR);
        if (this.yP != null) {
            sb.append("  PARAMS: ");
            for (final Map.Entry<String, String> entry : this.yP.entrySet()) {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
                sb.append(",  ");
            }
        }
        return sb.toString();
    }
}
