// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.registry;

import android.os.StatFs;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineStorageVolumeUiList;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadState;
import com.netflix.mediaclient.service.offline.download.OfflinePlayablePersistentData;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineStorageVolume;
import java.security.NoSuchAlgorithmException;
import com.netflix.mediaclient.util.CryptoUtils;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import com.netflix.mediaclient.Log;
import java.util.Random;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.service.offline.utils.OfflinePathUtils;
import java.io.File;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.OfflineStorageVolumeUiListImpl;
import java.util.List;
import android.content.Context;
import java.util.Enumeration;

public class OfflineRegistry$RegistryEnumerator implements Enumeration
{
    int mCurrentIndex;
    final /* synthetic */ OfflineRegistry this$0;
    
    public OfflineRegistry$RegistryEnumerator(final OfflineRegistry this$0) {
        this.this$0 = this$0;
        this.mCurrentIndex = 0;
    }
    
    @Override
    public boolean hasMoreElements() {
        return this.mCurrentIndex < this.this$0.getOfflineStorageVolumeList().size();
    }
    
    @Override
    public RegistryData nextElement() {
        return this.this$0.getOfflineStorageVolumeList().get(this.mCurrentIndex++).mRegistryData;
    }
}
