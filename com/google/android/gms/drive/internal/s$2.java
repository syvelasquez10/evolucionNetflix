// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveApi$DriveContentsResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.drive.DriveFile$DownloadProgressListener;

class s$2 extends o$d
{
    final /* synthetic */ DriveFile$DownloadProgressListener OG;
    final /* synthetic */ s OH;
    final /* synthetic */ int Om;
    
    s$2(final s oh, final int om, final DriveFile$DownloadProgressListener og) {
        this.OH = oh;
        this.Om = om;
        this.OG = og;
    }
    
    @Override
    protected void a(final q q) {
        q.hY().a(new OpenContentsRequest(this.OH.getDriveId(), this.Om, 0), new av((BaseImplementation$b<DriveApi$DriveContentsResult>)this, this.OG));
    }
}
