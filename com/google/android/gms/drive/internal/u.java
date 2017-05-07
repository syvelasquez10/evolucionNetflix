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

public interface u extends IInterface
{
    IntentSender a(final CreateFileIntentSenderRequest p0) throws RemoteException;
    
    IntentSender a(final OpenFileIntentSenderRequest p0) throws RemoteException;
    
    void a(final AddEventListenerRequest p0, final w p1, final String p2, final v p3) throws RemoteException;
    
    void a(final AuthorizeAccessRequest p0, final v p1) throws RemoteException;
    
    void a(final CloseContentsAndUpdateMetadataRequest p0, final v p1) throws RemoteException;
    
    void a(final CloseContentsRequest p0, final v p1) throws RemoteException;
    
    void a(final CreateContentsRequest p0, final v p1) throws RemoteException;
    
    void a(final CreateFileRequest p0, final v p1) throws RemoteException;
    
    void a(final CreateFolderRequest p0, final v p1) throws RemoteException;
    
    void a(final DisconnectRequest p0) throws RemoteException;
    
    void a(final GetMetadataRequest p0, final v p1) throws RemoteException;
    
    void a(final ListParentsRequest p0, final v p1) throws RemoteException;
    
    void a(final OpenContentsRequest p0, final v p1) throws RemoteException;
    
    void a(final QueryRequest p0, final v p1) throws RemoteException;
    
    void a(final RemoveEventListenerRequest p0, final w p1, final String p2, final v p3) throws RemoteException;
    
    void a(final TrashResourceRequest p0, final v p1) throws RemoteException;
    
    void a(final UpdateMetadataRequest p0, final v p1) throws RemoteException;
    
    void a(final v p0) throws RemoteException;
    
    void b(final QueryRequest p0, final v p1) throws RemoteException;
    
