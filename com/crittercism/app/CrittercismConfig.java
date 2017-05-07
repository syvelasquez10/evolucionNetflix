// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.app;

import java.util.Collection;
import org.json.JSONObject;
import java.util.LinkedList;
import java.util.List;

public class CrittercismConfig
{
    public static final String API_VERSION = "3.1.4";
    private String a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private String g;
    private String h;
    private String i;
    private List j;
    
    public CrittercismConfig() {
        this.a = null;
        this.b = false;
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = false;
        this.g = "com.crittercism/dumps";
        this.h = "Developer Reply";
        this.i = null;
        this.j = new LinkedList();
    }
    
    public CrittercismConfig(final CrittercismConfig crittercismConfig) {
        this.a = null;
        this.b = false;
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = false;
        this.g = "com.crittercism/dumps";
        this.h = "Developer Reply";
        this.i = null;
        this.j = new LinkedList();
        this.a = crittercismConfig.a;
        this.b = crittercismConfig.b;
        this.c = crittercismConfig.c;
        this.d = crittercismConfig.d;
        this.e = crittercismConfig.e;
        this.f = crittercismConfig.f;
        this.g = crittercismConfig.g;
        this.h = crittercismConfig.h;
        this.setApmBlackListURLPatterns(crittercismConfig.j);
        this.i = crittercismConfig.i;
    }
    
    public CrittercismConfig(final JSONObject jsonObject) {
        this.a = null;
        this.b = false;
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = false;
        this.g = "com.crittercism/dumps";
        this.h = "Developer Reply";
        this.i = null;
        this.j = new LinkedList();
        this.a = a(jsonObject, "customVersionName", this.a);
        this.c = a(jsonObject, "includeVersionCode", this.c);
        this.d = a(jsonObject, "installNdk", this.d);
        this.b = a(jsonObject, "delaySendingAppLoad", this.b);
        this.e = a(jsonObject, "shouldCollectLogcat", this.e);
        this.g = a(jsonObject, "nativeDumpPath", this.g);
        this.h = a(jsonObject, "notificationTitle", this.h);
        this.f = a(jsonObject, "installApm", this.f);
    }
    
    private static int a(final String s) {
        int hashCode = 0;
        if (s != null) {
            hashCode = s.hashCode();
        }
        return hashCode;
    }
    
    private static String a(final JSONObject jsonObject, final String s, final String s2) {
        String string = s2;
        if (!jsonObject.has(s)) {
            return string;
        }
        try {
            string = jsonObject.getString(s);
            return string;
        }
        catch (Exception ex) {
            return s2;
        }
    }
    
    protected static boolean a(final String s, final String s2) {
        if (s == null) {
            return s2 == null;
        }
        return s.equals(s2);
    }
    
    private static boolean a(final JSONObject jsonObject, final String s, final boolean b) {
        boolean boolean1 = b;
        if (!jsonObject.has(s)) {
            return boolean1;
        }
        try {
            boolean1 = jsonObject.getBoolean(s);
            return boolean1;
        }
        catch (Exception ex) {
            return b;
        }
    }
    
    public final boolean delaySendingAppLoad() {
        return this.b;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof CrittercismConfig) {
            final CrittercismConfig crittercismConfig = (CrittercismConfig)o;
            if (this.b == crittercismConfig.b && this.e == crittercismConfig.e && this.isNdkCrashReportingEnabled() == crittercismConfig.isNdkCrashReportingEnabled() && this.isVersionCodeToBeIncludedInVersionString() == crittercismConfig.isVersionCodeToBeIncludedInVersionString() && a(this.a, crittercismConfig.a) && a(this.h, crittercismConfig.h) && a(this.g, crittercismConfig.g) && this.j.equals(crittercismConfig.j) && a(this.i, crittercismConfig.i)) {
                return true;
            }
        }
        return false;
    }
    
    public List getApmBlackListURLPatterns() {
        return new LinkedList(this.j);
    }
    
    public final String getCustomVersionName() {
        return this.a;
    }
    
    public final String getNativeDumpPath() {
        return this.g;
    }
    
    public final String getNotificationTitle() {
        return this.h;
    }
    
    public final String getRateMyAppTestTarget() {
        return this.i;
    }
    
    @Override
    public int hashCode() {
        int n = 1;
        final int a = a(this.a);
        final int a2 = a(this.h);
        final int a3 = a(this.g);
        final int a4 = a(this.i);
        final int hashCode = this.j.hashCode();
        int n2;
        if (this.b) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        int n3;
        if (this.e) {
            n3 = 1;
        }
        else {
            n3 = 0;
        }
        int n4;
        if (this.isNdkCrashReportingEnabled()) {
            n4 = 1;
        }
        else {
            n4 = 0;
        }
        if (!this.isVersionCodeToBeIncludedInVersionString()) {
            n = 0;
        }
        return Integer.valueOf(((n4 + (n3 + (n2 + 0 << 1) << 1) << 1) + 0 << 1) + n).hashCode() + (hashCode + ((((a + 0) * 31 + a2) * 31 + a3) * 31 + a4) * 31) * 31;
    }
    
    public final boolean isLogcatReportingEnabled() {
        return this.e;
    }
    
    public final boolean isNdkCrashReportingEnabled() {
        return this.d;
    }
    
    public final boolean isVersionCodeToBeIncludedInVersionString() {
        return this.c;
    }
    
    public void setApmBlackListURLPatterns(final List list) {
        this.j.clear();
        if (list != null) {
            this.j.addAll(list);
        }
    }
    
    public final void setCustomVersionName(final String a) {
        this.a = a;
    }
    
    public final void setDelaySendingAppLoad(final boolean b) {
        this.b = b;
    }
    
    public final void setLogcatReportingEnabled(final boolean e) {
        this.e = e;
    }
    
    public final void setNativeDumpPath(final String g) {
        this.g = g;
    }
    
    public final void setNdkCrashReportingEnabled(final boolean d) {
        this.d = d;
    }
    
    public final void setNotificationTitle(final String h) {
        this.h = h;
    }
    
    public final void setRateMyAppTestTarget(final String i) {
        this.i = i;
    }
    
    public final void setVersionCodeToBeIncludedInVersionString(final boolean c) {
        this.c = c;
    }
}
