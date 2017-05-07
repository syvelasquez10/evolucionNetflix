// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.ny;
import com.google.android.gms.internal.jp;
import android.os.ParcelFileDescriptor;
import android.os.Bundle;
import android.os.IInterface;

public interface b extends IInterface
{
    void a(final int p0, final Bundle p1, final Bundle p2);
    
    void a(final int p0, final Bundle p1, final ParcelFileDescriptor p2);
    
    void a(final int p0, final Bundle p1, final jp p2);
    
    void a(final int p0, final ny p1);
    
    void a(final DataHolder p0, final String p1);
    
    void a(final DataHolder p0, final String p1, final String p2);
    
    void aB(final Status p0);
    
    void cb(final String p0);
    
    void cc(final String p0);
    
    void h(final int p0, final Bundle p1);
}
