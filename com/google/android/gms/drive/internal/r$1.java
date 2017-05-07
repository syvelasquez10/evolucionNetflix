// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.ParcelFileDescriptor;
import java.io.OutputStream;
import java.io.InputStream;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.drive.ExecutionOptions$Builder;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile$DownloadProgressListener;
import com.google.android.gms.drive.DriveApi$DriveContentsResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;

class r$1 extends o$d
{
    final /* synthetic */ r OD;
    
    r$1(final r od) {
        this.OD = od;
    }
    
    @Override
    protected void a(final q q) {
        q.hY().a(new OpenContentsRequest(this.OD.getDriveId(), 536870912, this.OD.Op.getRequestId()), new av((BaseImplementation$b<DriveApi$DriveContentsResult>)this, null));
    }
}
