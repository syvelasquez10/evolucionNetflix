// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.content.IntentSender;
import android.os.IInterface;

public interface o extends IInterface
{
    IntentSender a(final CreateFileIntentSenderRequest p0) throws RemoteException;
    
    IntentSender a(final OpenFileIntentSenderRequest p0) throws RemoteException;
    
    void a(final CloseContentsRequest p0, final p p1) throws RemoteException;
    
    void a(final CreateContentsRequest p0, final p p1) throws RemoteException;
    
    void a(final CreateFileRequest p0, final p p1) throws RemoteException;
    
    void a(final CreateFolderRequest p0, final p p1) throws RemoteException;
    
    void a(final GetMetadataRequest p0, final p p1) throws RemoteException;
    
    void a(final OpenContentsRequest p0, final p p1) throws RemoteException;
    
    void a(final QueryRequest p0, final p p1) throws RemoteException;
    
    void a(final UpdateMetadataRequest p0, final p p1) throws RemoteException;
    
    void a(final p p0) throws RemoteException;
    
    public abstract static class a extends Binder implements o
    {
        public static o C(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.drive.internal.IDriveService");
            if (queryLocalInterface != null && queryLocalInterface instanceof o) {
                return (o)queryLocalInterface;
            }
            return new o.a.a(binder);
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final QueryRequest queryRequest = null;
            final UpdateMetadataRequest updateMetadataRequest = null;
            final CreateContentsRequest createContentsRequest = null;
            final CreateFileRequest createFileRequest = null;
            final CreateFolderRequest createFolderRequest = null;
            final OpenContentsRequest openContentsRequest = null;
            final CloseContentsRequest closeContentsRequest = null;
            final OpenFileIntentSenderRequest openFileIntentSenderRequest = null;
            final CreateFileIntentSenderRequest createFileIntentSenderRequest = null;
            GetMetadataRequest getMetadataRequest = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.drive.internal.IDriveService");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    if (parcel.readInt() != 0) {
                        getMetadataRequest = (GetMetadataRequest)GetMetadataRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(getMetadataRequest, p.a.D(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    QueryRequest queryRequest2 = queryRequest;
                    if (parcel.readInt() != 0) {
                        queryRequest2 = (QueryRequest)QueryRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(queryRequest2, p.a.D(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    UpdateMetadataRequest updateMetadataRequest2 = updateMetadataRequest;
                    if (parcel.readInt() != 0) {
                        updateMetadataRequest2 = (UpdateMetadataRequest)UpdateMetadataRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(updateMetadataRequest2, p.a.D(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    CreateContentsRequest createContentsRequest2 = createContentsRequest;
                    if (parcel.readInt() != 0) {
                        createContentsRequest2 = (CreateContentsRequest)CreateContentsRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(createContentsRequest2, p.a.D(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    CreateFileRequest createFileRequest2 = createFileRequest;
                    if (parcel.readInt() != 0) {
                        createFileRequest2 = (CreateFileRequest)CreateFileRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(createFileRequest2, p.a.D(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    CreateFolderRequest createFolderRequest2 = createFolderRequest;
                    if (parcel.readInt() != 0) {
                        createFolderRequest2 = (CreateFolderRequest)CreateFolderRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(createFolderRequest2, p.a.D(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    OpenContentsRequest openContentsRequest2 = openContentsRequest;
                    if (parcel.readInt() != 0) {
                        openContentsRequest2 = (OpenContentsRequest)OpenContentsRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(openContentsRequest2, p.a.D(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    CloseContentsRequest closeContentsRequest2 = closeContentsRequest;
                    if (parcel.readInt() != 0) {
                        closeContentsRequest2 = (CloseContentsRequest)CloseContentsRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(closeContentsRequest2, p.a.D(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    this.a(p.a.D(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    OpenFileIntentSenderRequest openFileIntentSenderRequest2 = openFileIntentSenderRequest;
                    if (parcel.readInt() != 0) {
                        openFileIntentSenderRequest2 = (OpenFileIntentSenderRequest)OpenFileIntentSenderRequest.CREATOR.createFromParcel(parcel);
                    }
                    final IntentSender a = this.a(openFileIntentSenderRequest2);
                    parcel2.writeNoException();
                    if (a != null) {
                        parcel2.writeInt(1);
                        a.writeToParcel(parcel2, 1);
                    }
                    else {
                        parcel2.writeInt(0);
                    }
                    return true;
                }
                case 11: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    CreateFileIntentSenderRequest createFileIntentSenderRequest2 = createFileIntentSenderRequest;
                    if (parcel.readInt() != 0) {
                        createFileIntentSenderRequest2 = (CreateFileIntentSenderRequest)CreateFileIntentSenderRequest.CREATOR.createFromParcel(parcel);
                    }
                    final IntentSender a2 = this.a(createFileIntentSenderRequest2);
                    parcel2.writeNoException();
                    if (a2 != null) {
                        parcel2.writeInt(1);
                        a2.writeToParcel(parcel2, 1);
                    }
                    else {
                        parcel2.writeInt(0);
                    }
                    return true;
                }
            }
        }
        
        private static class a implements o
        {
            private IBinder dU;
            
            a(final IBinder du) {
                this.dU = du;
            }
            
            @Override
            public IntentSender a(final CreateFileIntentSenderRequest createFileIntentSenderRequest) throws RemoteException {
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
                        this.dU.transact(11, obtain, obtain2, 0);
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
            public IntentSender a(final OpenFileIntentSenderRequest openFileIntentSenderRequest) throws RemoteException {
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
                        this.dU.transact(10, obtain, obtain2, 0);
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
            public void a(final CloseContentsRequest closeContentsRequest, final p p2) throws RemoteException {
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
                            if (p2 != null) {
                                final IBinder binder = p2.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.dU.transact(8, obtain, obtain2, 0);
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
            public void a(final CreateContentsRequest createContentsRequest, final p p2) throws RemoteException {
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
                            if (p2 != null) {
                                final IBinder binder = p2.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.dU.transact(4, obtain, obtain2, 0);
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
            public void a(final CreateFileRequest createFileRequest, final p p2) throws RemoteException {
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
                            if (p2 != null) {
                                final IBinder binder = p2.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.dU.transact(5, obtain, obtain2, 0);
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
            public void a(final CreateFolderRequest createFolderRequest, final p p2) throws RemoteException {
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
                            if (p2 != null) {
                                final IBinder binder = p2.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.dU.transact(6, obtain, obtain2, 0);
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
            public void a(final GetMetadataRequest getMetadataRequest, final p p2) throws RemoteException {
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
                            if (p2 != null) {
                                final IBinder binder = p2.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.dU.transact(1, obtain, obtain2, 0);
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
            public void a(final OpenContentsRequest openContentsRequest, final p p2) throws RemoteException {
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
                            if (p2 != null) {
                                final IBinder binder = p2.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.dU.transact(7, obtain, obtain2, 0);
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
            public void a(final QueryRequest queryRequest, final p p2) throws RemoteException {
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
                            if (p2 != null) {
                                final IBinder binder = p2.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.dU.transact(2, obtain, obtain2, 0);
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
            public void a(final UpdateMetadataRequest updateMetadataRequest, final p p2) throws RemoteException {
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
                            if (p2 != null) {
                                final IBinder binder = p2.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.dU.transact(3, obtain, obtain2, 0);
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
            public void a(final p p) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    IBinder binder;
                    if (p != null) {
                        binder = p.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.dU;
            }
        }
    }
}
