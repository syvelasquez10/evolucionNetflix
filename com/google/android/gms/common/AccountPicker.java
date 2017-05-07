// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import android.os.Parcelable;
import java.io.Serializable;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import android.accounts.Account;

public final class AccountPicker
{
    public static Intent a(final Account account, final ArrayList<Account> list, final String[] array, final boolean b, final String s, final String s2, final String[] array2, final Bundle bundle, final boolean b2) {
        return a(account, list, array, b, s, s2, array2, bundle, b2, 0, 0);
    }
    
    public static Intent a(final Account account, final ArrayList<Account> list, final String[] array, final boolean b, final String s, final String s2, final String[] array2, final Bundle bundle, final boolean b2, final int n, final int n2) {
        final Intent intent = new Intent();
        intent.setAction("com.google.android.gms.common.account.CHOOSE_ACCOUNT");
        intent.putExtra("allowableAccounts", (Serializable)list);
        intent.putExtra("allowableAccountTypes", array);
        intent.putExtra("addAccountOptions", bundle);
        intent.putExtra("selectedAccount", (Parcelable)account);
        intent.putExtra("alwaysPromptForAccount", b);
        intent.putExtra("descriptionTextOverride", s);
        intent.putExtra("authTokenType", s2);
        intent.putExtra("addAccountRequiredFeatures", array2);
        intent.putExtra("setGmsCoreAccount", b2);
        intent.putExtra("overrideTheme", n);
        intent.putExtra("overrideCustomTheme", n2);
        return intent;
    }
    
    public static Intent newChooseAccountIntent(final Account account, final ArrayList<Account> list, final String[] array, final boolean b, final String s, final String s2, final String[] array2, final Bundle bundle) {
        return a(account, list, array, b, s, s2, array2, bundle, false);
    }
}
