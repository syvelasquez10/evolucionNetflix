// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.common.api.a;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveFile;

public class o extends r implements DriveFile
{
    public o(final DriveId driveId) {
        super(driveId);
    }
    
    @Override
    public PendingResult<Status> commitAndCloseContents(final GoogleApiClient googleApiClient, final Contents contents) {
        if (contents == null) {
            throw new IllegalArgumentException("Contents must be provided.");
        }
        return googleApiClient.b((PendingResult<Status>)new b() {
            protected void a(final n n) throws RemoteException {
                contents.close();
                n.fE().a(new CloseContentsRequest(contents, true), new al((a.d<Status>)this));
            }
        });
    }
    
    @Override
    public PendingResult<Status> commitAndCloseContents(final GoogleApiClient googleApiClient, final Contents contents, final MetadataChangeSet set) {
        if (contents == null) {
            throw new IllegalArgumentException("Contents must be provided.");
        }
        return googleApiClient.b((PendingResult<Status>)new a() {
            protected void a(final n n) throws RemoteException {
                contents.close();
                n.fE().a(new CloseContentsAndUpdateMetadataRequest(o.this.Ew, set.fD(), contents), new al((com.google.android.gms.common.api.a.d<Status>)this));
            }
        });
    }
    
    @Override
    public PendingResult<Status> discardContents(final GoogleApiClient googleApiClient, final Contents contents) {
        return Drive.DriveApi.discardContents(googleApiClient, contents);
    }
    
    @Override
    public PendingResult<DriveApi.ContentsResult> openContents(final GoogleApiClient googleApiClient, final int n, final DownloadProgressListener downloadProgressListener) {
        if (n != 268435456 && n != 536870912 && n != 805306368) {
            throw new IllegalArgumentException("Invalid mode provided.");
        }
        return googleApiClient.a((PendingResult<DriveApi.ContentsResult>)new d() {
            protected void a(final n n) throws RemoteException {
                n.fE().a(new OpenContentsRequest(o.this.getDriveId(), n), new o.c((com.google.android.gms.common.api.a.d<DriveApi.ContentsResult>)this, downloadProgressListener));
            }
        });
    }
    
    private abstract class a extends m<Status>
    {
        public Status f(final Status status) {
            return status;
        }
    }
    
    private abstract class b extends m<Status>
    {
        public Status f(final Status status) {
            return status;
        }
    }
    
    private static class c extends com.google.android.gms.drive.internal.c
    {
        private final DownloadProgressListener Ft;
        private final com.google.android.gms.common.api.a.d<DriveApi.ContentsResult> wH;
        
        public c(final com.google.android.gms.common.api.a.d<DriveApi.ContentsResult> wh, final DownloadProgressListener ft) {
            this.wH = wh;
            this.Ft = ft;
        }
        
        @Override
        public void a(final OnContentsResponse onContentsResponse) throws RemoteException {
            this.wH.b(new l.a(Status.Bv, onContentsResponse.fI()));
        }
        
        @Override
        public void a(final OnDownloadProgressResponse onDownloadProgressResponse) throws RemoteException {
            if (this.Ft != null) {
                this.Ft.onProgress(onDownloadProgressResponse.fJ(), onDownloadProgressResponse.fK());
            }
        }
        
        @Override
        public void m(final Status status) throws RemoteException {
            this.wH.b(new l.a(status, null));
        }
    }
    
    private abstract class d extends m<DriveApi.ContentsResult>
    {
        public DriveApi.ContentsResult o(final Status status) {
            return new l.a(status, null);
        }
    }
}
