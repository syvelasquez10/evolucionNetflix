// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.text.Html;
import android.annotation.TargetApi;

@TargetApi(16)
class ShareCompatJB
{
    public static String escapeHtml(final CharSequence charSequence) {
        return Html.escapeHtml(charSequence);
    }
}
