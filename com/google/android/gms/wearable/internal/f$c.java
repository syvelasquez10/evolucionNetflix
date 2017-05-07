// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import java.io.IOException;
import android.os.ParcelFileDescriptor$AutoCloseInputStream;
import android.os.ParcelFileDescriptor;
import java.io.InputStream;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.DataApi$GetFdForAssetResult;

public class f$c implements DataApi$GetFdForAssetResult
{
    private final Status CM;
    private volatile InputStream XM;
    private volatile ParcelFileDescriptor avj;
    private volatile boolean mClosed;
    
    public f$c(final Status cm, final ParcelFileDescriptor avj) {
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
