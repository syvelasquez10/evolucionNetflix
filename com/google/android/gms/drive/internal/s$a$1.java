// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveFile$DownloadProgressListener;
import com.google.android.gms.common.api.c$b;

class s$a$1 implements c$b<DriveFile$DownloadProgressListener>
{
    final /* synthetic */ long OJ;
    final /* synthetic */ long OK;
    final /* synthetic */ s$a OL;
    
    s$a$1(final s$a ol, final long oj, final long ok) {
        this.OL = ol;
        this.OJ = oj;
        this.OK = ok;
    }
    
    public void a(final DriveFile$DownloadProgressListener driveFile$DownloadProgressListener) {
        driveFile$DownloadProgressListener.onProgress(this.OJ, this.OK);
    }
    
    @Override
    public void gs() {
    }
}
