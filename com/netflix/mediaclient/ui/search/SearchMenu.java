// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import android.view.MenuItem;
import android.view.Menu;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class SearchMenu
{
    public static MenuItem addSearchNavigation(final NetflixActivity netflixActivity, final Menu menu, final boolean b) {
        int icon;
        if (b) {
            icon = 2130837756;
        }
        else {
            icon = 2130837755;
        }
        return menu.add(0, 2131689472, 0, 2131231281).setIcon(icon).setIntent(SearchActivity.create(netflixActivity)).setShowAsActionFlags(1);
    }
}
