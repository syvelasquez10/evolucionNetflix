// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.error;

import org.json.JSONObject;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import org.json.JSONException;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.error.action.ExitPlayerAction;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import java.util.Locale;

class ConcurentStreamUpgradeErrorDescriptor extends ActionId3ErrorDescriptor
{
    private static final Locale US_LOCALE;
    
    static {
        US_LOCALE = Locale.US;
    }
    
    ConcurentStreamUpgradeErrorDescriptor(final AlertDialogFactory$AlertDialogDescriptor alertDialogFactory$AlertDialogDescriptor) {
        super(alertDialogFactory$AlertDialogDescriptor);
    }
    
    static ConcurentStreamUpgradeErrorDescriptor build(final PlayerFragment playerFragment, final NccpActionId nccpActionId, final String s) {
        if (Log.isLoggable()) {
            Log.d("nf_play_error", "Original message: " + nccpActionId.getMessage());
        }
        if (nccpActionId.getReasonCode() != 102) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        final String trimWhiteSpace = StringUtils.trimWhiteSpace(nccpActionId.getMessage());
        final PlaybackErrorDescriptor$LinkTag link = extractLink(trimWhiteSpace, sb);
        if (link == null) {
            final ExitPlayerAction exitPlayerAction = new ExitPlayerAction(playerFragment.getActivity());
            playerFragment.setMaxStreamsReachedDialogId(playerFragment.getNetflixActivity().reportUiModelessViewSessionStart(IClientLogging$ModalView.maxStreamsReached));
            return new ConcurentStreamUpgradeErrorDescriptor(new AlertDialogFactory$AlertDialogDescriptor(s, trimWhiteSpace, null, exitPlayerAction));
        }
        if (Log.isLoggable()) {
            Log.d("nf_play_error", "Link found: href=" + link.href + ", text=" + link.text);
        }
        final String text = link.text;
        String string = null;
        Label_0199: {
            if (text != null) {
                string = text;
                if (!"".equals(text.trim())) {
                    break Label_0199;
                }
            }
            string = playerFragment.getString(2131231000);
        }
        final String string2 = playerFragment.getString(2131230999);
        Log.d("nf_play_error", "Check if link contains NCCP reason code");
        if (link.href.startsWith("RC:")) {
            Log.d("nf_play_error", "NCCP reason code found");
            try {
                return getErrorDescriptorForConcurrentStreamUpgradeWithNccpReasonCode(playerFragment, link, s, sb.toString(), string, string2);
            }
            catch (JSONException ex) {
                Log.e("nf_play_error", "Failed to process upgrade with NCCP reason code, return generic action id 3 message", (Throwable)ex);
                return null;
            }
        }
        Log.d("nf_play_error", "NCCP reason code NOT found, treat it as path of URL");
        playerFragment.setMaxStreamsReachedDialogId(playerFragment.getNetflixActivity().reportUiModelessViewSessionStart(IClientLogging$ModalView.maxStreamsReached));
        return getErrorDescriptorForConcurrentStreamUpgradeRegular(playerFragment, link, s, sb.toString(), string, string2);
    }
    
    private static PlaybackErrorDescriptor$LinkTag extractLink(final String s, final StringBuilder sb) {
        if (Log.isLoggable()) {
            Log.d("nf_play_error", "Trimmed message: " + s);
        }
        final String lowerCase = s.toLowerCase(ConcurentStreamUpgradeErrorDescriptor.US_LOCALE);
        if (Log.isLoggable()) {
            Log.d("nf_play_error", "Lower case test message: " + lowerCase);
        }
        final int index = lowerCase.indexOf("<a href=\"");
        if (index < 0) {
            Log.d("nf_play_error", "Not a concurrent stream upgrade message");
            return null;
        }
        final int index2 = lowerCase.indexOf("\"", index + 10);
        if (index2 < 0) {
            Log.d("nf_play_error", "Not a concurrent stream upgrade message");
            return null;
        }
        final PlaybackErrorDescriptor$LinkTag playbackErrorDescriptor$LinkTag = new PlaybackErrorDescriptor$LinkTag();
        playbackErrorDescriptor$LinkTag.href = s.substring(index + 9, index2);
        final int index3 = lowerCase.indexOf(">", index2);
        final String substring = lowerCase.substring(index3 + 1);
        if (Log.isLoggable()) {
            Log.d("nf_play_error", "HREF extracted: " + playbackErrorDescriptor$LinkTag.href);
            Log.d("nf_play_error", "Rest of message: " + substring);
        }
        final int index4 = lowerCase.indexOf("</a>");
        if (index > index4) {
            Log.e("nf_play_error", "Closing </a> found before <a>!" + index4 + " < " + index);
            return null;
        }
        playbackErrorDescriptor$LinkTag.text = s.substring(index3 + 1, index4);
        String substring2;
        if (index4 + 5 >= s.length()) {
            substring2 = "";
        }
        else {
            substring2 = s.substring(index4 + 4);
        }
        if (Log.isLoggable()) {
            Log.d("nf_play_error", "TEXT extracted: " + playbackErrorDescriptor$LinkTag.text);
            Log.d("nf_play_error", "Rest of message: " + substring2);
        }
        sb.append(s.substring(0, index));
        if (Log.isLoggable()) {
            Log.d("nf_play_error", "User message (part before link): " + sb.toString());
        }
        sb.append(" ").append(substring2);
        if (Log.isLoggable()) {
            Log.d("nf_play_error", "User message (part after link): " + sb.toString());
        }
        return playbackErrorDescriptor$LinkTag;
    }
    
