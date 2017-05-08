// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.service.mdx.message.controller.PlayerGetCapabilities;
import com.netflix.mediaclient.ui.mdx.MdxTargetCapabilities;
import com.netflix.mediaclient.service.mdx.message.target.PlayerState;
import com.netflix.mediaclient.service.mdx.message.target.PinNotRequired;
import com.netflix.mediaclient.service.mdx.message.target.PinRequired;
import com.netflix.mediaclient.service.mdx.message.target.PlayerStateChanged;
import com.netflix.mediaclient.service.mdx.message.target.PlayerCurrentState;
import org.json.JSONException;
import com.netflix.mediaclient.service.mdx.message.target.HandshakeAccepted;
import org.json.JSONObject;
import java.security.InvalidParameterException;
import com.netflix.mediaclient.util.WebApiUtils;
import com.netflix.mediaclient.util.WebApiUtils$VideoIds;
import android.os.Message;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.RemoteDevice;
import android.os.HandlerThread;
import android.os.Handler;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController;
import java.util.Map;
import com.netflix.mediaclient.util.StringUtils;
import java.util.HashMap;

class TargetContext$LaunchNetflix implements Runnable
{
    final /* synthetic */ TargetContext this$0;
    
    TargetContext$LaunchNetflix(final TargetContext this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.mController.getDiscovery().isRemoteDeviceReady(this.this$0.mDialUsn);
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("intent", "sync");
        if (StringUtils.isNotEmpty(this.this$0.mDialUsn)) {
            this.this$0.mController.getDiscovery().launchNetflix(this.this$0.mDialUsn, hashMap);
            return;
        }
        this.this$0.mController.getDiscovery().launchNetflix(this.this$0.mUsn, hashMap);
    }
}
