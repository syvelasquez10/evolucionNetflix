// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.regex.Matcher;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

class DataLayer$2 implements DataLayer$zzc$zza
{
    final /* synthetic */ DataLayer zzaPH;
    
    DataLayer$2(final DataLayer zzaPH) {
        this.zzaPH = zzaPH;
    }
    
    @Override
    public void zzo(final List<DataLayer$zza> list) {
        for (final DataLayer$zza dataLayer$zza : list) {
            this.zzaPH.zzJ(this.zzaPH.zzj(dataLayer$zza.zztP, dataLayer$zza.zzID));
        }
        this.zzaPH.zzaPG.countDown();
    }
}
