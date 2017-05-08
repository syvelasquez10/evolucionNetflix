// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.app;

import com.crittercism.internal.dw;
import java.util.Collection;
import android.os.Build$VERSION;
import org.json.JSONObject;
import java.util.LinkedList;
import java.util.List;

public class CrittercismConfig
{
    public String a;
    private String b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;
    private String h;
    private String i;
    private List j;
    private List k;
    
    public CrittercismConfig() {
        this.b = null;
        this.c = false;
        this.d = false;
        this.e = true;
        this.f = false;
        this.g = b();
        this.a = "com.crittercism/dumps";
        this.h = "Developer Reply";
        this.i = null;
        this.j = new LinkedList();
        this.k = new LinkedList();
    }
    
    public CrittercismConfig(final CrittercismConfig crittercismConfig) {
        this.b = null;
        this.c = false;
        this.d = false;
        this.e = true;
        this.f = false;
        this.g = b();
        this.a = "com.crittercism/dumps";
        this.h = "Developer Reply";
        this.i = null;
        this.j = new LinkedList();
        this.k = new LinkedList();
        this.b = crittercismConfig.b;
        this.c = crittercismConfig.c;
        this.d = crittercismConfig.d;
        this.e = crittercismConfig.e;
        this.f = crittercismConfig.f;
        this.g = crittercismConfig.g;
        this.a = crittercismConfig.a;
        this.h = crittercismConfig.h;
        this.setURLBlacklistPatterns(crittercismConfig.j);
        this.setPreserveQueryStringPatterns(crittercismConfig.k);
        this.i = crittercismConfig.i;
    }
    
    public CrittercismConfig(final JSONObject jsonObject) {
        this.b = null;
        this.c = false;
        this.d = false;
        this.e = true;
        this.f = false;
        this.g = b();
        this.a = "com.crittercism/dumps";
        this.h = "Developer Reply";
        this.i = null;
        this.j = new LinkedList();
        this.k = new LinkedList();
        this.b = a(jsonObject, "customVersionName", this.b);
        this.d = a(jsonObject, "includeVersionCode", this.d);
        this.e = a(jsonObject, "installNdk", this.e);
        this.c = a(jsonObject, "delaySendingAppLoad", this.c);
        this.f = a(jsonObject, "shouldCollectLogcat", this.f);
        this.a = a(jsonObject, "nativeDumpPath", this.a);
        this.h = a(jsonObject, "notificationTitle", this.h);
        this.g = a(jsonObject, "installApm", this.g);
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
    
    public static boolean a(final String s, final String s2) {
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
    
    private static final boolean b() {
        return Build$VERSION.SDK_INT >= 10 && Build$VERSION.SDK_INT <= 23;
    }
    
    public List a() {
        return this.getURLBlacklistPatterns();
    }
    
    public final boolean delaySendingAppLoad() {
        return this.c;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof CrittercismConfig) {
            final CrittercismConfig crittercismConfig = (CrittercismConfig)o;
            if (this.c == crittercismConfig.c && this.f == crittercismConfig.f && this.isNdkCrashReportingEnabled() == crittercismConfig.isNdkCrashReportingEnabled() && this.isOptmzEnabled() == crittercismConfig.isOptmzEnabled() && this.isVersionCodeToBeIncludedInVersionString() == crittercismConfig.isVersionCodeToBeIncludedInVersionString() && a(this.b, crittercismConfig.b) && a(this.h, crittercismConfig.h) && a(this.a, crittercismConfig.a) && this.j.equals(crittercismConfig.j) && this.k.equals(crittercismConfig.k) && a(this.i, crittercismConfig.i)) {
                return true;
            }
        }
        return false;
    }
    
    public final String getCustomVersionName() {
        return this.b;
    }
    
    public List getPreserveQueryStringPatterns() {
        return new LinkedList(this.k);
    }
    
    public final String getRateMyAppTestTarget() {
        return this.i;
    }
    
    public List getURLBlacklistPatterns() {
        return new LinkedList(this.j);
    }
    
    @Override
    public int hashCode() {
        int n = 1;
        final int a = a(this.b);
        final int a2 = a(this.h);
        final int a3 = a(this.a);
        final int a4 = a(this.i);
        final int hashCode = this.j.hashCode();
        final int hashCode2 = this.k.hashCode();
        int n2;
        if (this.c) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        int n3;
        if (this.f) {
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
        int n5;
        if (this.isOptmzEnabled()) {
            n5 = 1;
        }
        else {
            n5 = 0;
        }
        if (!this.isVersionCodeToBeIncludedInVersionString()) {
            n = 0;
        }
        return Integer.valueOf((n5 + (n4 + (n3 + (n2 + 0 << 1) << 1) << 1) << 1) + n).hashCode() + (hashCode2 + (((((a + 0) * 31 + a2) * 31 + a3) * 31 + a4) * 31 + hashCode) * 31) * 31;
    }
    
    public final boolean isLogcatReportingEnabled() {
        return this.f;
    }
    
    public final boolean isNdkCrashReportingEnabled() {
        return this.e;
    }
    
    @Deprecated
    public final boolean isOptmzEnabled() {
        return this.g;
    }
    
    public final boolean isServiceMonitoringEnabled() {
        return this.isOptmzEnabled();
    }
    
    public final boolean isVersionCodeToBeIncludedInVersionString() {
        return this.d;
    }
    
    public final void setCustomVersionName(final String b) {
        this.b = b;
    }
    
    public final void setDelaySendingAppLoad(final boolean c) {
        this.c = c;
    }
    
    public final void setLogcatReportingEnabled(final boolean f) {
        this.f = f;
    }
    
    public final void setNdkCrashReportingEnabled(final boolean e) {
        this.e = e;
    }
    
    @Deprecated
    public final void setOptmzEnabled(final boolean g) {
        if (!b() && g) {
            dw.c("OPTMZ is currently only allowed for api levels 10 to 23.  APM will not be installed");
            return;
        }
        this.g = g;
    }
    
    public void setPreserveQueryStringPatterns(final List list) {
        this.k.clear();
        if (list != null) {
            this.k.addAll(list);
        }
    }
    
    public final void setRateMyAppTestTarget(final String i) {
        this.i = i;
    }
    
    public final void setServiceMonitoringEnabled(final boolean optmzEnabled) {
        this.setOptmzEnabled(optmzEnabled);
    }
    
    public void setURLBlacklistPatterns(final List list) {
        this.j.clear();
        if (list != null) {
            this.j.addAll(list);
        }
    }
    
    public final void setVersionCodeToBeIncludedInVersionString(final boolean d) {
        this.d = d;
    }
}
