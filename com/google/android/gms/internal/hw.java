// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.Status;
import android.os.IInterface;

public interface hw extends IInterface
{
    void a(final Status p0);
    
    void a(final Status p0, final ParcelFileDescriptor p1);
    
    void a(final Status p0, final boolean p1);
    
    void a(final hm$b p0);
}
