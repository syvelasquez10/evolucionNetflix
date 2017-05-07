// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.UUID;
import android.os.Bundle;
import java.util.HashMap;
import java.util.HashSet;
import java.math.BigInteger;

public class dj
{
    private static final dj qJ;
    public static final String qK;
    private final Object li;
    public final String qL;
    private final dk qM;
    private BigInteger qN;
    private final HashSet<di> qO;
    private final HashMap<String, dm> qP;
    
    static {
        qJ = new dj();
        qK = dj.qJ.qL;
    }
    
    private dj() {
        this.li = new Object();
        this.qN = BigInteger.ONE;
        this.qO = new HashSet<di>();
        this.qP = new HashMap<String, dm>();
        this.qL = br();
        this.qM = new dk(this.qL);
    }
    
    public static Bundle a(final dl dl, final String s) {
        return dj.qJ.b(dl, s);
    }
    
    public static void b(final HashSet<di> set) {
        dj.qJ.c(set);
    }
    
    public static dj bq() {
        return dj.qJ;
    }
    
    private static String br() {
        final UUID randomUUID = UUID.randomUUID();
        final byte[] byteArray = BigInteger.valueOf(randomUUID.getLeastSignificantBits()).toByteArray();
        final byte[] byteArray2 = BigInteger.valueOf(randomUUID.getMostSignificantBits()).toByteArray();
        String s = new BigInteger(1, byteArray).toString();
        int n = 0;
    Label_0099_Outer:
        while (true) {
            if (n >= 2) {
                return s;
            }
            while (true) {
                try {
                    final MessageDigest instance = MessageDigest.getInstance("MD5");
                    instance.update(byteArray);
                    instance.update(byteArray2);
                    final byte[] array = new byte[8];
                    System.arraycopy(instance.digest(), 0, array, 0, 8);
                    s = new BigInteger(1, array).toString();
                    ++n;
                    continue Label_0099_Outer;
                }
                catch (NoSuchAlgorithmException ex) {
                    continue;
                }
                break;
            }
        }
    }
    
    public static String bs() {
        return dj.qJ.bt();
    }
    
    public static dk bu() {
        return dj.qJ.bv();
    }
    
    public void a(final di di) {
        synchronized (this.li) {
            this.qO.add(di);
        }
    }
    
    public void a(final String s, final dm dm) {
        synchronized (this.li) {
            this.qP.put(s, dm);
        }
    }
    
    public Bundle b(final dl dl, String s) {
        final Bundle bundle;
        synchronized (this.li) {
            bundle = new Bundle();
            bundle.putBundle("app", this.qM.q(s));
            s = (String)new Bundle();
            for (final String s2 : this.qP.keySet()) {
                ((Bundle)s).putBundle(s2, this.qP.get(s2).toBundle());
            }
        }
        bundle.putBundle("slots", (Bundle)s);
        final ArrayList<Bundle> list = new ArrayList<Bundle>();
        final Iterator<di> iterator2 = this.qO.iterator();
        while (iterator2.hasNext()) {
            list.add(iterator2.next().toBundle());
        }
        bundle.putParcelableArrayList("ads", (ArrayList)list);
        final dl dl2;
        dl2.a(this.qO);
        this.qO.clear();
        // monitorexit(o)
        return bundle;
    }
    
    public String bt() {
        synchronized (this.li) {
            final String string = this.qN.toString();
            this.qN = this.qN.add(BigInteger.ONE);
            return string;
        }
    }
    
    public dk bv() {
        synchronized (this.li) {
            return this.qM;
        }
    }
    
    public void c(final HashSet<di> set) {
        synchronized (this.li) {
            this.qO.addAll((Collection<?>)set);
        }
    }
}
