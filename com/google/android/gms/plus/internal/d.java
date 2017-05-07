// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.internal;

import com.google.android.gms.internal.jb;
import java.util.List;
import android.os.Bundle;
import android.net.Uri;
import com.google.android.gms.internal.jp;
import com.google.android.gms.common.internal.i;
import android.os.IInterface;

public interface d extends IInterface
{
    i a(final b p0, final int p1, final int p2, final int p3, final String p4);
    
    void a(final jp p0);
    
    void a(final b p0);
    
    void a(final b p0, final int p1, final String p2, final Uri p3, final String p4, final String p5);
    
    void a(final b p0, final Uri p1, final Bundle p2);
    
    void a(final b p0, final jp p1);
    
    void a(final b p0, final String p1);
    
    void a(final b p0, final String p1, final String p2);
    
    void a(final b p0, final List<String> p1);
    
    void a(final String p0, final jb p1, final jb p2);
    
    void b(final b p0);
    
    void b(final b p0, final String p1);
    
    void c(final b p0, final String p1);
    
    void clearDefaultAccount();
    
    void d(final b p0, final String p1);
    
    void e(final b p0, final String p1);
    
    String getAccountName();
    
    String mZ();
    
    boolean na();
    
    String nb();
    
    void removeMoment(final String p0);
}
