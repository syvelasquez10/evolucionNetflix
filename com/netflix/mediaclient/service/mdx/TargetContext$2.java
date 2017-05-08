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
import java.security.InvalidParameterException;
import com.netflix.mediaclient.util.WebApiUtils;
import com.netflix.mediaclient.util.WebApiUtils$VideoIds;
import android.os.Message;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.RemoteDevice;
import android.os.HandlerThread;
import android.os.Handler;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController;
import org.json.JSONObject;

class TargetContext$2 implements Runnable
{
    final /* synthetic */ TargetContext this$0;
    final /* synthetic */ String val$messageName;
    final /* synthetic */ JSONObject val$msgObj;
    
    TargetContext$2(final TargetContext this$0, final String val$messageName, final JSONObject val$msgObj) {
        this.this$0 = this$0;
        this.val$messageName = val$messageName;
        this.val$msgObj = val$msgObj;
    }
    
    @Override
    public void run() {
        this.this$0.mController.getSession().sendMessage(this.this$0.mSessionId, this.val$messageName, this.val$msgObj);
    }
}
