// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import android.text.util.Linkify;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.content.Intent;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class OpenSourceLicensesActivity extends NetflixActivity
{
    private static final String APACHE_LICENSE_20 = "\nLicensed under the Apache License, Version 2.0 (the \"License\"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at:\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software distributed under the License is distributed on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the specific language governing permissions and limitations under the License.";
    private static final String ROUNDED_IMAGE_VIEW_LICENSE = "Copyright (c) 2013, Vincent Mi\n\nLicensed under the Apache License, Version 2.0 (the \"License\"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at:\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software distributed under the License is distributed on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the specific language governing permissions and limitations under the License.";
    private static final String STICKY_GRID_HEADERS_LICENSE = "Copyright 2013 Tonic Artos\n\nLicensed under the Apache License, Version 2.0 (the \"License\"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at:\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software distributed under the License is distributed on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the specific language governing permissions and limitations under the License.";
    private static final List<OslInfo> oslInfo;
    
    static {
        oslInfo = new ArrayList<OslInfo>();
    }
    
    public static Intent create(final Context context) {
        return new Intent(context, (Class)OpenSourceLicensesActivity.class);
    }
    
    private String createHeaderText(final String s) {
        return String.format(this.getString(2131493196), s);
    }
    
    private void createOslInfo() {
        OpenSourceLicensesActivity.oslInfo.clear();
        OpenSourceLicensesActivity.oslInfo.add(new OslInfo(this.createHeaderText("StickyGridHeaders"), "Copyright 2013 Tonic Artos\n\nLicensed under the Apache License, Version 2.0 (the \"License\"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at:\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software distributed under the License is distributed on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the specific language governing permissions and limitations under the License."));
        OpenSourceLicensesActivity.oslInfo.add(new OslInfo(this.createHeaderText("RoundedImageView"), "Copyright (c) 2013, Vincent Mi\n\nLicensed under the Apache License, Version 2.0 (the \"License\"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at:\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software distributed under the License is distributed on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the specific language governing permissions and limitations under the License."));
    }
    
    @Override
    public IClientLogging.ModalView getUiScreen() {
        return IClientLogging.ModalView.openSourceLicenses;
    }
    
    public boolean isLoadingData() {
        return false;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.createOslInfo();
        final NetflixActionBar netflixActionBar = this.getNetflixActionBar();
        if (netflixActionBar != null) {
            netflixActionBar.setTitle(this.getString(2131493195));
        }
        final ListView contentView = new ListView((Context)this);
        contentView.setAdapter((ListAdapter)new OslAdapter());
        this.setContentView((View)contentView);
    }
    
    @Override
    protected boolean showSettingsInMenu() {
        return false;
    }
    
    @Override
    protected boolean showSignOutInMenu() {
        return false;
    }
    
    private static class Holder
    {
        final TextView license;
        final TextView title;
        
        public Holder(final TextView title, final TextView license) {
            this.title = title;
            this.license = license;
        }
    }
    
    private class OslAdapter extends BaseAdapter
    {
        public int getCount() {
            return OpenSourceLicensesActivity.oslInfo.size();
        }
        
        public OslInfo getItem(final int n) {
            return OpenSourceLicensesActivity.oslInfo.get(n);
        }
        
        public long getItemId(final int n) {
            return n;
        }
        
        public View getView(final int n, final View view, final ViewGroup viewGroup) {
            View inflate = view;
            if (view == null) {
                inflate = OpenSourceLicensesActivity.this.getLayoutInflater().inflate(2130903133, (ViewGroup)null);
                inflate.setTag((Object)new Holder((TextView)inflate.findViewById(2131165478), (TextView)inflate.findViewById(2131165479)));
            }
            final Holder holder = (Holder)inflate.getTag();
            final OslInfo item = this.getItem(n);
            holder.title.setText((CharSequence)item.title);
            holder.license.setText((CharSequence)item.license);
            Linkify.addLinks(holder.license, 1);
            return inflate;
        }
    }
    
    private static class OslInfo
    {
        final String license;
        final String title;
        
        public OslInfo(final String title, final String license) {
            this.title = title;
            this.license = license;
        }
    }
}
