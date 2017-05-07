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
import java.util.List;
import java.util.TreeSet;

public class PasswordSpecification$zza
{
    private final TreeSet<Character> zzSB;
    private final List<String> zzSw;
    private final List<Integer> zzSx;
    private int zzSy;
    private int zzSz;
    
    public PasswordSpecification$zza() {
        this.zzSB = new TreeSet<Character>();
        this.zzSw = new ArrayList<String>();
        this.zzSx = new ArrayList<Integer>();
        this.zzSy = 12;
        this.zzSz = 16;
    }
    
    private void zzlL() {
        final Iterator<Integer> iterator = this.zzSx.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            n += iterator.next();
        }
        if (n > this.zzSz) {
            throw new PasswordSpecification$zzb("required character count cannot be greater than the max password size");
        }
    }
    
    private void zzlM() {
        final boolean[] array = new boolean[95];
        final Iterator<String> iterator = this.zzSw.iterator();
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
        this.zzSB.addAll(this.zzr(s, "allowedChars"));
        return this;
    }
    
    public PasswordSpecification$zza zzf(final String s, final int n) {
        if (n < 1) {
            throw new PasswordSpecification$zzb("count must be at least 1");
        }
        this.zzSw.add(zzb(this.zzr(s, "requiredChars")));
        this.zzSx.add(n);
        return this;
    }
    
    public PasswordSpecification$zza zzg(final int zzSy, final int zzSz) {
        if (zzSy < 1) {
            throw new PasswordSpecification$zzb("minimumSize must be at least 1");
        }
        if (zzSy > zzSz) {
            throw new PasswordSpecification$zzb("maximumSize must be greater than or equal to minimumSize");
        }
        this.zzSy = zzSy;
        this.zzSz = zzSz;
        return this;
    }
    
    public PasswordSpecification zzlK() {
        if (this.zzSB.isEmpty()) {
            throw new PasswordSpecification$zzb("no allowed characters specified");
        }
        this.zzlL();
        this.zzlM();
        return new PasswordSpecification(1, zzb(this.zzSB), this.zzSw, this.zzSx, this.zzSy, this.zzSz);
    }
}
