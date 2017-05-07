// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.content.DialogInterface;
import android.widget.TextView;
import android.widget.EditText;
import android.view.View;
import android.content.DialogInterface$OnClickListener;

public class PromptListener implements DialogInterface$OnClickListener
{
    private View promptDialogView;
    private int promptEditTextControlId;
    private String promptValue;
    private PromptResult result;
    
    public PromptListener(final View promptDialogView, final int n, final String s, final int promptEditTextControlId, final String s2, final PromptResult result) {
        if (promptDialogView == null) {
            throw new IllegalArgumentException("Dialog view is null!");
        }
        this.promptDialogView = promptDialogView;
        this.promptEditTextControlId = promptEditTextControlId;
        this.result = result;
        String text;
        if ((text = s2) == null) {
            text = "";
        }
        final EditText editText = (EditText)this.promptDialogView.findViewById(promptEditTextControlId);
        if (editText != null) {
            editText.setText((CharSequence)text);
        }
        final TextView textView = (TextView)this.promptDialogView.findViewById(n);
        if (textView != null) {
            String text2;
            if ((text2 = s) == null) {
                text2 = "";
            }
            textView.setText((CharSequence)text2);
        }
    }
    
    private String getPromptText() {
        final EditText editText = (EditText)this.promptDialogView.findViewById(this.promptEditTextControlId);
        if (editText != null) {
            return editText.getText().toString();
        }
        return "";
    }
    
    public String getPromptValue() {
        return this.promptValue;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        if (n == -1) {
            this.result.confirm(this.getPromptText());
        }
        else if (n == -2) {
            this.result.cancel();
        }
    }
}
