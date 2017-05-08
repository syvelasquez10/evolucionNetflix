// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.upstream;

public interface TransferListener
{
    void onBytesTransferred(final int p0);
    
    void onTransferEnd();
    
    void onTransferStart();
}
