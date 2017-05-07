// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.concurrent.LinkedBlockingQueue;
import android.content.Context;
import com.google.android.gms.internal.hb;
import java.util.List;
import android.text.TextUtils;
import java.util.Map;

class s$1 implements Runnable
{
    final /* synthetic */ Map yZ;
    final /* synthetic */ s za;
    
    s$1(final s za, final Map yz) {
        this.za = za;
        this.yZ = yz;
    }
    
    @Override
    public void run() {
        this.za.x(this.yZ);
        if (TextUtils.isEmpty((CharSequence)this.yZ.get("&cid"))) {
            this.yZ.put("&cid", h.dR().getValue("&cid"));
        }
        if (GoogleAnalytics.getInstance(this.za.mContext).getAppOptOut() || this.za.w(this.yZ)) {
            return;
        }
        if (!TextUtils.isEmpty((CharSequence)this.za.yW)) {
            t.eq().B(true);
            this.yZ.putAll(new HitBuilders$HitBuilder<HitBuilders$HitBuilder>().setCampaignParamsFromUrl(this.za.yW).build());
            t.eq().B(false);
            this.za.yW = null;
        }
        this.za.y(this.yZ);
        this.za.yY.b(x.z(this.yZ), Long.valueOf(this.yZ.get("&ht")), this.za.v(this.yZ), this.za.yV);
    }
}
