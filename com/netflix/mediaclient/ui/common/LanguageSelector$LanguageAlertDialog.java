// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.content.DialogInterface$OnCancelListener;
import android.content.Context;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import android.app.AlertDialog;

class LanguageSelector$LanguageAlertDialog extends AlertDialog implements MdxMiniPlayerFrag$MdxMiniPlayerDialog
{
    private LanguageSelector$LanguageAlertDialog(final Context context) {
        super(context);
    }
    
    private LanguageSelector$LanguageAlertDialog(final Context context, final int n) {
        super(context, n);
    }
    
    private LanguageSelector$LanguageAlertDialog(final Context context, final boolean b, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        super(context, b, dialogInterface$OnCancelListener);
    }
}
