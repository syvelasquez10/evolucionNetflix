// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

@ez
class fv
{
    private int tc;
    private final List<String> uJ;
    private final List<String> uK;
    private final String uL;
    private final String uM;
    private final String uN;
    private final String uO;
    private final boolean uP;
    private final int uQ;
    private String uR;
    
    public fv(final int tc, final Map<String, String> map) {
        this.uR = map.get("url");
        this.uM = map.get("base_uri");
        this.uN = map.get("post_parameters");
        this.uP = parseBoolean(map.get("drt_include"));
        this.uL = map.get("activation_overlay_url");
        this.uK = this.J(map.get("check_packages"));
        this.uQ = this.parseInt(map.get("request_id"));
        this.uO = map.get("type");
        this.uJ = this.J(map.get("errors"));
        this.tc = tc;
    }
    
    private List<String> J(final String s) {
        if (s == null) {
            return null;
        }
        return Arrays.asList(s.split(","));
    }
    
    private static boolean parseBoolean(final String s) {
        return s != null && (s.equals("1") || s.equals("true"));
    }
    
    private int parseInt(final String s) {
        if (s == null) {
            return 0;
        }
        return Integer.parseInt(s);
    }
    
    public List<String> cM() {
        return this.uJ;
    }
    
    public String cN() {
        return this.uN;
    }
    
    public boolean cO() {
        return this.uP;
    }
    
    public int getErrorCode() {
        return this.tc;
    }
    
    public String getType() {
        return this.uO;
    }
    
    public String getUrl() {
        return this.uR;
    }
    
    public void setUrl(final String ur) {
        this.uR = ur;
    }
}
