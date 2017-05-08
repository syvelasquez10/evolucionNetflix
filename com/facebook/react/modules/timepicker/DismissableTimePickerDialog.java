// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.timepicker;

import android.os.Build$VERSION;
import android.app.TimePickerDialog$OnTimeSetListener;
import android.content.Context;
import android.app.TimePickerDialog;

public class DismissableTimePickerDialog extends TimePickerDialog
{
    public DismissableTimePickerDialog(final Context context, final TimePickerDialog$OnTimeSetListener timePickerDialog$OnTimeSetListener, final int n, final int n2, final boolean b) {
        super(context, timePickerDialog$OnTimeSetListener, n, n2, b);
    }
    
    protected void onStop() {
        if (Build$VERSION.SDK_INT > 19) {
            super.onStop();
        }
    }
}
