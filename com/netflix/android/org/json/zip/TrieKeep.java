// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json.zip;

import com.netflix.android.org.json.Kim;

class TrieKeep extends Keep
{
    private int[] froms;
    private Kim[] kims;
    private TrieKeep$Node root;
    private int[] thrus;
    
    public TrieKeep(final int n) {
        super(n);
        this.froms = new int[this.capacity];
        this.thrus = new int[this.capacity];
        this.kims = new Kim[this.capacity];
        this.root = new TrieKeep$Node(this);
    }
    
    public Kim kim(final int n) {
        final Kim kim = this.kims[n];
        final int n2 = this.froms[n];
        final int n3 = this.thrus[n];
        if (n2 == 0) {
            final Kim kim2 = kim;
            if (n3 == kim.length) {
                return kim2;
            }
        }
        final Kim kim2 = new Kim(kim, n2, n3);
        this.froms[n] = 0;
        this.thrus[n] = kim2.length;
        this.kims[n] = kim2;
        return kim2;
    }
    
    public int length(final int n) {
        return this.thrus[n] - this.froms[n];
    }
    
    public int match(final Kim kim, int i, final int n) {
        TrieKeep$Node trieKeep$Node = this.root;
        int n2 = i;
        int access$000 = -1;
        while (i < n) {
            trieKeep$Node = trieKeep$Node.get(kim.get(i));
            if (trieKeep$Node == null) {
                break;
            }
            if (trieKeep$Node.integer != -1) {
                access$000 = trieKeep$Node.integer;
            }
            ++n2;
            ++i;
        }
        return access$000;
    }
    
    @Override
    public boolean postMortem(final PostMortem postMortem) {
        boolean b = true;
        final TrieKeep trieKeep = (TrieKeep)postMortem;
        if (this.length != trieKeep.length) {
            JSONzip.log("\nLength " + this.length + " <> " + trieKeep.length);
            return false;
        }
        if (this.capacity != trieKeep.capacity) {
            JSONzip.log("\nCapacity " + this.capacity + " <> " + trieKeep.capacity);
            return false;
        }
        int i = 0;
        boolean b2 = true;
        while (i < this.length) {
            final Kim kim = this.kim(i);
            final Kim kim2 = trieKeep.kim(i);
            if (!kim.equals(kim2)) {
                JSONzip.log("\n[" + i + "] " + kim + " <> " + kim2);
                b2 = false;
            }
            ++i;
        }
        if (!b2 || !this.root.postMortem(trieKeep.root)) {
            b = false;
        }
        return b;
    }
    
    public void registerMany(final Kim kim) {
        int n = 40;
        final int length = kim.length;
        final int n2 = this.capacity - this.length;
        if (n2 <= 40) {
            n = n2;
        }
        final int n3 = 0;
        int n4 = n;
    Label_0190:
        for (int i = n3; i < length - 2; ++i) {
            int n5;
            if ((n5 = length - i) > 10) {
                n5 = 10;
            }
            TrieKeep$Node trieKeep$Node = this.root;
            int n6;
            for (int j = i; j < n5 + i; ++j, n4 = n6) {
                trieKeep$Node = trieKeep$Node.vet(kim.get(j));
                n6 = n4;
                if (trieKeep$Node.integer == -1) {
                    n6 = n4;
                    if (j - i >= 2) {
                        trieKeep$Node.integer = this.length;
                        this.uses[this.length] = 1L;
                        this.kims[this.length] = kim;
                        this.froms[this.length] = i;
                        this.thrus[this.length] = j + 1;
                        ++this.length;
                        if ((n6 = n4 - 1) <= 0) {
                            break Label_0190;
                        }
                    }
                }
            }
        }
    }
    
    public int registerOne(final Kim kim, final int n, final int n2) {
        int length;
        final int n3 = length = -1;
        if (this.length < this.capacity) {
            TrieKeep$Node trieKeep$Node = this.root;
            for (int i = n; i < n2; ++i) {
                trieKeep$Node = trieKeep$Node.vet(kim.get(i));
            }
            length = n3;
            if (trieKeep$Node.integer == -1) {
                length = this.length;
                trieKeep$Node.integer = length;
                this.uses[length] = 1L;
                this.kims[length] = kim;
                this.froms[length] = n;
                this.thrus[length] = n2;
                ++this.length;
            }
        }
        return length;
    }
    
    public void registerOne(final Kim kim) {
        final int registerOne = this.registerOne(kim, 0, kim.length);
        if (registerOne != -1) {
            this.kims[registerOne] = kim;
        }
    }
    
    public void reserve() {
        if (this.capacity - this.length < 40) {
            this.root = new TrieKeep$Node(this);
            int n = 0;
            int n2;
            for (int i = 0; i < this.capacity; ++i, n = n2) {
                n2 = n;
                if (this.uses[i] > 1L) {
                    final Kim kim = this.kims[i];
                    final int n3 = this.thrus[i];
                    TrieKeep$Node trieKeep$Node = this.root;
                    for (int j = this.froms[i]; j < n3; ++j) {
                        trieKeep$Node = trieKeep$Node.vet(kim.get(j));
                    }
                    trieKeep$Node.integer = n;
                    this.uses[n] = Keep.age(this.uses[i]);
                    this.froms[n] = this.froms[i];
                    this.thrus[n] = n3;
                    this.kims[n] = kim;
                    n2 = n + 1;
                }
            }
            int k = n;
            if (this.capacity - n < 40) {
                this.power = 0;
                this.root = new TrieKeep$Node(this);
                k = 0;
            }
            this.length = k;
            while (k < this.capacity) {
                this.uses[k] = 0L;
                this.kims[k] = null;
                this.froms[k] = 0;
                this.thrus[k] = 0;
                ++k;
            }
        }
    }
    
    @Override
    public Object value(final int n) {
        return this.kim(n);
    }
}
