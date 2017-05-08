// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error.crypto;

import com.netflix.mediaclient.service.error.ErrorDescriptor;
import com.netflix.mediaclient.service.configuration.crypto.CryptoProvider;
import com.netflix.mediaclient.service.configuration.crypto.CryptoManagerRegistry;
import android.app.ActivityManager;
import org.json.JSONArray;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.service.offline.agent.OfflineAgent;
import java.util.Iterator;
import com.netflix.mediaclient.ui.offline.DownloadButton;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;
import android.util.Log;
import com.netflix.mediaclient.StatusCode;
import java.util.ArrayList;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import java.util.concurrent.atomic.AtomicBoolean;
import android.os.Handler;
import java.util.List;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.netflix.mediaclient.servicemgr.IErrorHandler;
import android.content.Context;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;

class CryptoErrorManager$1 implements Runnable
{
    final /* synthetic */ CryptoErrorManager this$0;
    
    CryptoErrorManager$1(final CryptoErrorManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.doRemoveOfflineContent();
    }
}
