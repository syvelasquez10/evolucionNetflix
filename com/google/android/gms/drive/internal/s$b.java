// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.Contents;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveFile$DownloadProgressListener;
import com.google.android.gms.drive.DriveApi$ContentsResult;
import com.google.android.gms.common.api.BaseImplementation$b;

class s$b extends c
{
    private final BaseImplementation$b<DriveApi$ContentsResult> De;
    private final DriveFile$DownloadProgressListener OM;
    
    public s$b(final BaseImplementation$b<DriveApi$ContentsResult> de, final DriveFile$DownloadProgressListener om) {
        this.De = de;
        this.OM = om;
    }
    
    @Override
    public void a(final OnContentsResponse onContentsResponse) {
        Status jo;
        if (onContentsResponse.ie()) {
            jo = new Status(-1);
        }
        else {
            jo = Status.Jo;
        }
        this.De.b(new o$a(jo, onContentsResponse.id()));
    }
    
    @Override
    public void a(final OnDownloadProgressResponse onDownloadProgressResponse) {
        if (this.OM != null) {
            this.OM.onProgress(onDownloadProgressResponse.if(), onDownloadProgressResponse.ig());
        }
    }
    
    @Override
    public void o(final Status status) {
        this.De.b(new o$a(status, null));
    }
}
