// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.os.Process;
import com.netflix.mediaclient.event.nrdp.media.NccpError;
import android.media.AudioManager;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import java.util.List;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.android.widget.UpdateDialog;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.event.nrdp.media.NccpNetworkingError;
import com.netflix.mediaclient.event.android.NetworkError;
import com.netflix.mediaclient.event.UIEvent;
import com.netflix.mediaclient.event.nrdp.device.ReasonCode;
import org.json.JSONObject;
import android.net.Uri;
import org.json.JSONException;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import com.netflix.mediaclient.ui.login.LogoutActivity;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.service.NetflixService;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.nrdp.media.MediaEvent;
import com.netflix.mediaclient.service.ServiceAgent;
import android.os.Handler;
import java.util.Locale;

public class ErrorManager
{
    public static final String[] ACTION_IDS;
    public static final String MEDIA_ERR_DECODE = "MEDIA_ERR_DECODE";
    public static final String MEDIA_ERR_NETWORK = "MEDIA_ERR_NETWORK";
    public static final String MEDIA_ERR_SRC_NOT_SUPPORTED = "MEDIA_ERR_SRC_NOT_SUPPORTED";
    private static final long PLAYREADY_CERT_REVOKED = 113L;
    private static final String TAG = "ErrorManager";
    private static final Locale US_LOCALE;
    private PlayerActivity context;
    private boolean destroyed;
    private boolean errorPosted;
    private Runnable exit;
    private final Runnable forceExit;
    private Handler handler;
    private ServiceAgent.ConfigurationAgentInterface mConfig;
    private MediaEvent reportedError;
    private Runnable resetApp;
    private Runnable restartApp;
    private final Runnable unregister;
    
    static {
        US_LOCALE = Locale.US;
        ACTION_IDS = new String[] { "NFErr_MC_NCCP_NonRecoverableError", "NFErr_MC_NCCP_PotentiallyRecoverableError", "NFErr_MC_NCCP_CustomError", "NFErr_MC_NCCP_RegistrationRequired", "NFErr_MC_NCCP_CTicketRenewalRequired", "NFErr_MC_NCCP_MTicketRenewalRequired", "NFErr_MC_NCCP_ImpossibleImpossibility", "NFErr_MC_NCCP_GetNewCredentials", "NFErr_MC_NCCP_UnsupportedVersion", "NFErr_MC_NCCP_SecondaryCredentialsRenewalRequired", "NFErr_MC_NCCP_AbortPlayback", "NFErr_MC_NCCP_StaleCredentials" };
    }
    
