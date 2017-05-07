// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import android.util.Pair;
import java.util.HashMap;
import android.text.TextUtils;
import com.google.android.gms.internal.zzof;
import android.database.sqlite.SQLiteException;
import android.content.Context;
import com.google.android.gms.analytics.CampaignTrackingService;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.android.gms.analytics.AnalyticsService;
import com.google.android.gms.analytics.AnalyticsReceiver;
import java.util.Iterator;
import com.google.android.gms.internal.zzob;
import java.util.Map;
import com.google.android.gms.internal.zzok;
import com.google.android.gms.internal.zzja;
import com.google.android.gms.internal.zzod;
import com.google.android.gms.internal.zzjb;
import com.google.android.gms.analytics.zza;
import com.google.android.gms.internal.zzol;
import com.google.android.gms.common.internal.zzx;

class zzl$1 extends zzt
{
    final /* synthetic */ zzl zzMx;
    
    zzl$1(final zzl zzMx, final zzf zzf) {
        this.zzMx = zzMx;
        super(zzf);
    }
    
    @Override
    public void run() {
        this.zzMx.zziO();
    }
}
