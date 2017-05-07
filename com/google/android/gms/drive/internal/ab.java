// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.RealtimeDocumentSyncRequest;
import android.content.IntentSender;
import android.os.IInterface;

public interface ab extends IInterface
{
    IntentSender a(final CreateFileIntentSenderRequest p0);
    
    IntentSender a(final OpenFileIntentSenderRequest p0);
    
    void a(final RealtimeDocumentSyncRequest p0, final ac p1);
    
    void a(final AddEventListenerRequest p0, final ad p1, final String p2, final ac p3);
    
    void a(final AuthorizeAccessRequest p0, final ac p1);
    
    void a(final CheckResourceIdsExistRequest p0, final ac p1);
    
    void a(final CloseContentsAndUpdateMetadataRequest p0, final ac p1);
    
    void a(final CloseContentsRequest p0, final ac p1);
    
    void a(final CreateContentsRequest p0, final ac p1);
    
    void a(final CreateFileRequest p0, final ac p1);
    
    void a(final CreateFolderRequest p0, final ac p1);
    
    void a(final DeleteResourceRequest p0, final ac p1);
    
    void a(final DisconnectRequest p0);
    
    void a(final GetDriveIdFromUniqueIdentifierRequest p0, final ac p1);
    
    void a(final GetMetadataRequest p0, final ac p1);
    
    void a(final ListParentsRequest p0, final ac p1);
    
    void a(final LoadRealtimeRequest p0, final ac p1);
    
    void a(final OpenContentsRequest p0, final ac p1);
    
    void a(final QueryRequest p0, final ac p1);
    
    void a(final RemoveEventListenerRequest p0, final ad p1, final String p2, final ac p3);
    
    void a(final SetDrivePreferencesRequest p0, final ac p1);
    
    void a(final SetResourceParentsRequest p0, final ac p1);
    
    void a(final TrashResourceRequest p0, final ac p1);
    
    void a(final UpdateMetadataRequest p0, final ac p1);
    
    void a(final ac p0);
    
    void b(final QueryRequest p0, final ac p1);
    
    void b(final ac p0);
    
    void c(final ac p0);
    
    void d(final ac p0);
    
    void e(final ac p0);
    
    void f(final ac p0);
    
    void g(final ac p0);
}