    public ErrorManager(final Handler handler, final PlayerActivity context, final ServiceAgent.ConfigurationAgentInterface mConfig) {
        this.errorPosted = false;
        this.destroyed = false;
        this.exit = new Runnable() {
            @Override
            public void run() {
                Log.e("ErrorManager", "Exit");
                ErrorManager.this.context.finish();
            }
        };
        this.restartApp = new Runnable() {
            @Override
            public void run() {
                Log.e("ErrorManager", "restartApp");
                if (ErrorManager.this.context instanceof NetflixActivity) {
                    NetflixActivity.finishAllActivities((Context)ErrorManager.this.context);
                }
                final Intent intent = new Intent();
                intent.setClass((Context)ErrorManager.this.context, (Class)NetflixService.class);
                ErrorManager.this.context.stopService(intent);
            }
        };
        this.resetApp = new Runnable() {
            @Override
            public void run() {
                AndroidUtils.clearApplicationData((Context)ErrorManager.this.context);
                Log.e("ErrorManager", "resetApp");
                if (ErrorManager.this.context instanceof NetflixActivity) {
                    NetflixActivity.finishAllActivities((Context)ErrorManager.this.context);
                }
                final Intent intent = new Intent();
                intent.setClass((Context)ErrorManager.this.context, (Class)NetflixService.class);
                ErrorManager.this.context.stopService(intent);
            }
        };
        this.unregister = new Runnable() {
            @Override
            public void run() {
                ErrorManager.this.context.startActivity(LogoutActivity.create((Context)ErrorManager.this.context));
                ErrorManager.this.context.finish();
            }
        };
        this.forceExit = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Log.d("ErrorManager", "Waiting 1.5 second to exit app");
                        this.wait(1500L);
                        Log.d("ErrorManager", "Kill app");
                        ErrorManager.this.forceStop();
                    }
                    catch (Exception ex) {
                        Log.w("ErrorManager", "Wait is interrupted", ex);
                        continue;
                    }
                    break;
                }
            }
        };
        this.handler = handler;
        this.context = context;
        this.mConfig = mConfig;
    }
    
    private LinkTag extractLink(final String s, final StringBuilder sb) {
        if (Log.isLoggable("ErrorManager", 3)) {
            Log.d("ErrorManager", "Trimmed message: " + s);
        }
        final String lowerCase = s.toLowerCase(ErrorManager.US_LOCALE);
        if (Log.isLoggable("ErrorManager", 3)) {
            Log.d("ErrorManager", "Lower case test message: " + lowerCase);
        }
        final int index = lowerCase.indexOf("<a href=\"");
        LinkTag linkTag;
        if (index < 0) {
            Log.d("ErrorManager", "Not a concurrent stream upgrade message");
            linkTag = null;
        }
        else {
            final int index2 = lowerCase.indexOf("\"", index + 10);
            if (index2 < 0) {
                Log.d("ErrorManager", "Not a concurrent stream upgrade message");
                return null;
            }
            final LinkTag linkTag2 = new LinkTag();
            linkTag2.href = s.substring(index + 9, index2);
            final int index3 = lowerCase.indexOf(">", index2);
            final String substring = lowerCase.substring(index3 + 1);
            if (Log.isLoggable("ErrorManager", 3)) {
                Log.d("ErrorManager", "HREF extracted: " + linkTag2.href);
                Log.d("ErrorManager", "Rest of message: " + substring);
            }
            final int index4 = lowerCase.indexOf("</a>");
            if (index > index4) {
                Log.e("ErrorManager", "Closing </a> found before <a>!" + index4 + " < " + index);
                return null;
            }
            linkTag2.text = s.substring(index3 + 1, index4);
            String substring2;
            if (index4 + 5 >= s.length()) {
                substring2 = "";
            }
            else {
                substring2 = s.substring(index4 + 4);
            }
            if (Log.isLoggable("ErrorManager", 3)) {
                Log.d("ErrorManager", "TEXT extracted: " + linkTag2.text);
                Log.d("ErrorManager", "Rest of message: " + substring2);
            }
            sb.append(s.substring(0, index));
            if (Log.isLoggable("ErrorManager", 3)) {
                Log.d("ErrorManager", "User message (part before link): " + sb.toString());
            }
            sb.append(" ").append(substring2);
            linkTag = linkTag2;
            if (Log.isLoggable("ErrorManager", 3)) {
                Log.d("ErrorManager", "User message (part after link): " + sb.toString());
                return linkTag2;
            }
        }
        return linkTag;
    }
    
    private AlertDialogFactory.AlertDialogDescriptor getErrorDescriptorForActionId1(final NccpActionId nccpActionId, final String s) {
        Log.d("ErrorManager", "actionID 1 NFErr_MC_NCCP_NonRecoverableError");
        return new AlertDialogFactory.AlertDialogDescriptor(s, this.context.getString(2131296362), null, this.exit);
    }
    
    private AlertDialogFactory.AlertDialogDescriptor getErrorDescriptorForActionId10(final NccpActionId nccpActionId, final String s) {
        Log.w("ErrorManager", "ActionID 10 NFErr_MC_NCCP_CustomerCredentialsRenewalRequired");
        return new AlertDialogFactory.AlertDialogDescriptor(s, this.context.getString(2131296368), null, this.unregister);
    }
    
    private AlertDialogFactory.AlertDialogDescriptor getErrorDescriptorForActionId11(final NccpActionId nccpActionId, final String s) {
        Log.w("ErrorManager", "ActionID 11 ");
        final String message = nccpActionId.getMessage();
        if (message != null) {
            final String string = message;
            if (!"".equals(message.trim())) {
                return new AlertDialogFactory.AlertDialogDescriptor(s, string, null, this.exit);
            }
        }
        final String string = this.context.getString(2131296369);
        return new AlertDialogFactory.AlertDialogDescriptor(s, string, null, this.exit);
    }
    
    private AlertDialogFactory.AlertDialogDescriptor getErrorDescriptorForActionId12(final NccpActionId nccpActionId, final String s) {
        return new AlertDialogFactory.AlertDialogDescriptor(s, this.context.getString(2131296370), null, this.exit);
    }
    
    private AlertDialogFactory.AlertDialogDescriptor getErrorDescriptorForActionId2(final NccpActionId nccpActionId, final String s) {
        Log.w("ErrorManager", "ActionID 2 NFErr_MC_NCCP_PotentiallyRecoverableError");
        return new AlertDialogFactory.AlertDialogDescriptor(s, this.context.getString(2131296363), null, this.exit);
    }
    
    private AlertDialogFactory.AlertDialogDescriptor getErrorDescriptorForActionId3(final NccpActionId nccpActionId, final String s) {
        final String message = nccpActionId.getMessage();
        final Runnable exit = this.exit;
        String string;
        if (message == null) {
            string = this.context.getString(2131296364);
            Log.e("ErrorManager", "ActionID 3 NFErr_MC_NCCP_CustomError: Error message expected, but not received, displaying generic error");
        }
        else {
            if (Log.isLoggable("ErrorManager", 6)) {
                Log.e("ErrorManager", "ActionID 3 NFErr_MC_NCCP_CustomError: " + message);
            }
            final AlertDialogFactory.AlertDialogDescriptor errorDescriptorForConcurrentStreamUpgrade = this.getErrorDescriptorForConcurrentStreamUpgrade(nccpActionId, s);
            string = message;
            if (errorDescriptorForConcurrentStreamUpgrade != null) {
                Log.d("ErrorManager", "ActionID 3: Concurrent stream upgrade message");
                return errorDescriptorForConcurrentStreamUpgrade;
            }
        }
        return new AlertDialogFactory.AlertDialogDescriptor(s, string, null, exit);
    }
    
    private AlertDialogFactory.AlertDialogDescriptor getErrorDescriptorForActionId4(final NccpActionId nccpActionId, final String s) {
        Log.w("ErrorManager", "ActionID 4 NFErr_MC_NCCP_RegistrationRequired");
        return new AlertDialogFactory.AlertDialogDescriptor(s, this.context.getString(2131296463), null, this.unregister);
    }
    
    private AlertDialogFactory.AlertDialogDescriptor getErrorDescriptorForActionId5(final NccpActionId nccpActionId, final String s) {
        Log.w("ErrorManager", "ActionID 5 NFErr_MC_NCCP_CTicketRenewalRequired, AUTHENTICATION_RENEW_REQUIRE");
        return new AlertDialogFactory.AlertDialogDescriptor(s, this.context.getString(2131296365), null, this.unregister);
    }
    
    private AlertDialogFactory.AlertDialogDescriptor getErrorDescriptorForActionId6(final NccpActionId nccpActionId, final String s) {
        Log.w("ErrorManager", "ActionID 6 NFErr_MC_NCCP_MTicketRenewalRequired, AUTHORIZATION_RENEW_REQUIRED");
        return new AlertDialogFactory.AlertDialogDescriptor(s, this.context.getString(2131296465), null, this.unregister);
    }
    
    private AlertDialogFactory.AlertDialogDescriptor getErrorDescriptorForActionId7(final NccpActionId nccpActionId, final String s) {
        Log.w("ErrorManager", "ActionID 7 NFErr_MC_NCCP_ImpossibleImpossibility, logout");
        return new AlertDialogFactory.AlertDialogDescriptor(s, this.context.getString(2131296466), null, this.unregister);
    }
    
    private AlertDialogFactory.AlertDialogDescriptor getErrorDescriptorForActionId8(final NccpActionId nccpActionId, final String s) {
        Log.w("ErrorManager", "ActionID 8 NFErr_MC_NCCP_GetNewCredentials");
        return new AlertDialogFactory.AlertDialogDescriptor(s, this.context.getString(2131296366), null, this.unregister);
    }
    
    private AlertDialogFactory.AlertDialogDescriptor getErrorDescriptorForActionId9(final NccpActionId nccpActionId, final String s) {
        final String message = nccpActionId.getMessage();
        String string;
        if (message == null) {
            string = this.context.getString(2131296367);
            Log.e("ErrorManager", "ActionID 9 NFErr_MC_NCCP_UnsupportedVersion: force exit app, generic message");
        }
        else {
            string = message;
            if (Log.isLoggable("ErrorManager", 6)) {
                Log.e("ErrorManager", "ActionID 9 NFErr_MC_NCCP_UnsupportedVersion: force exit app, with custom message: " + message);
                string = message;
            }
        }
        return new AlertDialogFactory.AlertDialogDescriptor(s, string, null, this.forceExit);
    }
    
    private AlertDialogFactory.AlertDialogDescriptor getErrorDescriptorForConcurrentStreamUpgrade(final NccpActionId nccpActionId, final String s) {
        if (Log.isLoggable("ErrorManager", 3)) {
            Log.d("ErrorManager", "Original message: " + nccpActionId.getMessage());
        }
        if (nccpActionId.getReasonCode() != 102) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        final String trimWhiteSpace = StringUtils.trimWhiteSpace(nccpActionId.getMessage());
        final LinkTag link = this.extractLink(trimWhiteSpace, sb);
        if (link == null) {
            this.context.setMaxStreamsReachedDialogId(this.context.reportUiModelessViewSessionStart(IClientLogging.ModalView.maxStreamsReached));
            return new AlertDialogFactory.AlertDialogDescriptor(s, trimWhiteSpace, null, this.exit);
        }
        if (Log.isLoggable("ErrorManager", 3)) {
            Log.d("ErrorManager", "Link found: href=" + link.href + ", text=" + link.text);
        }
        final String text = link.text;
        String string = null;
        Label_0200: {
            if (text != null) {
                string = text;
                if (!"".equals(text.trim())) {
                    break Label_0200;
                }
            }
            string = this.context.getString(2131296481);
        }
        final String string2 = this.context.getString(2131296482);
        Log.d("ErrorManager", "Check if link contains NCCP reason code");
        if (link.href.startsWith("RC:")) {
            Log.d("ErrorManager", "NCCP reason code found");
            try {
                return this.getErrorDescriptorForConcurrentStreamUpgradeWithNccpReasonCode(link, s, sb.toString(), string, string2);
            }
            catch (JSONException ex) {
                Log.e("ErrorManager", "Failed to process upgrade with NCCP reason code, return generic action id 3 message", (Throwable)ex);
                return null;
            }
        }
        Log.d("ErrorManager", "NCCP reason code NOT found, treat it as path of URL");
        this.context.setMaxStreamsReachedDialogId(this.context.reportUiModelessViewSessionStart(IClientLogging.ModalView.maxStreamsReached));
        return this.getErrorDescriptorForConcurrentStreamUpgradeRegular(link, s, sb.toString(), string, string2);
    }
    
    private AlertDialogFactory.AlertDialogDescriptor getErrorDescriptorForConcurrentStreamUpgradeRegular(final LinkTag linkTag, final String s, final String s2, final String s3, final String s4) {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String s;
                if (linkTag.href.toLowerCase(ErrorManager.US_LOCALE).trim().startsWith("http")) {
                    s = linkTag.href;
                }
                else {
                    s = "http://www.netflix.com" + "/" + linkTag.href;
                }
                Log.d("ErrorManager", "Launch browser");
                ErrorManager.this.context.runOnUiThread((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (Log.isLoggable("ErrorManager", 3)) {
                            Log.d("ErrorManager", "Open browser to " + s);
                        }
                        ErrorManager.this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(s)));
                        Log.d("ErrorManager", "Exit from playback after browser is started");
                        ErrorManager.this.context.finish();
                    }
                });
            }
        };
        this.context.setMaxStreamsReachedDialogId(this.context.reportUiModelessViewSessionStart(IClientLogging.ModalView.maxStreamsReached));
        return new AlertDialogFactory.TwoButtonAlertDialogDescriptor(s, s2, s3, runnable, s4, this.exit);
    }
    
    private AlertDialogFactory.AlertDialogDescriptor getErrorDescriptorForConcurrentStreamUpgradeWithNccpReasonCode(final LinkTag linkTag, final String s, final String s2, final String s3, final String s4) throws JSONException {
        if (Log.isLoggable("ErrorManager", 3)) {
            Log.d("ErrorManager", "Link found: href=" + linkTag.href + ", text=" + linkTag.text);
        }
        final String[] tokens = StringUtils.extractTokens(linkTag.href, ";");
        final JSONObject jsonObject = new JSONObject();
        if (tokens.length != 2) {
            Log.e("ErrorManager", "Problem with RC format! ; is missing!");
            return null;
        }
        if (Log.isLoggable("ErrorManager", 3)) {
            Log.d("ErrorManager", "HREF token: " + tokens[0] + ", " + tokens[1]);
        }
        final String[] tokens2 = StringUtils.extractTokens(tokens[0], ":");
        if (tokens2.length != 2) {
            Log.e("ErrorManager", "Problem with RC format!");
            return null;
        }
        if (!"RC".equalsIgnoreCase(tokens2[0])) {
            Log.e("ErrorManager", "RC is NOT first element, but: " + tokens2[0]);
            return null;
        }
        jsonObject.put("RC", (Object)tokens2[1]);
        final String[] tokens3 = StringUtils.extractTokens(tokens[1], "&");
        if (Log.isLoggable("ErrorManager", 3)) {
            Log.d("ErrorManager", "# of parameters found in NCCP reason code: " + tokens3.length);
        }
        for (int i = 0; i < tokens3.length; ++i) {
            final String[] tokens4 = StringUtils.extractTokens(tokens3[i], "=");
            if (tokens4.length == 2) {
                jsonObject.put(tokens4[0], (Object)tokens4[1]);
            }
            else if (Log.isLoggable("ErrorManager", 5)) {
                Log.w("ErrorManager", "Parameter " + i + " does not have proper format: " + tokens3[i] + ". Skipping.");
            }
        }
        if (Log.isLoggable("ErrorManager", 3)) {
            Log.d("ErrorManager", "JSON: " + jsonObject);
        }
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d("ErrorManager", "Publish NCCP reason code event to UI");
                ErrorManager.this.context.getNetflixApplication().publishEvent(new ReasonCode(jsonObject));
                Log.d("ErrorManager", "Exit from playback after UI is alerted to handle");
                ErrorManager.this.context.finish();
            }
        };
        this.context.setMaxStreamsReachedDialogId(this.context.reportUiModelessViewSessionStart(IClientLogging.ModalView.maxStreamsReached));
        return new AlertDialogFactory.TwoButtonAlertDialogDescriptor(s, s2, s3, runnable, s4, this.exit);
    }
    
    private AlertDialogFactory.AlertDialogDescriptor getHandler(final MediaEvent mediaEvent) {
        if (mediaEvent instanceof NccpActionId) {
            return this.getHandlerForActionIdError((NccpActionId)mediaEvent);
        }
        if (mediaEvent instanceof NetworkError) {
            return this.getHandlerForNetworkError((NetworkError)mediaEvent);
        }
        if (mediaEvent instanceof NccpNetworkingError) {
            return this.getHandlerForNetworkingError((NccpNetworkingError)mediaEvent);
        }
        if (mediaEvent instanceof Error) {
            return this.getHandlerForMediaError((Error)mediaEvent);
        }
        Log.e("ErrorManager", "Ukwnown NCCP error, display generic error!");
        return this.getUknownErrorDescriptor(mediaEvent, "");
    }
    
    private AlertDialogFactory.AlertDialogDescriptor getHandlerForActionIdError(final NccpActionId nccpActionId) {
        Log.d("ErrorManager", "NccpError " + nccpActionId);
        switch (nccpActionId.getActionId()) {
            default: {
                Log.w("ErrorManager", "default, try to find correct error");
                return this.getUknownErrorDescriptor(nccpActionId, "");
            }
            case 1: {
                return this.getErrorDescriptorForActionId1(nccpActionId, "");
            }
            case 2: {
                return this.getErrorDescriptorForActionId2(nccpActionId, "");
            }
            case 3: {
                return this.getErrorDescriptorForActionId3(nccpActionId, "");
            }
            case 4: {
                return this.getErrorDescriptorForActionId4(nccpActionId, "");
            }
            case 5: {
                return this.getErrorDescriptorForActionId5(nccpActionId, "");
            }
            case 6: {
                return this.getErrorDescriptorForActionId6(nccpActionId, "");
            }
            case 7: {
                return this.getErrorDescriptorForActionId7(nccpActionId, "");
            }
            case 8: {
                return this.getErrorDescriptorForActionId8(nccpActionId, "");
            }
            case 9: {
                return this.getErrorDescriptorForActionId9(nccpActionId, "");
            }
            case 10: {
                return this.getErrorDescriptorForActionId10(nccpActionId, "");
            }
            case 11: {
                Log.d("ErrorManager", "ActionID 11 NFErr_MC_AbortPlayback");
                return this.getErrorDescriptorForActionId11(nccpActionId, "");
            }
            case 12: {
                Log.d("ErrorManager", "ActionID 12 NFErr_MC_StaleCredentials");
                return this.getErrorDescriptorForActionId12(nccpActionId, "");
            }
        }
    }
    
    private AlertDialogFactory.AlertDialogDescriptor getHandlerForMediaError(final Error error) {
        final boolean checkForOpenDeviceFailureInStack = error.checkForOpenDeviceFailureInStack();
        if (Log.isLoggable("ErrorManager", 3)) {
            Log.d("ErrorManager", "MediaError " + error);
            Log.d("ErrorManager", "checkForOpenDeviceFailureInStack : " + checkForOpenDeviceFailureInStack);
        }
        if (error.getError() == -268369916) {
            return new AlertDialogFactory.AlertDialogDescriptor("", this.context.getString(2131296458), null, this.restartApp);
        }
        if (error.getError() != -268369915 || !checkForOpenDeviceFailureInStack) {
            return new AlertDialogFactory.AlertDialogDescriptor("", this.context.getString(2131296401), null, this.exit);
        }
        final String string = this.context.getString(2131296458);
        if (this.mConfig.getCurrentPlayerType() == PlayerType.device12 && this.mConfig.isCurrentDrmWidevine()) {
            return new AlertDialogFactory.AlertDialogDescriptor("", string, null, this.restartApp);
        }
        return new AlertDialogFactory.AlertDialogDescriptor("", string, null, this.resetApp);
    }
    
    private AlertDialogFactory.AlertDialogDescriptor getHandlerForNetworkError(final NetworkError networkError) {
        if (Log.isLoggable("ErrorManager", 3)) {
            Log.d("ErrorManager", "NetworkError " + networkError);
        }
        return new AlertDialogFactory.AlertDialogDescriptor("", this.context.getString(2131296507), null, this.exit);
    }
    
    private AlertDialogFactory.AlertDialogDescriptor getHandlerForNetworkingError(final NccpNetworkingError nccpNetworkingError) {
        if (Log.isLoggable("ErrorManager", 3)) {
            Log.d("ErrorManager", "NccpNetworkingError " + nccpNetworkingError);
        }
        return new AlertDialogFactory.AlertDialogDescriptor("", this.context.getString(2131296429), null, this.exit);
    }
    
    private AlertDialogFactory.AlertDialogDescriptor getUknownErrorDescriptor(final MediaEvent mediaEvent, final String s) {
        Log.w("ErrorManager", "Uknown error");
        return new AlertDialogFactory.AlertDialogDescriptor(s, this.context.getString(2131296361), null, this.exit);
    }
    
    private Runnable handle(final MediaEvent mediaEvent) {
        Log.d("ErrorManager", "Handle error " + mediaEvent);
        return new Runnable() {
            @Override
            public void run() {
                ErrorManager.this.handleError(mediaEvent);
            }
        };
    }
    
    private void handleError(final MediaEvent mediaEvent) {
        while (true) {
            Label_0058: {
                synchronized (this) {
                    if (this.destroyed) {
                        Log.w("ErrorManager", "ErrorManager is already destroyed, ignoring...");
                    }
                    else {
                        if (!this.handlePlayReadyRevocation(mediaEvent)) {
                            break Label_0058;
                        }
                        this.reportStartPlayEnded(RootCause.clientFailure, ActionOnUIError.handledSilently, null, null);
                        Log.d("ErrorManager", "Playready certificate revocated, do not display error!");
                    }
                    return;
                }
            }
            final MediaEvent mediaEvent2;
            final AlertDialogFactory.AlertDialogDescriptor handler = this.getHandler(mediaEvent2);
            if (handler == null) {
                Log.w("ErrorManager", "We decided to ignore " + mediaEvent2);
                this.errorPosted = false;
                return;
            }
            this.reportStartPlayEnded(RootCause.clientFailure, ActionOnUIError.displayedError, handler.getMessage(), null);
            final UpdateDialog.Builder dialog = AlertDialogFactory.createDialog((Context)this.context, this.handler, handler);
            if (dialog != null) {
                dialog.show();
                this.context.setErrorDialogId(this.context.reportUiModelessViewSessionStart(IClientLogging.ModalView.errorDialog));
            }
        }
    }
    
    private boolean handlePlayReadyRevocation(final MediaEvent mediaEvent) {
        if (!(mediaEvent instanceof NccpActionId)) {
            Log.d("ErrorManager", "Not action ID error");
            return false;
        }
        if (((NccpActionId)mediaEvent).getReasonCode() == 113L) {
            Log.e("ErrorManager", "Playready certificate revocated, we do not have backup player, report an error!");
            return false;
        }
        Log.e("ErrorManager", "Regular error, handle as popup");
        return false;
    }
    
    private void reportStartPlayEnded(final RootCause rootCause, final ActionOnUIError actionOnUIError, final String s, final Integer n) {
        if (this.context.getState().playStartInProgress.getAndSet(false)) {
            LogUtils.reportPlayActionEnded((Context)this.context, IClientLogging.CompletionReason.failed, new UIError(rootCause, actionOnUIError, s, null), n);
        }
    }
    
    private void unmuteAudio() {
        final AudioManager audioManager = (AudioManager)this.context.getSystemService("audio");
        if (audioManager != null) {
            audioManager.setStreamMute(3, false);
            Log.d("ErrorManager", "UN-MUTED");
        }
    }
    
    public boolean addError(final NccpError nccpError) {
        while (true) {
            boolean b = true;
            Label_0050: {
                synchronized (this) {
                    if (this.destroyed) {
                        Log.w("ErrorManager", "ErrorManager is already destroyed, ignoring...");
                        b = false;
                    }
                    else {
                        if (!this.errorPosted) {
                            break Label_0050;
                        }
                        Log.w("ErrorManager", "ErrorManager already posted an error message, ignoring...");
                    }
                    return b;
                }
            }
            this.errorPosted = true;
            final MediaEvent reportedError;
            this.reportedError = reportedError;
            this.handler.post(this.handle(reportedError));
            return b;
        }
    }
    
    public boolean addMediaError(final Error error) {
        synchronized (this) {
            if (this.destroyed) {
                Log.w("ErrorManager", "ErrorManager is already destroyed, ignoring...");
                return false;
            }
            if (this.errorPosted) {
                Log.w("ErrorManager", "ErrorManager already posted an error message, ignoring...");
                return true;
            }
        }
        this.errorPosted = true;
        final MediaEvent reportedError;
        this.reportedError = reportedError;
        this.handler.post(this.handle(reportedError));
        // monitorexit(this)
        return true;
    }
    
    public void destroy() {
        synchronized (this) {
            this.destroyed = true;
            this.context = null;
            this.handler = null;
            this.exit = null;
        }
    }
    
    public void forceStop() {
        synchronized (this) {
            this.unmuteAudio();
            final int myPid = Process.myPid();
            Log.d("ErrorManager", "Destroying app proces " + myPid + "...");
            Process.killProcess(myPid);
            Log.d("ErrorManager", "Destroying app proces " + myPid + " done.");
        }
    }
    
    public MediaEvent getReportedError() {
        return this.reportedError;
    }
    
    public final boolean isErrorReported() {
        return this.errorPosted;
    }
    
    public void reset() {
        synchronized (this) {
            this.errorPosted = false;
        }
    }
    
    private static class LinkTag
    {
        public String href;
        public String text;
    }
}
