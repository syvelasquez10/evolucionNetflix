// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.realtime.internal.m$a;
import com.google.android.gms.common.api.Status;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class ac$a extends Binder implements ac
{
    public ac$a() {
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
        return new ac$a$a(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
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
                this.a(onLoadRealtimeResponse2, m$a.ai(parcel.readStrongBinder()));
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
}
