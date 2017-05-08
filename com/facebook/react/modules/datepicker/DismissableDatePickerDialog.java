// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.datepicker;

import android.os.Build$VERSION;
import android.app.DatePickerDialog$OnDateSetListener;
import android.content.Context;
import android.app.DatePickerDialog;

public class DismissableDatePickerDialog extends DatePickerDialog
{
    public DismissableDatePickerDialog(final Context context, final int n, final DatePickerDialog$OnDateSetListener datePickerDialog$OnDateSetListener, final int n2, final int n3, final int n4) {
        super(context, n, datePickerDialog$OnDateSetListener, n2, n3, n4);
    }
    
    public DismissableDatePickerDialog(final Context context, final DatePickerDialog$OnDateSetListener datePickerDialog$OnDateSetListener, final int n, final int n2, final int n3) {
        super(context, datePickerDialog$OnDateSetListener, n, n2, n3);
    }
    
    protected void onStop() {
        if (Build$VERSION.SDK_INT > 19) {
            super.onStop();
        }
    }
}
