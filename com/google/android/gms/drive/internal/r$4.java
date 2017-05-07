// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.DriveApi$DriveContentsResult;
import android.os.ParcelFileDescriptor;
import java.io.OutputStream;
import java.io.InputStream;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.drive.ExecutionOptions$Builder;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.MetadataChangeSet;

class r$4 extends p$a
{
    final /* synthetic */ r OD;
    final /* synthetic */ MetadataChangeSet OE;
    final /* synthetic */ ExecutionOptions OF;
    
    r$4(final r od, final MetadataChangeSet oe, final ExecutionOptions of) {
        this.OD = od;
        this.OE = oe;
        this.OF = of;
    }
    
    @Override
    protected void a(final q q) {
        this.OE.hS().setContext(q.getContext());
        q.hY().a(new CloseContentsAndUpdateMetadataRequest(this.OD.Op.getDriveId(), this.OE.hS(), this.OD.Op, this.OF), new bb((BaseImplementation$b<Status>)this));
    }
}
