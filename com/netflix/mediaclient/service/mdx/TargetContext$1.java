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
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.RemoteDevice;
import android.os.HandlerThread;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.MdxController;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;

class TargetContext$1 extends Handler
{
    final /* synthetic */ TargetContext this$0;
    
    TargetContext$1(final TargetContext this$0, final Looper looper) {
        this.this$0 = this$0;
        super(looper);
    }
    
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {}
            case 2: {
                if (Log.isLoggable("nf_mdx", 3)) {
                    Log.d("nf_mdx", "TargetContext: received a command at state " + this.this$0.mStateMachine.mCurrentState.getName());
                }
                this.this$0.mStateMachine.addUiCommand((Runnable)message.obj);
                this.this$0.mStateMachine.receivedEvent(TargetStateManager$TargetContextEvent.SessionCommandReceived);
            }
            case 1: {
                this.this$0.mStateMachine.receivedEvent((TargetStateManager$TargetContextEvent)message.obj);
            }
            case 3: {
                final boolean notEmpty = StringUtils.isNotEmpty(this.this$0.mPairingContext);
                Log.d("nf_mdx", "TargetContext: MSG_UPDATETARGET " + notEmpty + ", " + this.this$0.mRegistrationAcceptance + ", " + this.this$0.mActivated + ", " + this.this$0.mLaunchStatus);
                this.this$0.mStateMachine.updateTarget(notEmpty, this.this$0.mRegistrationAcceptance, this.this$0.mActivated, this.this$0.mLaunchStatus);
                this.this$0.mStateMachine.receivedEvent(TargetStateManager$TargetContextEvent.TargetUpdate);
            }
            case 4: {
                if (this.this$0.mStateMachine.isSessionActive()) {
                    this.this$0.requestStateCheck();
                    return;
                }
                Log.d("nf_mdx", "TargetContext: MSG_PERIODIC,target is not active");
            }
        }
    }
}
