// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.util.ArrayList;

final class BackStackRecord$Op
{
    int cmd;
    int enterAnim;
    int exitAnim;
    Fragment fragment;
    BackStackRecord$Op next;
    int popEnterAnim;
    int popExitAnim;
    BackStackRecord$Op prev;
    ArrayList<Fragment> removed;
}
