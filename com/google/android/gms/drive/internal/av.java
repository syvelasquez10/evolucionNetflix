// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.common.api.BaseImplementation;

class av extends c
{
    private final BaseImplementation.b<DriveApi.DriveContentsResult> De;
    private final DriveFile.DownloadProgressListener OM;
    
    av(final BaseImplementation.b<DriveApi.DriveContentsResult> de, final DriveFile.DownloadProgressListener om) {
        this.De = de;
        this.OM = om;
    }
    
    @Override
    public void a(final OnContentsResponse onContentsResponse) throws RemoteException {
        Status jo;
        if (onContentsResponse.ie()) {
            jo = new Status(-1);
        }
        else {
            jo = Status.Jo;
        }
        this.De.b(new o.c(jo, new r(onContentsResponse.id())));
    }
    
    @Override
    public void a(final OnDownloadProgressResponse onDownloadProgressResponse) throws RemoteException {
        if (this.OM != null) {
            this.OM.onProgress(onDownloadProgressResponse.if(), onDownloadProgressResponse.ig());
        }
    }
    
    @Override
    public void o(final Status status) throws RemoteException {
        this.De.b(new o.c(status, null));
    }
}
