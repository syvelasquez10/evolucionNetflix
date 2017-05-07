// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.ui.diagnosis.DiagnosisActivity;
import android.os.Bundle;
import android.os.Build;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import android.net.Uri;
import android.content.Intent;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.preference.PreferenceFragment;
import android.content.Context;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.preference.Preference;
import android.preference.Preference$OnPreferenceClickListener;

class AboutFragment$2 implements Preference$OnPreferenceClickListener
{
    final /* synthetic */ AboutFragment this$0;
    
    AboutFragment$2(final AboutFragment this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onPreferenceClick(final Preference preference) {
        ApmLogUtils.reportUiModalViewChanged((Context)this.this$0.activity, IClientLogging$ModalView.legalTerms);
        return false;
    }
}
