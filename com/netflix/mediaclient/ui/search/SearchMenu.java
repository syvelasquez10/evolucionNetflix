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
            icon = 2130837721;
        }
        else {
            icon = 2130837720;
        }
        menu.add(0, 2131165250, 0, 2131493191).setIcon(icon).setIntent(SearchActivity.create(netflixActivity)).setShowAsAction(1);
    }
}
