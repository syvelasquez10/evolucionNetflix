// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.bandwidthsetting;

import android.widget.CompoundButton$OnCheckedChangeListener;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.support.v7.widget.SwitchCompat;
import android.widget.TextView;
import android.preference.DialogPreference;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.view.View$OnClickListener;

class BandwidthPreferenceDialog$DataSelectorViewListener implements View$OnClickListener
{
    final /* synthetic */ BandwidthPreferenceDialog this$0;
    
    BandwidthPreferenceDialog$DataSelectorViewListener(final BandwidthPreferenceDialog this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        if (this.this$0.isAutoSelectorViewOn()) {
            Log.d("nf_bw", "ignore manual selection - in auto mode");
        }
        else {
            final BandwidthPreferenceDialog$ManualBwChoice undefined = BandwidthPreferenceDialog$ManualBwChoice.UNDEFINED;
            BandwidthPreferenceDialog$ManualBwChoice bandwidthPreferenceDialog$ManualBwChoice = null;
            switch (view.getId()) {
                default: {
                    Log.d("nf_bw", "Ignoring click on unknown view");
                    bandwidthPreferenceDialog$ManualBwChoice = undefined;
                    break;
                }
                case 2131821349:
                case 2131821350: {
                    bandwidthPreferenceDialog$ManualBwChoice = BandwidthPreferenceDialog$ManualBwChoice.OFF;
                    break;
                }
                case 2131821353:
                case 2131821354: {
                    bandwidthPreferenceDialog$ManualBwChoice = BandwidthPreferenceDialog$ManualBwChoice.LOW;
                    break;
                }
                case 2131821357:
                case 2131821358: {
                    bandwidthPreferenceDialog$ManualBwChoice = BandwidthPreferenceDialog$ManualBwChoice.MEDIUM;
                    break;
                }
                case 2131821361:
                case 2131821362: {
                    bandwidthPreferenceDialog$ManualBwChoice = BandwidthPreferenceDialog$ManualBwChoice.HIGH;
                    break;
                }
                case 2131821365:
                case 2131821366: {
                    bandwidthPreferenceDialog$ManualBwChoice = BandwidthPreferenceDialog$ManualBwChoice.UNLIMITED;
                    break;
                }
            }
            if (bandwidthPreferenceDialog$ManualBwChoice != BandwidthPreferenceDialog$ManualBwChoice.UNDEFINED) {
                this.this$0.unselectManualChoices();
                this.this$0.selectManualChoice(bandwidthPreferenceDialog$ManualBwChoice);
            }
        }
    }
}