    private static ConcurentStreamUpgradeErrorDescriptor getErrorDescriptorForConcurrentStreamUpgradeRegular(final PlayerFragment playerFragment, final PlaybackErrorDescriptor$LinkTag playbackErrorDescriptor$LinkTag, final String s, final String s2, final String s3, final String s4) {
        final ConcurentStreamUpgradeErrorDescriptor$2 concurentStreamUpgradeErrorDescriptor$2 = new ConcurentStreamUpgradeErrorDescriptor$2(playbackErrorDescriptor$LinkTag, playerFragment);
        playerFragment.setMaxStreamsReachedDialogId(playerFragment.getNetflixActivity().reportUiModelessViewSessionStart(IClientLogging$ModalView.maxStreamsReached));
        return new ConcurentStreamUpgradeErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, s2, s3, concurentStreamUpgradeErrorDescriptor$2, s4, new ExitPlayerAction(playerFragment.getActivity())));
    }
    
    private static ConcurentStreamUpgradeErrorDescriptor getErrorDescriptorForConcurrentStreamUpgradeWithNccpReasonCode(final PlayerFragment playerFragment, final PlaybackErrorDescriptor$LinkTag playbackErrorDescriptor$LinkTag, final String s, final String s2, final String s3, final String s4) {
        if (Log.isLoggable()) {
            Log.d("nf_play_error", "Link found: href=" + playbackErrorDescriptor$LinkTag.href + ", text=" + playbackErrorDescriptor$LinkTag.text);
        }
        final String[] tokens = StringUtils.extractTokens(playbackErrorDescriptor$LinkTag.href, ";");
        final JSONObject jsonObject = new JSONObject();
        if (tokens.length != 2) {
            Log.e("nf_play_error", "Problem with RC format! ; is missing!");
            return null;
        }
        if (Log.isLoggable()) {
            Log.d("nf_play_error", "HREF token: " + tokens[0] + ", " + tokens[1]);
        }
        final String[] tokens2 = StringUtils.extractTokens(tokens[0], ":");
        if (tokens2.length != 2) {
            Log.e("nf_play_error", "Problem with RC format!");
            return null;
        }
        if (!"RC".equalsIgnoreCase(tokens2[0])) {
            Log.e("nf_play_error", "RC is NOT first element, but: " + tokens2[0]);
            return null;
        }
        jsonObject.put("RC", (Object)tokens2[1]);
        final String[] tokens3 = StringUtils.extractTokens(tokens[1], "&");
        if (Log.isLoggable()) {
            Log.d("nf_play_error", "# of parameters found in NCCP reason code: " + tokens3.length);
        }
        for (int i = 0; i < tokens3.length; ++i) {
            final String[] tokens4 = StringUtils.extractTokens(tokens3[i], "=");
            if (tokens4.length == 2) {
                jsonObject.put(tokens4[0], (Object)tokens4[1]);
            }
            else if (Log.isLoggable()) {
                Log.w("nf_play_error", "Parameter " + i + " does not have proper format: " + tokens3[i] + ". Skipping.");
            }
        }
        if (Log.isLoggable()) {
            Log.d("nf_play_error", "JSON: " + jsonObject);
        }
        final ConcurentStreamUpgradeErrorDescriptor$1 concurentStreamUpgradeErrorDescriptor$1 = new ConcurentStreamUpgradeErrorDescriptor$1(playerFragment, jsonObject);
        playerFragment.setMaxStreamsReachedDialogId(playerFragment.getNetflixActivity().reportUiModelessViewSessionStart(IClientLogging$ModalView.maxStreamsReached));
        return new ConcurentStreamUpgradeErrorDescriptor(new AlertDialogFactory$TwoButtonAlertDialogDescriptor(s, s2, s3, concurentStreamUpgradeErrorDescriptor$1, s4, new ExitPlayerAction(playerFragment.getActivity())));
    }
}
