// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.regex.Matcher;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

class DataLayer$2 implements DataLayer$c$a
{
    final /* synthetic */ DataLayer aoD;
    
    DataLayer$2(final DataLayer aoD) {
        this.aoD = aoD;
    }
    
    @Override
    public void g(final List<DataLayer$a> list) {
        for (final DataLayer$a dataLayer$a : list) {
            this.aoD.F(this.aoD.c(dataLayer$a.JH, dataLayer$a.wq));
        }
        this.aoD.aoC.countDown();
    }
}
