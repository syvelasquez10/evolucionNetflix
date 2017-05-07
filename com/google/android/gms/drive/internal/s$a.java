// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.api.c$b;
import com.google.android.gms.common.api.c;
import com.google.android.gms.drive.DriveFile$DownloadProgressListener;

class s$a implements DriveFile$DownloadProgressListener
{
    private final c<DriveFile$DownloadProgressListener> OI;
    
    public s$a(final c<DriveFile$DownloadProgressListener> oi) {
        this.OI = oi;
    }
    
    @Override
    public void onProgress(final long n, final long n2) {
        this.OI.a(new s$a$1(this, n, n2));
    }
}
