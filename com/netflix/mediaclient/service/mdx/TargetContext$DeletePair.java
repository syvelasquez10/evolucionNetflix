// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

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
import com.netflix.mediaclient.service.mdx.message.MdxMessage;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.RemoteDevice;
import android.os.HandlerThread;
import android.os.Handler;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController;
import android.os.Message;

class TargetContext$DeletePair implements Runnable
{
    final /* synthetic */ TargetContext this$0;
    
    TargetContext$DeletePair(final TargetContext this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.mController.getPairing().deletePairing(this.this$0.mPairingContext);
        final Message message = new Message();
        message.what = 1;
        message.obj = TargetStateManager$TargetContextEvent.DeletePairSucceed;
        this.this$0.mTargetContextHandler.sendMessageDelayed(message, 20L);
    }
}