    public abstract static class a extends Binder implements u
    {
        public static u G(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.drive.internal.IDriveService");
            if (queryLocalInterface != null && queryLocalInterface instanceof u) {
                return (u)queryLocalInterface;
            }
            return new u.a.a(binder);
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
            final AuthorizeAccessRequest authorizeAccessRequest = null;
            final ListParentsRequest listParentsRequest = null;
            final AddEventListenerRequest addEventListenerRequest = null;
            final RemoveEventListenerRequest removeEventListenerRequest = null;
            final DisconnectRequest disconnectRequest = null;
            final TrashResourceRequest trashResourceRequest = null;
            final CloseContentsAndUpdateMetadataRequest closeContentsAndUpdateMetadataRequest = null;
            final QueryRequest queryRequest2 = null;
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
                    this.a(getMetadataRequest, v.a.H(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    QueryRequest queryRequest3 = queryRequest;
                    if (parcel.readInt() != 0) {
                        queryRequest3 = (QueryRequest)QueryRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(queryRequest3, v.a.H(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    UpdateMetadataRequest updateMetadataRequest2 = updateMetadataRequest;
                    if (parcel.readInt() != 0) {
                        updateMetadataRequest2 = (UpdateMetadataRequest)UpdateMetadataRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(updateMetadataRequest2, v.a.H(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    CreateContentsRequest createContentsRequest2 = createContentsRequest;
                    if (parcel.readInt() != 0) {
                        createContentsRequest2 = (CreateContentsRequest)CreateContentsRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(createContentsRequest2, v.a.H(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    CreateFileRequest createFileRequest2 = createFileRequest;
                    if (parcel.readInt() != 0) {
                        createFileRequest2 = (CreateFileRequest)CreateFileRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(createFileRequest2, v.a.H(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    CreateFolderRequest createFolderRequest2 = createFolderRequest;
                    if (parcel.readInt() != 0) {
                        createFolderRequest2 = (CreateFolderRequest)CreateFolderRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(createFolderRequest2, v.a.H(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    OpenContentsRequest openContentsRequest2 = openContentsRequest;
                    if (parcel.readInt() != 0) {
                        openContentsRequest2 = (OpenContentsRequest)OpenContentsRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(openContentsRequest2, v.a.H(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    CloseContentsRequest closeContentsRequest2 = closeContentsRequest;
                    if (parcel.readInt() != 0) {
                        closeContentsRequest2 = (CloseContentsRequest)CloseContentsRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(closeContentsRequest2, v.a.H(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    this.a(v.a.H(parcel.readStrongBinder()));
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
                case 12: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    AuthorizeAccessRequest authorizeAccessRequest2 = authorizeAccessRequest;
                    if (parcel.readInt() != 0) {
                        authorizeAccessRequest2 = (AuthorizeAccessRequest)AuthorizeAccessRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(authorizeAccessRequest2, v.a.H(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 13: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    ListParentsRequest listParentsRequest2 = listParentsRequest;
                    if (parcel.readInt() != 0) {
                        listParentsRequest2 = (ListParentsRequest)ListParentsRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(listParentsRequest2, v.a.H(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 14: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    AddEventListenerRequest addEventListenerRequest2 = addEventListenerRequest;
                    if (parcel.readInt() != 0) {
                        addEventListenerRequest2 = (AddEventListenerRequest)AddEventListenerRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(addEventListenerRequest2, w.a.I(parcel.readStrongBinder()), parcel.readString(), v.a.H(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 15: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    RemoveEventListenerRequest removeEventListenerRequest2 = removeEventListenerRequest;
                    if (parcel.readInt() != 0) {
                        removeEventListenerRequest2 = (RemoveEventListenerRequest)RemoveEventListenerRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(removeEventListenerRequest2, w.a.I(parcel.readStrongBinder()), parcel.readString(), v.a.H(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 16: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    DisconnectRequest disconnectRequest2 = disconnectRequest;
                    if (parcel.readInt() != 0) {
                        disconnectRequest2 = (DisconnectRequest)DisconnectRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(disconnectRequest2);
                    parcel2.writeNoException();
                    return true;
                }
                case 17: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    TrashResourceRequest trashResourceRequest2 = trashResourceRequest;
                    if (parcel.readInt() != 0) {
                        trashResourceRequest2 = (TrashResourceRequest)TrashResourceRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(trashResourceRequest2, v.a.H(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 18: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    CloseContentsAndUpdateMetadataRequest closeContentsAndUpdateMetadataRequest2 = closeContentsAndUpdateMetadataRequest;
                    if (parcel.readInt() != 0) {
                        closeContentsAndUpdateMetadataRequest2 = (CloseContentsAndUpdateMetadataRequest)CloseContentsAndUpdateMetadataRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.a(closeContentsAndUpdateMetadataRequest2, v.a.H(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 19: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                    QueryRequest queryRequest4 = queryRequest2;
                    if (parcel.readInt() != 0) {
                        queryRequest4 = (QueryRequest)QueryRequest.CREATOR.createFromParcel(parcel);
                    }
                    this.b(queryRequest4, v.a.H(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements u
        {
            private IBinder kn;
            
            a(final IBinder kn) {
                this.kn = kn;
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
                        this.kn.transact(11, obtain, obtain2, 0);
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
                        this.kn.transact(10, obtain, obtain2, 0);
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
            public void a(final AddEventListenerRequest addEventListenerRequest, final w w, final String s, final v v) throws RemoteException {
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
                            if (w != null) {
                                final IBinder binder2 = w.asBinder();
                                obtain.writeStrongBinder(binder2);
                                obtain.writeString(s);
                                IBinder binder3 = binder;
                                if (v != null) {
                                    binder3 = v.asBinder();
                                }
                                obtain.writeStrongBinder(binder3);
                                this.kn.transact(14, obtain, obtain2, 0);
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
            public void a(final AuthorizeAccessRequest authorizeAccessRequest, final v v) throws RemoteException {
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
                            if (v != null) {
                                final IBinder binder = v.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.kn.transact(12, obtain, obtain2, 0);
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
            public void a(final CloseContentsAndUpdateMetadataRequest closeContentsAndUpdateMetadataRequest, final v v) throws RemoteException {
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
                            if (v != null) {
                                final IBinder binder = v.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.kn.transact(18, obtain, obtain2, 0);
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
            public void a(final CloseContentsRequest closeContentsRequest, final v v) throws RemoteException {
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
                            if (v != null) {
                                final IBinder binder = v.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.kn.transact(8, obtain, obtain2, 0);
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
            public void a(final CreateContentsRequest createContentsRequest, final v v) throws RemoteException {
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
                            if (v != null) {
                                final IBinder binder = v.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.kn.transact(4, obtain, obtain2, 0);
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
            public void a(final CreateFileRequest createFileRequest, final v v) throws RemoteException {
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
                            if (v != null) {
                                final IBinder binder = v.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.kn.transact(5, obtain, obtain2, 0);
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
            public void a(final CreateFolderRequest createFolderRequest, final v v) throws RemoteException {
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
                            if (v != null) {
                                final IBinder binder = v.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.kn.transact(6, obtain, obtain2, 0);
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
            public void a(final DisconnectRequest disconnectRequest) throws RemoteException {
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
                    this.kn.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final GetMetadataRequest getMetadataRequest, final v v) throws RemoteException {
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
                            if (v != null) {
                                final IBinder binder = v.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.kn.transact(1, obtain, obtain2, 0);
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
            public void a(final ListParentsRequest listParentsRequest, final v v) throws RemoteException {
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
                            if (v != null) {
                                final IBinder binder = v.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.kn.transact(13, obtain, obtain2, 0);
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
            public void a(final OpenContentsRequest openContentsRequest, final v v) throws RemoteException {
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
                            if (v != null) {
                                final IBinder binder = v.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.kn.transact(7, obtain, obtain2, 0);
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
            public void a(final QueryRequest queryRequest, final v v) throws RemoteException {
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
                            if (v != null) {
                                final IBinder binder = v.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.kn.transact(2, obtain, obtain2, 0);
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
            public void a(final RemoveEventListenerRequest removeEventListenerRequest, final w w, final String s, final v v) throws RemoteException {
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
                            if (w != null) {
                                final IBinder binder2 = w.asBinder();
                                obtain.writeStrongBinder(binder2);
                                obtain.writeString(s);
                                IBinder binder3 = binder;
                                if (v != null) {
                                    binder3 = v.asBinder();
                                }
                                obtain.writeStrongBinder(binder3);
                                this.kn.transact(15, obtain, obtain2, 0);
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
            public void a(final TrashResourceRequest trashResourceRequest, final v v) throws RemoteException {
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
                            if (v != null) {
                                final IBinder binder = v.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.kn.transact(17, obtain, obtain2, 0);
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
            public void a(final UpdateMetadataRequest updateMetadataRequest, final v v) throws RemoteException {
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
                            if (v != null) {
                                final IBinder binder = v.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.kn.transact(3, obtain, obtain2, 0);
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
            public void a(final v v) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    IBinder binder;
                    if (v != null) {
                        binder = v.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.kn.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.kn;
            }
            
            @Override
            public void b(final QueryRequest queryRequest, final v v) throws RemoteException {
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
                            if (v != null) {
                                final IBinder binder = v.asBinder();
                                obtain.writeStrongBinder(binder);
                                this.kn.transact(19, obtain, obtain2, 0);
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
        }
    }
}
