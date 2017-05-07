// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.realtime.internal.m;
import android.os.RemoteException;
import android.os.IInterface;

public interface ac extends IInterface
{
    void a(final OnContentsResponse p0) throws RemoteException;
    
    void a(final OnDownloadProgressResponse p0) throws RemoteException;
    
    void a(final OnDriveIdResponse p0) throws RemoteException;
    
    void a(final OnDrivePreferencesResponse p0) throws RemoteException;
    
    void a(final OnListEntriesResponse p0) throws RemoteException;
    
    void a(final OnListParentsResponse p0) throws RemoteException;
    
    void a(final OnLoadRealtimeResponse p0, final m p1) throws RemoteException;
    
    void a(final OnMetadataResponse p0) throws RemoteException;
    
    void a(final OnResourceIdSetResponse p0) throws RemoteException;
    
    void a(final OnStorageStatsResponse p0) throws RemoteException;
    
    void a(final OnSyncMoreResponse p0) throws RemoteException;
    
    void o(final Status p0) throws RemoteException;
    
    void onSuccess() throws RemoteException;
    
    public abstract static class a extends Binder implements ac
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.drive.internal.IDriveServiceCallbacks");
        }
        
        public static ac V(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
            if (queryLocalInterface != null && queryLocalInterface instanceof ac) {
                return (ac)queryLocalInterface;
            }
            return new ac.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final OnListEntriesResponse onListEntriesResponse = null;
            final OnDriveIdResponse onDriveIdResponse = null;
            final OnMetadataResponse onMetadataResponse = null;
            final OnContentsResponse onContentsResponse = null;
            final Status status = null;
            final OnListParentsResponse onListParentsResponse = null;
            final OnSyncMoreResponse onSyncMoreResponse = null;
            final OnStorageStatsResponse onStorageStatsResponse = null;
            final OnLoadRealtimeResponse onLoadRealtimeResponse = null;
            final OnResourceIdSetResponse onResourceIdSetResponse = null;
            final OnDrivePreferencesResponse onDrivePreferencesResponse = null;
            OnDownloadProgressResponse onDownloadProgressResponse = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    if (parcel.readInt() != 0) {
                        onDownloadProgressResponse = (OnDownloadProgressResponse)OnDownloadProgressResponse.CREATOR.createFromParcel(parcel);
                    }
                    this.a(onDownloadProgressResponse);
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    OnListEntriesResponse onListEntriesResponse2 = onListEntriesResponse;
                    if (parcel.readInt() != 0) {
                        onListEntriesResponse2 = (OnListEntriesResponse)OnListEntriesResponse.CREATOR.createFromParcel(parcel);
                    }
                    this.a(onListEntriesResponse2);
                    parcel2.writeNoException();
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    OnDriveIdResponse onDriveIdResponse2 = onDriveIdResponse;
                    if (parcel.readInt() != 0) {
                        onDriveIdResponse2 = (OnDriveIdResponse)OnDriveIdResponse.CREATOR.createFromParcel(parcel);
                    }
                    this.a(onDriveIdResponse2);
                    parcel2.writeNoException();
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    OnMetadataResponse onMetadataResponse2 = onMetadataResponse;
                    if (parcel.readInt() != 0) {
                        onMetadataResponse2 = (OnMetadataResponse)OnMetadataResponse.CREATOR.createFromParcel(parcel);
                    }
                    this.a(onMetadataResponse2);
                    parcel2.writeNoException();
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    OnContentsResponse onContentsResponse2 = onContentsResponse;
                    if (parcel.readInt() != 0) {
                        onContentsResponse2 = (OnContentsResponse)OnContentsResponse.CREATOR.createFromParcel(parcel);
                    }
                    this.a(onContentsResponse2);
                    parcel2.writeNoException();
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    Status fromParcel = status;
                    if (parcel.readInt() != 0) {
                        fromParcel = Status.CREATOR.createFromParcel(parcel);
                    }
                    this.o(fromParcel);
                    parcel2.writeNoException();
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    this.onSuccess();
                    parcel2.writeNoException();
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    OnListParentsResponse onListParentsResponse2 = onListParentsResponse;
                    if (parcel.readInt() != 0) {
                        onListParentsResponse2 = (OnListParentsResponse)OnListParentsResponse.CREATOR.createFromParcel(parcel);
                    }
                    this.a(onListParentsResponse2);
                    parcel2.writeNoException();
                    return true;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    OnSyncMoreResponse onSyncMoreResponse2 = onSyncMoreResponse;
                    if (parcel.readInt() != 0) {
                        onSyncMoreResponse2 = (OnSyncMoreResponse)OnSyncMoreResponse.CREATOR.createFromParcel(parcel);
                    }
                    this.a(onSyncMoreResponse2);
                    parcel2.writeNoException();
                    return true;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    OnStorageStatsResponse onStorageStatsResponse2 = onStorageStatsResponse;
                    if (parcel.readInt() != 0) {
                        onStorageStatsResponse2 = (OnStorageStatsResponse)OnStorageStatsResponse.CREATOR.createFromParcel(parcel);
                    }
                    this.a(onStorageStatsResponse2);
                    parcel2.writeNoException();
                    return true;
                }
                case 11: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    OnLoadRealtimeResponse onLoadRealtimeResponse2 = onLoadRealtimeResponse;
                    if (parcel.readInt() != 0) {
                        onLoadRealtimeResponse2 = (OnLoadRealtimeResponse)OnLoadRealtimeResponse.CREATOR.createFromParcel(parcel);
                    }
                    this.a(onLoadRealtimeResponse2, m.a.ai(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 12: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    OnResourceIdSetResponse onResourceIdSetResponse2 = onResourceIdSetResponse;
                    if (parcel.readInt() != 0) {
                        onResourceIdSetResponse2 = (OnResourceIdSetResponse)OnResourceIdSetResponse.CREATOR.createFromParcel(parcel);
                    }
                    this.a(onResourceIdSetResponse2);
                    parcel2.writeNoException();
                    return true;
                }
                case 13: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    OnDrivePreferencesResponse onDrivePreferencesResponse2 = onDrivePreferencesResponse;
                    if (parcel.readInt() != 0) {
                        onDrivePreferencesResponse2 = (OnDrivePreferencesResponse)OnDrivePreferencesResponse.CREATOR.createFromParcel(parcel);
                    }
                    this.a(onDrivePreferencesResponse2);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements ac
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public void a(final OnContentsResponse onContentsResponse) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    if (onContentsResponse != null) {
                        obtain.writeInt(1);
                        onContentsResponse.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final OnDownloadProgressResponse onDownloadProgressResponse) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    if (onDownloadProgressResponse != null) {
                        obtain.writeInt(1);
                        onDownloadProgressResponse.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final OnDriveIdResponse onDriveIdResponse) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    if (onDriveIdResponse != null) {
                        obtain.writeInt(1);
                        onDriveIdResponse.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final OnDrivePreferencesResponse onDrivePreferencesResponse) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    if (onDrivePreferencesResponse != null) {
                        obtain.writeInt(1);
                        onDrivePreferencesResponse.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final OnListEntriesResponse onListEntriesResponse) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    if (onListEntriesResponse != null) {
                        obtain.writeInt(1);
                        onListEntriesResponse.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final OnListParentsResponse onListParentsResponse) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    if (onListParentsResponse != null) {
                        obtain.writeInt(1);
                        onListParentsResponse.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final OnLoadRealtimeResponse onLoadRealtimeResponse, final m m) throws RemoteException {
                while (true) {
                    final Parcel obtain = Parcel.obtain();
                    final Parcel obtain2 = Parcel.obtain();
                    while (true) {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                            if (onLoadRealtimeResponse != null) {
                                obtain.writeInt(1);
                                onLoadRealtimeResponse.writeToParcel(obtain, 0);
                            }
                            else {
                                obtain.writeInt(0);
                            }
                            if (m != null) {
                                final IBinder binder = m.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.lb.transact(11, obtain, obtain2, 0);
                                obtain2.readException();
                                return;
                            }
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        final IBinder binder = null;
                        continue;
                    }
                }
            }
            
            @Override
            public void a(final OnMetadataResponse onMetadataResponse) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    if (onMetadataResponse != null) {
                        obtain.writeInt(1);
                        onMetadataResponse.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final OnResourceIdSetResponse onResourceIdSetResponse) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    if (onResourceIdSetResponse != null) {
                        obtain.writeInt(1);
                        onResourceIdSetResponse.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final OnStorageStatsResponse onStorageStatsResponse) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    if (onStorageStatsResponse != null) {
                        obtain.writeInt(1);
                        onStorageStatsResponse.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final OnSyncMoreResponse onSyncMoreResponse) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    if (onSyncMoreResponse != null) {
                        obtain.writeInt(1);
                        onSyncMoreResponse.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public void o(final Status status) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    if (status != null) {
                        obtain.writeInt(1);
                        status.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onSuccess() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
                    this.lb.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
