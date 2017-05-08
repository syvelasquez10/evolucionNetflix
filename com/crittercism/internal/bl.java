// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.util.HashMap;
import java.util.Map;

public final class bl
{
    private static final Map e;
    public String a;
    public String b;
    public String c;
    public String d;
    
    static {
        (e = new HashMap()).put("00555300", "crittercism.com");
        bl.e.put("00555304", "crit-ci.com");
        bl.e.put("00555305", "crit-staging.com");
        bl.e.put("00444503", "eu.crittercism.com");
    }
    
    public bl(final String s) {
        if (s == null) {
            throw new bl$a("Given null appId");
        }
        if (!s.matches("[0-9a-fA-F]+")) {
            throw new bl$a("Invalid appId: '" + s + "'. AppId must be hexadecimal characters");
        }
        if (s.length() != 24 && s.length() != 40) {
            throw new bl$a("Invalid appId: '" + s + "'. AppId must be either 24 or 40 characters");
        }
        Object substring = null;
        if (s.length() == 24) {
            substring = "00555300";
        }
        else if (s.length() == 40) {
            substring = s.substring(s.length() - 8);
        }
        final String s2 = bl.e.get(substring);
        if (s2 == null) {
            throw new bl$a("Invalid appId: '" + s + "'. Invalid app locator code");
        }
        this.a = System.getProperty("com.crittercism.apmUrl", "https://apm." + s2);
        this.b = System.getProperty("com.crittercism.apiUrl", "https://api." + s2);
        this.c = System.getProperty("com.crittercism.txnUrl", "https://txn.ingest." + s2);
        this.d = System.getProperty("com.crittercism.appLoadUrl", "https://appload.ingest." + s2);
    }
}
