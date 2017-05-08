// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import android.view.Menu;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class SearchMenu
{
    public static void addSearchNavigation(final NetflixActivity netflixActivity, final Menu menu, final boolean b) {
        int icon;
        if (b) {
            icon = 2130837753;
        }
        else {
            icon = 2130837752;
        }
        menu.add(0, 2131623936, 0, 2131165729).setIcon(icon).setIntent(SearchActivity.create(netflixActivity)).setShowAsAction(1);
    }
}
