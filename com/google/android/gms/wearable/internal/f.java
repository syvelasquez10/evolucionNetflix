// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import java.io.IOException;
import android.os.ParcelFileDescriptor$AutoCloseInputStream;
import java.io.InputStream;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.DataItemAsset;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataItemBuffer;
import com.google.android.gms.wearable.DataItem;
import android.net.Uri;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.BaseImplementation;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import android.content.IntentFilter;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;

public final class f implements DataApi
{
    private PendingResult<Status> a(final GoogleApiClient googleApiClient, final DataListener dataListener, final IntentFilter[] array) {
        return googleApiClient.a((PendingResult<Status>)new d<Status>() {
            protected void a(final aw aw) throws RemoteException {
                aw.a((BaseImplementation.b<Status>)this, dataListener, array);
            }
            
            public Status d(final Status status) {
                return new Status(13);
            }
        });
    }
    
    private void a(final Asset asset) {
        if (asset == null) {
            throw new IllegalArgumentException("asset is null");
        }
        if (asset.getDigest() == null) {
            throw new IllegalArgumentException("invalid asset");
        }
        if (asset.getData() != null) {
            throw new IllegalArgumentException("invalid asset");
        }
    }
    
    @Override
    public PendingResult<Status> addListener(final GoogleApiClient googleApiClient, final DataListener dataListener) {
        return this.a(googleApiClient, dataListener, null);
    }
    
    @Override
    public PendingResult<DeleteDataItemsResult> deleteDataItems(final GoogleApiClient googleApiClient, final Uri uri) {
        return googleApiClient.a((PendingResult<DeleteDataItemsResult>)new d<DeleteDataItemsResult>() {
            protected void a(final aw aw) throws RemoteException {
                aw.c((BaseImplementation.b<DeleteDataItemsResult>)this, uri);
            }
            
            protected DeleteDataItemsResult aH(final Status status) {
                return new f.b(status, 0);
            }
        });
    }
    
    @Override
    public PendingResult<DataItemResult> getDataItem(final GoogleApiClient googleApiClient, final Uri uri) {
        return googleApiClient.a((PendingResult<DataItemResult>)new d<DataItemResult>() {
            protected void a(final aw aw) throws RemoteException {
                aw.a((BaseImplementation.b<DataItemResult>)this, uri);
            }
            
            protected DataItemResult aF(final Status status) {
                return new f.a(status, null);
            }
        });
    }
    
    @Override
    public PendingResult<DataItemBuffer> getDataItems(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<DataItemBuffer>)new d<DataItemBuffer>() {
            protected void a(final aw aw) throws RemoteException {
                aw.o((BaseImplementation.b<DataItemBuffer>)this);
            }
            
            protected DataItemBuffer aG(final Status status) {
                return new DataItemBuffer(DataHolder.as(status.getStatusCode()));
            }
        });
    }
    
    @Override
    public PendingResult<DataItemBuffer> getDataItems(final GoogleApiClient googleApiClient, final Uri uri) {
        return googleApiClient.a((PendingResult<DataItemBuffer>)new d<DataItemBuffer>() {
            protected void a(final aw aw) throws RemoteException {
                aw.b((BaseImplementation.b<DataItemBuffer>)this, uri);
            }
            
            protected DataItemBuffer aG(final Status status) {
                return new DataItemBuffer(DataHolder.as(status.getStatusCode()));
            }
        });
    }
    
    @Override
    public PendingResult<GetFdForAssetResult> getFdForAsset(final GoogleApiClient googleApiClient, final Asset asset) {
        this.a(asset);
        return googleApiClient.a((PendingResult<GetFdForAssetResult>)new d<GetFdForAssetResult>() {
            protected void a(final aw aw) throws RemoteException {
                aw.a((BaseImplementation.b<GetFdForAssetResult>)this, asset);
            }
            
            protected GetFdForAssetResult aI(final Status status) {
                return new f.c(status, null);
            }
        });
    }
    
    @Override
    public PendingResult<GetFdForAssetResult> getFdForAsset(final GoogleApiClient googleApiClient, final DataItemAsset dataItemAsset) {
        return googleApiClient.a((PendingResult<GetFdForAssetResult>)new d<GetFdForAssetResult>() {
            protected void a(final aw aw) throws RemoteException {
                aw.a((BaseImplementation.b<GetFdForAssetResult>)this, dataItemAsset);
            }
            
            protected GetFdForAssetResult aI(final Status status) {
                return new f.c(status, null);
            }
        });
    }
    
    @Override
    public PendingResult<DataItemResult> putDataItem(final GoogleApiClient googleApiClient, final PutDataRequest putDataRequest) {
        return googleApiClient.a((PendingResult<DataItemResult>)new d<DataItemResult>() {
            protected void a(final aw aw) throws RemoteException {
                aw.a((BaseImplementation.b<DataItemResult>)this, putDataRequest);
            }
            
            public DataItemResult aF(final Status status) {
                return new f.a(status, null);
            }
        });
    }
    
    @Override
    public PendingResult<Status> removeListener(final GoogleApiClient googleApiClient, final DataListener dataListener) {
        return googleApiClient.a((PendingResult<Status>)new d<Status>() {
            protected void a(final aw aw) throws RemoteException {
                aw.a((BaseImplementation.b<Status>)this, dataListener);
            }
            
            public Status d(final Status status) {
                return new Status(13);
            }
        });
    }
    
    public static class a implements DataItemResult
    {
        private final Status CM;
        private final DataItem avh;
        
        public a(final Status cm, final DataItem avh) {
            this.CM = cm;
            this.avh = avh;
        }
        
        @Override
        public DataItem getDataItem() {
            return this.avh;
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
    }
    
    public static class b implements DeleteDataItemsResult
    {
        private final Status CM;
        private final int avi;
        
        public b(final Status cm, final int avi) {
            this.CM = cm;
            this.avi = avi;
        }
        
        @Override
        public int getNumDeleted() {
            return this.avi;
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
    }
    
    public static class c implements GetFdForAssetResult
    {
        private final Status CM;
        private volatile InputStream XM;
        private volatile ParcelFileDescriptor avj;
        private volatile boolean mClosed;
        
        public c(final Status cm, final ParcelFileDescriptor avj) {
            this.mClosed = false;
            this.CM = cm;
            this.avj = avj;
        }
        
        @Override
        public ParcelFileDescriptor getFd() {
            if (this.mClosed) {
                throw new IllegalStateException("Cannot access the file descriptor after release().");
            }
            return this.avj;
        }
        
        @Override
        public InputStream getInputStream() {
            if (this.mClosed) {
                throw new IllegalStateException("Cannot access the input stream after release().");
            }
            if (this.avj == null) {
                return null;
            }
            if (this.XM == null) {
                this.XM = (InputStream)new ParcelFileDescriptor$AutoCloseInputStream(this.avj);
            }
            return this.XM;
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
        
        @Override
        public void release() {
            if (this.avj == null) {
                return;
            }
            if (this.mClosed) {
                throw new IllegalStateException("releasing an already released result.");
            }
            try {
                if (this.XM != null) {
                    this.XM.close();
                }
                else {
                    this.avj.close();
                }
                this.mClosed = true;
                this.avj = null;
                this.XM = null;
            }
            catch (IOException ex) {}
        }
    }
}
