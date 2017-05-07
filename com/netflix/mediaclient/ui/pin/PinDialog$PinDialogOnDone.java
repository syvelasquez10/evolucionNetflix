// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.pin;

import android.app.AlertDialog;
import android.text.method.MovementMethod;
import android.view.View$OnKeyListener;
import android.view.ViewGroup;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.util.TypedValue;
import android.view.WindowManager$LayoutParams;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import android.content.Context;
import com.netflix.mediaclient.service.mdx.MdxAgent$Utils;
import android.view.inputmethod.InputMethodManager;
import android.os.Parcelable;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ProgressBar;
import android.widget.EditText;
import android.widget.ImageView;
import android.annotation.TargetApi;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView$OnEditorActionListener;

class PinDialog$PinDialogOnDone implements TextView$OnEditorActionListener
{
    final /* synthetic */ PinDialog this$0;
    
    private PinDialog$PinDialogOnDone(final PinDialog this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onEditorAction(final TextView textView, final int n, final KeyEvent keyEvent) {
        if (n != 6) {
            return false;
        }
        final String string = textView.getText().toString();
        if (string.length() < PinDialog.PIN_LENGTH) {
            return true;
        }
        if (this.this$0.getServiceManager() == null) {
            Log.d("nf_pin", "serviceManager is null");
            return false;
        }
        this.this$0.processUserInputPin(string);
        return false;
    }
}
