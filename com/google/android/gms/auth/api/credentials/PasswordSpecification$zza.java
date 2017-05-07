// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import java.util.Arrays;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.Random;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collection;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.List;

public class PasswordSpecification$zza
{
    private final List<String> zzRr;
    private final List<Integer> zzRs;
    private int zzRt;
    private int zzRu;
    private final TreeSet<Character> zzRw;
    
    public PasswordSpecification$zza() {
        this.zzRw = new TreeSet<Character>();
        this.zzRr = new ArrayList<String>();
        this.zzRs = new ArrayList<Integer>();
        this.zzRt = 12;
        this.zzRu = 16;
    }
    
    private void zzly() {
        final Iterator<Integer> iterator = this.zzRs.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            n += iterator.next();
        }
        if (n > this.zzRu) {
            throw new PasswordSpecification$zzb("required character count cannot be greater than the max password size");
        }
    }
    
    private void zzlz() {
        final boolean[] array = new boolean[95];
        final Iterator<String> iterator = this.zzRr.iterator();
        while (iterator.hasNext()) {
            final char[] charArray = iterator.next().toCharArray();
            for (int length = charArray.length, i = 0; i < length; ++i) {
                final char c = charArray[i];
                if (array[c - ' ']) {
                    throw new PasswordSpecification$zzb("character " + c + " occurs in more than one required character set");
                }
                array[c - ' '] = true;
            }
        }
    }
    
    private TreeSet<Character> zzr(final String s, final String s2) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new PasswordSpecification$zzb(s2 + " cannot be null or empty");
        }
        final TreeSet<Character> set = new TreeSet<Character>();
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char c = charArray[i];
            if (zzb(c, 32, 126)) {
                throw new PasswordSpecification$zzb(s2 + " must only contain ASCII printable characters");
            }
            set.add(c);
        }
        return set;
    }
    
    public PasswordSpecification$zza zzbD(final String s) {
        this.zzRw.addAll(this.zzr(s, "allowedChars"));
        return this;
    }
    
    public PasswordSpecification$zza zzf(final String s, final int n) {
        if (n < 1) {
            throw new PasswordSpecification$zzb("count must be at least 1");
        }
        this.zzRr.add(zzb(this.zzr(s, "requiredChars")));
        this.zzRs.add(n);
        return this;
    }
    
    public PasswordSpecification$zza zzh(final int zzRt, final int zzRu) {
        if (zzRt < 1) {
            throw new PasswordSpecification$zzb("minimumSize must be at least 1");
        }
        if (zzRt > zzRu) {
            throw new PasswordSpecification$zzb("maximumSize must be greater than or equal to minimumSize");
        }
        this.zzRt = zzRt;
        this.zzRu = zzRu;
        return this;
    }
    
    public PasswordSpecification zzlx() {
        if (this.zzRw.isEmpty()) {
            throw new PasswordSpecification$zzb("no allowed characters specified");
        }
        this.zzly();
        this.zzlz();
        return new PasswordSpecification(1, zzb(this.zzRw), this.zzRr, this.zzRs, this.zzRt, this.zzRu);
    }
}
