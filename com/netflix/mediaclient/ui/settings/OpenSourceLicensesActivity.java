// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.view.View;
import android.widget.ListAdapter;
import com.netflix.mediaclient.util.ViewUtils;
import android.widget.ListView;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.content.Intent;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class OpenSourceLicensesActivity extends NetflixActivity
{
    private static final String APACHE_LICENSE_20 = "\nLicensed under the Apache License, Version 2.0 (the \"License\"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at:\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software distributed under the License is distributed on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the specific language governing permissions and limitations under the License.";
    private static final String ROUNDED_IMAGE_VIEW_LICENSE = "Copyright (c) 2013, Vincent Mi\n\nLicensed under the Apache License, Version 2.0 (the \"License\"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at:\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software distributed under the License is distributed on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the specific language governing permissions and limitations under the License.";
    private static final List<OpenSourceLicensesActivity$OslInfo> oslInfo;
    
    static {
        oslInfo = new ArrayList<OpenSourceLicensesActivity$OslInfo>();
    }
    
    public static Intent create(final Context context) {
        return new Intent(context, (Class)OpenSourceLicensesActivity.class);
    }
    
    private String createHeaderText(final String s) {
        return String.format(this.getString(2131493181), s);
    }
    
    private void createOslInfo() {
        OpenSourceLicensesActivity.oslInfo.clear();
        OpenSourceLicensesActivity.oslInfo.add(new OpenSourceLicensesActivity$OslInfo(this.createHeaderText("RoundedImageView"), "Copyright (c) 2013, Vincent Mi\n\nLicensed under the Apache License, Version 2.0 (the \"License\"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at:\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software distributed under the License is distributed on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the specific language governing permissions and limitations under the License."));
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new OpenSourceLicensesActivity$1(this);
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.openSourceLicenses;
    }
    
    @Override
    protected boolean hasUpAction() {
        return false;
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.createOslInfo();
        final NetflixActionBar netflixActionBar = this.getNetflixActionBar();
        if (netflixActionBar != null) {
            netflixActionBar.setTitle(this.getString(2131493180));
        }
        final ListView contentView = new ListView((Context)this);
        contentView.setDividerHeight(0);
        ViewUtils.addActionBarPaddingView(contentView);
        contentView.setAdapter((ListAdapter)new OpenSourceLicensesActivity$OslAdapter(this, null));
        this.setContentView((View)contentView);
    }
    
    @Override
    protected boolean showAboutInMenu() {
        return false;
    }
    
    @Override
    protected boolean showSettingsInMenu() {
        return false;
    }
    
    @Override
    protected boolean showSignOutInMenu() {
        return false;
    }
}
