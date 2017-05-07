// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import java.io.InputStream;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;

public interface DataApi$GetFdForAssetResult extends Releasable, Result
{
    ParcelFileDescriptor getFd();
    
    InputStream getInputStream();
}
