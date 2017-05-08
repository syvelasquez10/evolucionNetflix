// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import android.graphics.Typeface;
import android.widget.TextView;
import com.netflix.mediaclient.ui.home.StandardSlidingMenu$GenreRowHolder;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import com.netflix.mediaclient.util.ViewUtils;
import android.support.v4.widget.DrawerLayout;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.home.StandardSlidingMenu;

public class KidsSlidingMenu extends StandardSlidingMenu
{
    private final int kidsItemTextColor;
    
    public KidsSlidingMenu(final NetflixActivity netflixActivity, final DrawerLayout drawerLayout) {
        int i = 0;
        super(netflixActivity, drawerLayout, false);
        this.notificationsDivider.setBackgroundColor(netflixActivity.getResources().getColor(2131624078));
        this.notificationsDivider.setVisibility(0);
        final int[] array2;
        final int[] array = array2 = new int[2];
        array2[0] = 2131690282;
        array2[1] = 2131690288;
        while (i < array.length) {
            drawerLayout.findViewById((int)Integer.valueOf(array[i])).setVisibility(8);
            ++i;
        }
        this.kidsItemTextColor = netflixActivity.getResources().getColor(2131624044);
        this.profileName.setTextColor(this.kidsItemTextColor);
        ViewUtils.setTextViewToBold(this.profileName);
        this.homeText.setTextColor(this.kidsItemTextColor);
        ViewUtils.setTextViewToBold(this.homeText);
        ((FrameLayout$LayoutParams)this.genresList.getLayoutParams()).bottomMargin = (int)netflixActivity.getResources().getDimension(2131362125);
        ViewUtils.removeShadow(this.homeText);
        this.homeText.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, netflixActivity.getResources().getDimensionPixelSize(2131362160)));
        drawerLayout.findViewById(2131690277).setBackgroundResource(2131624043);
    }
    
    @Override
    protected void updateAdapterViews(final StandardSlidingMenu$GenreRowHolder standardSlidingMenu$GenreRowHolder, final boolean b) {
        super.updateAdapterViews(standardSlidingMenu$GenreRowHolder, b);
        standardSlidingMenu$GenreRowHolder.tv.setTextColor(this.kidsItemTextColor);
        final TextView tv = standardSlidingMenu$GenreRowHolder.tv;
        final Typeface typeface = standardSlidingMenu$GenreRowHolder.tv.getTypeface();
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        tv.setTypeface(typeface, n);
        standardSlidingMenu$GenreRowHolder.tv.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, this.activity.getResources().getDimensionPixelSize(2131362160)));
        ViewUtils.removeShadow(standardSlidingMenu$GenreRowHolder.tv);
    }
    
    @Override
    protected void updateSwitchProfileButton() {
        super.updateSwitchProfileButton();
        this.switchProfilesIcon.setImageResource(2130837694);
    }
}
