// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.RealtimeDocumentSyncRequest;
import android.os.Parcel;
import android.content.IntentSender;
import android.os.IBinder;

class ab$a$a implements ab
{
    private IBinder lb;
    
    ab$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public IntentSender a(final CreateFileIntentSenderRequest createFileIntentSenderRequest) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                if (createFileIntentSenderRequest != null) {
                    obtain.writeInt(1);
                    createFileIntentSenderRequest.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                this.lb.transact(11, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    return (IntentSender)IntentSender.CREATOR.createFromParcel(obtain2);
                }
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
            return null;
        }
    }
    
    @Override
    public IntentSender a(final OpenFileIntentSenderRequest openFileIntentSenderRequest) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                if (openFileIntentSenderRequest != null) {
                    obtain.writeInt(1);
                    openFileIntentSenderRequest.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                this.lb.transact(10, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    return (IntentSender)IntentSender.CREATOR.createFromParcel(obtain2);
                }
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
            return null;
        }
    }
    
    @Override
    public void a(final RealtimeDocumentSyncRequest realtimeDocumentSyncRequest, final ac ac) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (realtimeDocumentSyncRequest != null) {
                        obtain.writeInt(1);
                        realtimeDocumentSyncRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (ac != null) {
                        final IBinder binder = ac.asBinder();
                        obtain.writeStrongBinder(binder);
                        this.lb.transact(34, obtain, obtain2, 0);
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
    public void a(final AddEventListenerRequest addEventListenerRequest, final ad ad, final String s, final ac ac) {
        while (true) {
            final IBinder binder = null;
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (addEventListenerRequest != null) {
                        obtain.writeInt(1);
                        addEventListenerRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (ad != null) {
                        final IBinder binder2 = ad.asBinder();
                        obtain.writeStrongBinder(binder2);
                        obtain.writeString(s);
                        IBinder binder3 = binder;
                        if (ac != null) {
                            binder3 = ac.asBinder();
                        }
                        obtain.writeStrongBinder(binder3);
                        this.lb.transact(14, obtain, obtain2, 0);
                        obtain2.readException();
                        return;
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
                final IBinder binder2 = null;
                continue;
            }
        }
    }
    
    @Override
    public void a(final AuthorizeAccessRequest authorizeAccessRequest, final ac ac) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (authorizeAccessRequest != null) {
                        obtain.writeInt(1);
                        authorizeAccessRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (ac != null) {
                        final IBinder binder = ac.asBinder();
                        obtain.writeStrongBinder(binder);
                        this.lb.transact(12, obtain, obtain2, 0);
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
    public void a(final CheckResourceIdsExistRequest checkResourceIdsExistRequest, final ac ac) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (checkResourceIdsExistRequest != null) {
                        obtain.writeInt(1);
                        checkResourceIdsExistRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (ac != null) {
                        final IBinder binder = ac.asBinder();
                        obtain.writeStrongBinder(binder);
                        this.lb.transact(30, obtain, obtain2, 0);
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
    public void a(final CloseContentsAndUpdateMetadataRequest closeContentsAndUpdateMetadataRequest, final ac ac) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (closeContentsAndUpdateMetadataRequest != null) {
                        obtain.writeInt(1);
                        closeContentsAndUpdateMetadataRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (ac != null) {
                        final IBinder binder = ac.asBinder();
                        obtain.writeStrongBinder(binder);
                        this.lb.transact(18, obtain, obtain2, 0);
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
    public void a(final CloseContentsRequest closeContentsRequest, final ac ac) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (closeContentsRequest != null) {
                        obtain.writeInt(1);
                        closeContentsRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (ac != null) {
                        final IBinder binder = ac.asBinder();
                        obtain.writeStrongBinder(binder);
                        this.lb.transact(8, obtain, obtain2, 0);
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
    public void a(final CreateContentsRequest createContentsRequest, final ac ac) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (createContentsRequest != null) {
                        obtain.writeInt(1);
                        createContentsRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (ac != null) {
                        final IBinder binder = ac.asBinder();
                        obtain.writeStrongBinder(binder);
                        this.lb.transact(4, obtain, obtain2, 0);
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
    public void a(final CreateFileRequest createFileRequest, final ac ac) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (createFileRequest != null) {
                        obtain.writeInt(1);
                        createFileRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (ac != null) {
                        final IBinder binder = ac.asBinder();
                        obtain.writeStrongBinder(binder);
                        this.lb.transact(5, obtain, obtain2, 0);
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
    public void a(final CreateFolderRequest createFolderRequest, final ac ac) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (createFolderRequest != null) {
                        obtain.writeInt(1);
                        createFolderRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (ac != null) {
                        final IBinder binder = ac.asBinder();
                        obtain.writeStrongBinder(binder);
                        this.lb.transact(6, obtain, obtain2, 0);
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
    public void a(final DeleteResourceRequest deleteResourceRequest, final ac ac) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (deleteResourceRequest != null) {
                        obtain.writeInt(1);
                        deleteResourceRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (ac != null) {
                        final IBinder binder = ac.asBinder();
                        obtain.writeStrongBinder(binder);
                        this.lb.transact(24, obtain, obtain2, 0);
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
    public void a(final DisconnectRequest disconnectRequest) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (disconnectRequest != null) {
                obtain.writeInt(1);
                disconnectRequest.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(16, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final GetDriveIdFromUniqueIdentifierRequest getDriveIdFromUniqueIdentifierRequest, final ac ac) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (getDriveIdFromUniqueIdentifierRequest != null) {
                        obtain.writeInt(1);
                        getDriveIdFromUniqueIdentifierRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (ac != null) {
                        final IBinder binder = ac.asBinder();
                        obtain.writeStrongBinder(binder);
                        this.lb.transact(29, obtain, obtain2, 0);
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
    public void a(final GetMetadataRequest getMetadataRequest, final ac ac) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (getMetadataRequest != null) {
                        obtain.writeInt(1);
                        getMetadataRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (ac != null) {
                        final IBinder binder = ac.asBinder();
                        obtain.writeStrongBinder(binder);
                        this.lb.transact(1, obtain, obtain2, 0);
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
    public void a(final ListParentsRequest listParentsRequest, final ac ac) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (listParentsRequest != null) {
                        obtain.writeInt(1);
                        listParentsRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (ac != null) {
                        final IBinder binder = ac.asBinder();
                        obtain.writeStrongBinder(binder);
                        this.lb.transact(13, obtain, obtain2, 0);
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
    public void a(final LoadRealtimeRequest loadRealtimeRequest, final ac ac) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (loadRealtimeRequest != null) {
                        obtain.writeInt(1);
                        loadRealtimeRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (ac != null) {
                        final IBinder binder = ac.asBinder();
                        obtain.writeStrongBinder(binder);
                        this.lb.transact(27, obtain, obtain2, 0);
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
    public void a(final OpenContentsRequest openContentsRequest, final ac ac) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (openContentsRequest != null) {
                        obtain.writeInt(1);
                        openContentsRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (ac != null) {
                        final IBinder binder = ac.asBinder();
                        obtain.writeStrongBinder(binder);
                        this.lb.transact(7, obtain, obtain2, 0);
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
    public void a(final QueryRequest queryRequest, final ac ac) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (queryRequest != null) {
                        obtain.writeInt(1);
                        queryRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (ac != null) {
                        final IBinder binder = ac.asBinder();
                        obtain.writeStrongBinder(binder);
                        this.lb.transact(2, obtain, obtain2, 0);
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
    public void a(final RemoveEventListenerRequest removeEventListenerRequest, final ad ad, final String s, final ac ac) {
        while (true) {
            final IBinder binder = null;
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (removeEventListenerRequest != null) {
                        obtain.writeInt(1);
                        removeEventListenerRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (ad != null) {
                        final IBinder binder2 = ad.asBinder();
                        obtain.writeStrongBinder(binder2);
                        obtain.writeString(s);
                        IBinder binder3 = binder;
                        if (ac != null) {
                            binder3 = ac.asBinder();
                        }
                        obtain.writeStrongBinder(binder3);
                        this.lb.transact(15, obtain, obtain2, 0);
                        obtain2.readException();
                        return;
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
                final IBinder binder2 = null;
                continue;
            }
        }
    }
    
    @Override
    public void a(final SetDrivePreferencesRequest setDrivePreferencesRequest, final ac ac) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (setDrivePreferencesRequest != null) {
                        obtain.writeInt(1);
                        setDrivePreferencesRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (ac != null) {
                        final IBinder binder = ac.asBinder();
                        obtain.writeStrongBinder(binder);
                        this.lb.transact(33, obtain, obtain2, 0);
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
    public void a(final SetResourceParentsRequest setResourceParentsRequest, final ac ac) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (setResourceParentsRequest != null) {
                        obtain.writeInt(1);
                        setResourceParentsRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (ac != null) {
                        final IBinder binder = ac.asBinder();
                        obtain.writeStrongBinder(binder);
                        this.lb.transact(28, obtain, obtain2, 0);
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
    public void a(final TrashResourceRequest trashResourceRequest, final ac ac) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (trashResourceRequest != null) {
                        obtain.writeInt(1);
                        trashResourceRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (ac != null) {
                        final IBinder binder = ac.asBinder();
                        obtain.writeStrongBinder(binder);
                        this.lb.transact(17, obtain, obtain2, 0);
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
    public void a(final UpdateMetadataRequest updateMetadataRequest, final ac ac) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (updateMetadataRequest != null) {
                        obtain.writeInt(1);
                        updateMetadataRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (ac != null) {
                        final IBinder binder = ac.asBinder();
                        obtain.writeStrongBinder(binder);
                        this.lb.transact(3, obtain, obtain2, 0);
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
    public void a(final ac ac) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            IBinder binder;
            if (ac != null) {
                binder = ac.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
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
    public void b(final QueryRequest queryRequest, final ac ac) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (queryRequest != null) {
                        obtain.writeInt(1);
                        queryRequest.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (ac != null) {
                        final IBinder binder = ac.asBinder();
                        obtain.writeStrongBinder(binder);
                        this.lb.transact(19, obtain, obtain2, 0);
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
    public void b(final ac ac) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            IBinder binder;
            if (ac != null) {
                binder = ac.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.lb.transact(20, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void c(final ac ac) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            IBinder binder;
            if (ac != null) {
                binder = ac.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.lb.transact(21, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void d(final ac ac) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            IBinder binder;
            if (ac != null) {
                binder = ac.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.lb.transact(22, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void e(final ac ac) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            IBinder binder;
            if (ac != null) {
                binder = ac.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.lb.transact(23, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void f(final ac ac) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            IBinder binder;
            if (ac != null) {
                binder = ac.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.lb.transact(31, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void g(final ac ac) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            IBinder binder;
            if (ac != null) {
                binder = ac.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.lb.transact(32, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
