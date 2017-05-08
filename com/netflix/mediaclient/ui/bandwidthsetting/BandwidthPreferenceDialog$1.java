// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.bandwidthsetting;

import android.view.View;
import android.view.View$OnClickListener;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.support.v7.widget.SwitchCompat;
import android.widget.TextView;
import android.preference.DialogPreference;
import com.netflix.mediaclient.Log;
import android.widget.CompoundButton;
import android.widget.CompoundButton$OnCheckedChangeListener;

class BandwidthPreferenceDialog$1 implements CompoundButton$OnCheckedChangeListener
{
    final /* synthetic */ BandwidthPreferenceDialog this$0;
    
    BandwidthPreferenceDialog$1(final BandwidthPreferenceDialog this$0) {
        this.this$0 = this$0;
    }
    
    public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
        Log.d("nf_bw", "bwSwitch toggled state: " + b);
        this.this$0.unselectManualChoices();
        this.this$0.enableManualViews(!b);
        if (!b) {
            this.this$0.selectManualChoice(BandwidthPreferenceDialog$ManualBwChoice.create(BandwidthUtility.DEFAULT_MANUAL_CHOICE));
        }
    }
}
