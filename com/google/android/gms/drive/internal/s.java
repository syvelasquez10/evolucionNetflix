// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.api.c;
import com.google.android.gms.common.api.BaseImplementation;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveFile;

public class s extends w implements DriveFile
{
    public s(final DriveId driveId) {
        super(driveId);
    }
    
    private static DownloadProgressListener a(final GoogleApiClient googleApiClient, final DownloadProgressListener downloadProgressListener) {
        if (downloadProgressListener == null) {
            return null;
        }
        return new a(googleApiClient.c(downloadProgressListener));
    }
    
    @Override
    public PendingResult<Status> commitAndCloseContents(final GoogleApiClient googleApiClient, final Contents contents) {
        return new r(contents).commit(googleApiClient, null);
    }
    
    @Override
    public PendingResult<Status> commitAndCloseContents(final GoogleApiClient googleApiClient, final Contents contents, final MetadataChangeSet set) {
        return new r(contents).commit(googleApiClient, set);
    }
    
    @Override
    public PendingResult<Status> discardContents(final GoogleApiClient googleApiClient, final Contents contents) {
        return Drive.DriveApi.discardContents(googleApiClient, contents);
    }
    
    @Override
    public PendingResult<DriveApi.DriveContentsResult> open(final GoogleApiClient googleApiClient, final int n, final DownloadProgressListener downloadProgressListener) {
        if (n != 268435456 && n != 536870912 && n != 805306368) {
            throw new IllegalArgumentException("Invalid mode provided.");
        }
        return googleApiClient.a((PendingResult<DriveApi.DriveContentsResult>)new o.d() {
            final /* synthetic */ DownloadProgressListener OG = a(googleApiClient, downloadProgressListener);
            
            protected void a(final q q) throws RemoteException {
                q.hY().a(new OpenContentsRequest(s.this.getDriveId(), n, 0), new av((BaseImplementation.b<DriveContentsResult>)this, this.OG));
            }
        });
    }
    
    @Override
    public PendingResult<DriveApi.ContentsResult> openContents(final GoogleApiClient googleApiClient, final int n, final DownloadProgressListener downloadProgressListener) {
        if (n != 268435456 && n != 536870912 && n != 805306368) {
            throw new IllegalArgumentException("Invalid mode provided.");
        }
        return googleApiClient.a((PendingResult<DriveApi.ContentsResult>)new o.b() {
            final /* synthetic */ DownloadProgressListener OG = a(googleApiClient, downloadProgressListener);
            
            protected void a(final q q) throws RemoteException {
                q.hY().a(new OpenContentsRequest(s.this.getDriveId(), n, 0), new s.b((BaseImplementation.b<ContentsResult>)this, this.OG));
            }
        });
    }
    
    private static class a implements DownloadProgressListener
    {
        private final com.google.android.gms.common.api.c<DownloadProgressListener> OI;
        
        public a(final com.google.android.gms.common.api.c<DownloadProgressListener> oi) {
            this.OI = oi;
        }
        
        @Override
        public void onProgress(final long n, final long n2) {
            this.OI.a((com.google.android.gms.common.api.c.b<DownloadProgressListener>)new com.google.android.gms.common.api.c.b<DownloadProgressListener>() {
                public void a(final DownloadProgressListener downloadProgressListener) {
                    downloadProgressListener.onProgress(n, n2);
                }
                
                @Override
                public void gs() {
                }
            });
        }
    }
    
    private static class b extends c
    {
        private final BaseImplementation.b<DriveApi.ContentsResult> De;
        private final DownloadProgressListener OM;
        
        public b(final BaseImplementation.b<DriveApi.ContentsResult> de, final DownloadProgressListener om) {
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
            this.De.b(new o.a(jo, onContentsResponse.id()));
        }
        
        @Override
        public void a(final OnDownloadProgressResponse onDownloadProgressResponse) throws RemoteException {
            if (this.OM != null) {
                this.OM.onProgress(onDownloadProgressResponse.if(), onDownloadProgressResponse.ig());
            }
        }
        
        @Override
        public void o(final Status status) throws RemoteException {
            this.De.b(new o.a(status, null));
        }
    }
}
