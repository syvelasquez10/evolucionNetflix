// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.io.UnsupportedEncodingException;
import java.util.zip.CRC32;
import android.os.Bundle;
import android.content.ComponentName;
import com.google.android.gms.appindexing.AppIndexApi$AppIndexingLink;
import java.util.List;
import android.net.Uri;
import android.content.Intent;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class hs implements SafeParcelable
{
    public static final ht CREATOR;
    final int BR;
    final hg CD;
    final long CE;
    final int CF;
    final he CG;
    public final String oT;
    
    static {
        CREATOR = new ht();
    }
    
    hs(final int br, final hg cd, final long ce, final int cf, final String ot, final he cg) {
        this.BR = br;
        this.CD = cd;
        this.CE = ce;
        this.CF = cf;
        this.oT = ot;
        this.CG = cg;
    }
    
    public hs(final hg hg, final long n, final int n2) {
        this(1, hg, n, n2, null, null);
    }
    
    public hs(final String s, final Intent intent, final String s2, final Uri uri, final String s3, final List<AppIndexApi$AppIndexingLink> list) {
        this(1, a(s, intent), System.currentTimeMillis(), 0, null, a(intent, s2, uri, s3, list).fk());
    }
    
    public static he$a a(final Intent intent, String s, final Uri uri, final String s2, final List<AppIndexApi$AppIndexingLink> list) {
        final he$a he$a = new he$a();
        he$a.a(av(s));
        if (uri != null) {
            he$a.a(f(uri));
        }
        if (list != null) {
            he$a.a(b(list));
        }
        s = intent.getAction();
        if (s != null) {
            he$a.a(j("intent_action", s));
        }
        s = intent.getDataString();
        if (s != null) {
            he$a.a(j("intent_data", s));
        }
        final ComponentName component = intent.getComponent();
        if (component != null) {
            he$a.a(j("intent_activity", component.getClassName()));
        }
        final Bundle extras = intent.getExtras();
        if (extras != null) {
            final String string = extras.getString("intent_extra_data_key");
            if (string != null) {
                he$a.a(j("intent_extra_data", string));
            }
        }
        return he$a.ar(s2).D(true);
    }
    
    public static hg a(final String s, final Intent intent) {
        return i(s, g(intent));
    }
    
    private static hi av(final String s) {
        return new hi(s, new hq$a("title").P(1).F(true).au("name").fn(), "text1");
    }
    
    private static hi b(final List<AppIndexApi$AppIndexingLink> list) {
        final lk$a lk$a = new lk$a();
        final lk$a$a[] adt = new lk$a$a[list.size()];
        for (int i = 0; i < adt.length; ++i) {
            adt[i] = new lk$a$a();
            final AppIndexApi$AppIndexingLink appIndexApi$AppIndexingLink = list.get(i);
            adt[i].adv = appIndexApi$AppIndexingLink.appIndexingUrl.toString();
            adt[i].adw = appIndexApi$AppIndexingLink.webUrl.toString();
            adt[i].viewId = appIndexApi$AppIndexingLink.viewId;
        }
        lk$a.adt = adt;
        return new hi(pm.f(lk$a), new hq$a("outlinks").E(true).au(".private:outLinks").at("blob").fn());
    }
    
    private static hi f(final Uri uri) {
        return new hi(uri.toString(), new hq$a("web_url").P(4).E(true).au("url").fn());
    }
    
    private static String g(final Intent intent) {
        final String uri = intent.toUri(1);
        final CRC32 crc32 = new CRC32();
        try {
            crc32.update(uri.getBytes("UTF-8"));
            return Long.toHexString(crc32.getValue());
        }
        catch (UnsupportedEncodingException ex) {
            throw new IllegalStateException(ex);
        }
    }
    
    private static hg i(final String s, final String s2) {
        return new hg(s, "", s2);
    }
    
    private static hi j(final String s, final String s2) {
        return new hi(s2, new hq$a(s).E(true).fn(), s);
    }
    
    public int describeContents() {
        final ht creator = hs.CREATOR;
        return 0;
    }
    
    @Override
    public String toString() {
        return String.format("UsageInfo[documentId=%s, timestamp=%d, usageType=%d]", this.CD, this.CE, this.CF);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final ht creator = hs.CREATOR;
        ht.a(this, parcel, n);
    }
}
