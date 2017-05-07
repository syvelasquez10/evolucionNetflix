// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.content.IntentSender;
import com.google.android.gms.drive.RealtimeDocumentSyncRequest;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class ab$a extends Binder implements ab
{
    public static ab U(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.drive.internal.IDriveService");
        if (queryLocalInterface != null && queryLocalInterface instanceof ab) {
            return (ab)queryLocalInterface;
        }
        return new ab$a$a(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
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
        final DeleteResourceRequest deleteResourceRequest = null;
        final LoadRealtimeRequest loadRealtimeRequest = null;
        final SetResourceParentsRequest setResourceParentsRequest = null;
        final GetDriveIdFromUniqueIdentifierRequest getDriveIdFromUniqueIdentifierRequest = null;
        final CheckResourceIdsExistRequest checkResourceIdsExistRequest = null;
        final SetDrivePreferencesRequest setDrivePreferencesRequest = null;
        final RealtimeDocumentSyncRequest realtimeDocumentSyncRequest = null;
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
                this.a(getMetadataRequest, ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                QueryRequest queryRequest3 = queryRequest;
                if (parcel.readInt() != 0) {
                    queryRequest3 = (QueryRequest)QueryRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(queryRequest3, ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                UpdateMetadataRequest updateMetadataRequest2 = updateMetadataRequest;
                if (parcel.readInt() != 0) {
                    updateMetadataRequest2 = (UpdateMetadataRequest)UpdateMetadataRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(updateMetadataRequest2, ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                CreateContentsRequest createContentsRequest2 = createContentsRequest;
                if (parcel.readInt() != 0) {
                    createContentsRequest2 = (CreateContentsRequest)CreateContentsRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(createContentsRequest2, ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                CreateFileRequest createFileRequest2 = createFileRequest;
                if (parcel.readInt() != 0) {
                    createFileRequest2 = (CreateFileRequest)CreateFileRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(createFileRequest2, ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                CreateFolderRequest createFolderRequest2 = createFolderRequest;
                if (parcel.readInt() != 0) {
                    createFolderRequest2 = (CreateFolderRequest)CreateFolderRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(createFolderRequest2, ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                OpenContentsRequest openContentsRequest2 = openContentsRequest;
                if (parcel.readInt() != 0) {
                    openContentsRequest2 = (OpenContentsRequest)OpenContentsRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(openContentsRequest2, ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                CloseContentsRequest closeContentsRequest2 = closeContentsRequest;
                if (parcel.readInt() != 0) {
                    closeContentsRequest2 = (CloseContentsRequest)CloseContentsRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(closeContentsRequest2, ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                this.a(ac$a.V(parcel.readStrongBinder()));
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
                this.a(authorizeAccessRequest2, ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 13: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                ListParentsRequest listParentsRequest2 = listParentsRequest;
                if (parcel.readInt() != 0) {
                    listParentsRequest2 = (ListParentsRequest)ListParentsRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(listParentsRequest2, ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 14: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                AddEventListenerRequest addEventListenerRequest2 = addEventListenerRequest;
                if (parcel.readInt() != 0) {
                    addEventListenerRequest2 = (AddEventListenerRequest)AddEventListenerRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(addEventListenerRequest2, ad$a.W(parcel.readStrongBinder()), parcel.readString(), ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 15: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                RemoveEventListenerRequest removeEventListenerRequest2 = removeEventListenerRequest;
                if (parcel.readInt() != 0) {
                    removeEventListenerRequest2 = (RemoveEventListenerRequest)RemoveEventListenerRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(removeEventListenerRequest2, ad$a.W(parcel.readStrongBinder()), parcel.readString(), ac$a.V(parcel.readStrongBinder()));
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
                this.a(trashResourceRequest2, ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 18: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                CloseContentsAndUpdateMetadataRequest closeContentsAndUpdateMetadataRequest2 = closeContentsAndUpdateMetadataRequest;
                if (parcel.readInt() != 0) {
                    closeContentsAndUpdateMetadataRequest2 = (CloseContentsAndUpdateMetadataRequest)CloseContentsAndUpdateMetadataRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(closeContentsAndUpdateMetadataRequest2, ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 19: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                QueryRequest queryRequest4 = queryRequest2;
                if (parcel.readInt() != 0) {
                    queryRequest4 = (QueryRequest)QueryRequest.CREATOR.createFromParcel(parcel);
                }
                this.b(queryRequest4, ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 20: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                this.b(ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 21: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                this.c(ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 22: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                this.d(ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 23: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                this.e(ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 24: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                DeleteResourceRequest deleteResourceRequest2 = deleteResourceRequest;
                if (parcel.readInt() != 0) {
                    deleteResourceRequest2 = (DeleteResourceRequest)DeleteResourceRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(deleteResourceRequest2, ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 27: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                LoadRealtimeRequest loadRealtimeRequest2 = loadRealtimeRequest;
                if (parcel.readInt() != 0) {
                    loadRealtimeRequest2 = (LoadRealtimeRequest)LoadRealtimeRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(loadRealtimeRequest2, ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 28: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                SetResourceParentsRequest setResourceParentsRequest2 = setResourceParentsRequest;
                if (parcel.readInt() != 0) {
                    setResourceParentsRequest2 = (SetResourceParentsRequest)SetResourceParentsRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(setResourceParentsRequest2, ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 29: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                GetDriveIdFromUniqueIdentifierRequest getDriveIdFromUniqueIdentifierRequest2 = getDriveIdFromUniqueIdentifierRequest;
                if (parcel.readInt() != 0) {
                    getDriveIdFromUniqueIdentifierRequest2 = (GetDriveIdFromUniqueIdentifierRequest)GetDriveIdFromUniqueIdentifierRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(getDriveIdFromUniqueIdentifierRequest2, ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 30: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                CheckResourceIdsExistRequest checkResourceIdsExistRequest2 = checkResourceIdsExistRequest;
                if (parcel.readInt() != 0) {
                    checkResourceIdsExistRequest2 = (CheckResourceIdsExistRequest)CheckResourceIdsExistRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(checkResourceIdsExistRequest2, ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 31: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                this.f(ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 32: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                this.g(ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 33: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                SetDrivePreferencesRequest setDrivePreferencesRequest2 = setDrivePreferencesRequest;
                if (parcel.readInt() != 0) {
                    setDrivePreferencesRequest2 = (SetDrivePreferencesRequest)SetDrivePreferencesRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(setDrivePreferencesRequest2, ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 34: {
                parcel.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
                RealtimeDocumentSyncRequest realtimeDocumentSyncRequest2 = realtimeDocumentSyncRequest;
                if (parcel.readInt() != 0) {
                    realtimeDocumentSyncRequest2 = (RealtimeDocumentSyncRequest)RealtimeDocumentSyncRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(realtimeDocumentSyncRequest2, ac$a.V(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
