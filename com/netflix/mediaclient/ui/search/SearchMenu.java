// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.view.Menu;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class SearchMenu
{
    public static void addSearchNavigation(final NetflixActivity netflixActivity, final Menu menu) {
        int icon;
        if (BrowseExperience.isKubrickKids()) {
            icon = 2130837726;
        }
        else {
            icon = 2130837725;
        }
        menu.add(0, 2131427342, 0, 2131493200).setIcon(icon).setIntent(SearchActivity.create(netflixActivity)).setShowAsAction(1);
    }
}
