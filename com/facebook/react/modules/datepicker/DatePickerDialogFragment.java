// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.datepicker;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.os.Build$VERSION;
import java.util.Locale;
import java.util.Calendar;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.content.DialogInterface$OnDismissListener;
import android.app.DatePickerDialog$OnDateSetListener;
import android.annotation.SuppressLint;
import android.app.DialogFragment;

@SuppressLint({ "ValidFragment" })
public class DatePickerDialogFragment extends DialogFragment
{
    private DatePickerDialog$OnDateSetListener mOnDateSetListener;
    private DialogInterface$OnDismissListener mOnDismissListener;
    
    static Dialog createDialog(final Bundle bundle, final Context context, final DatePickerDialog$OnDateSetListener datePickerDialog$OnDateSetListener) {
        final Calendar instance = Calendar.getInstance();
        if (bundle != null && bundle.containsKey("date")) {
            instance.setTimeInMillis(bundle.getLong("date"));
        }
        final int value = instance.get(1);
        final int value2 = instance.get(2);
        final int value3 = instance.get(5);
        DatePickerMode datePickerMode2;
        final DatePickerMode datePickerMode = datePickerMode2 = DatePickerMode.DEFAULT;
        if (bundle != null) {
            datePickerMode2 = datePickerMode;
            if (bundle.getString("mode", (String)null) != null) {
                datePickerMode2 = DatePickerMode.valueOf(bundle.getString("mode").toUpperCase(Locale.US));
            }
        }
        Object o = null;
        if (Build$VERSION.SDK_INT >= 21) {
            switch (DatePickerDialogFragment$1.$SwitchMap$com$facebook$react$modules$datepicker$DatePickerMode[datePickerMode2.ordinal()]) {
                default: {
                    o = null;
                    break;
                }
                case 1: {
                    o = new DismissableDatePickerDialog(context, context.getResources().getIdentifier("CalendarDatePickerDialog", "style", context.getPackageName()), datePickerDialog$OnDateSetListener, value, value2, value3);
                    break;
                }
                case 2: {
                    o = new DismissableDatePickerDialog(context, context.getResources().getIdentifier("SpinnerDatePickerDialog", "style", context.getPackageName()), datePickerDialog$OnDateSetListener, value, value2, value3);
                    break;
                }
                case 3: {
                    o = new DismissableDatePickerDialog(context, datePickerDialog$OnDateSetListener, value, value2, value3);
                    break;
                }
            }
        }
        else {
            o = new DismissableDatePickerDialog(context, datePickerDialog$OnDateSetListener, value, value2, value3);
            switch (DatePickerDialogFragment$1.$SwitchMap$com$facebook$react$modules$datepicker$DatePickerMode[datePickerMode2.ordinal()]) {
                case 1: {
                    ((DatePickerDialog)o).getDatePicker().setCalendarViewShown(true);
                    ((DatePickerDialog)o).getDatePicker().setSpinnersShown(false);
                    break;
                }
                case 2: {
                    ((DatePickerDialog)o).getDatePicker().setCalendarViewShown(false);
                    break;
                }
            }
        }
        final DatePicker datePicker = ((DatePickerDialog)o).getDatePicker();
        if (bundle != null && bundle.containsKey("minDate")) {
            instance.setTimeInMillis(bundle.getLong("minDate"));
            instance.set(11, 0);
            instance.set(12, 0);
            instance.set(13, 0);
            instance.set(14, 0);
            datePicker.setMinDate(instance.getTimeInMillis());
        }
        else {
            datePicker.setMinDate(-2208988800001L);
        }
        if (bundle != null && bundle.containsKey("maxDate")) {
            instance.setTimeInMillis(bundle.getLong("maxDate"));
            instance.set(11, 23);
            instance.set(12, 59);
            instance.set(13, 59);
            instance.set(14, 999);
            datePicker.setMaxDate(instance.getTimeInMillis());
        }
        return (Dialog)o;
    }
    
    public Dialog onCreateDialog(final Bundle bundle) {
        return createDialog(this.getArguments(), (Context)this.getActivity(), this.mOnDateSetListener);
    }
    
    public void onDismiss(final DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (this.mOnDismissListener != null) {
            this.mOnDismissListener.onDismiss(dialogInterface);
        }
    }
    
    void setOnDateSetListener(final DatePickerDialog$OnDateSetListener mOnDateSetListener) {
        this.mOnDateSetListener = mOnDateSetListener;
    }
    
    void setOnDismissListener(final DialogInterface$OnDismissListener mOnDismissListener) {
        this.mOnDismissListener = mOnDismissListener;
    }
}
