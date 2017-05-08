// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json.zip;

class TrieKeep$Node implements PostMortem
{
    private int integer;
    private TrieKeep$Node[] next;
    final /* synthetic */ TrieKeep this$0;
    
    public TrieKeep$Node(final TrieKeep this$0) {
        this.this$0 = this$0;
        this.integer = -1;
        this.next = null;
    }
    
    public TrieKeep$Node get(final byte b) {
        return this.get(b & 0xFF);
    }
    
    public TrieKeep$Node get(final int n) {
        if (this.next == null) {
            return null;
        }
        return this.next[n];
    }
    
    @Override
    public boolean postMortem(final PostMortem postMortem) {
        final TrieKeep$Node trieKeep$Node = (TrieKeep$Node)postMortem;
        if (trieKeep$Node == null) {
            JSONzip.log("\nMisalign");
        }
        else {
            if (this.integer != trieKeep$Node.integer) {
                JSONzip.log("\nInteger " + this.integer + " <> " + trieKeep$Node.integer);
                return false;
            }
            if (this.next != null) {
                for (int i = 0; i < 256; ++i) {
                    final TrieKeep$Node trieKeep$Node2 = this.next[i];
                    if (trieKeep$Node2 != null) {
                        if (!trieKeep$Node2.postMortem(trieKeep$Node.next[i])) {
                            return false;
                        }
                    }
                    else if (trieKeep$Node.next[i] != null) {
                        JSONzip.log("\nMisalign " + i);
                        return false;
                    }
                }
                return true;
            }
            if (trieKeep$Node.next == null) {
                return true;
            }
            JSONzip.log("\nNext is null " + this.integer);
            return false;
        }
        return false;
    }
    
    public void set(final byte b, final TrieKeep$Node trieKeep$Node) {
        this.set(b & 0xFF, trieKeep$Node);
    }
    
    public void set(final int n, final TrieKeep$Node trieKeep$Node) {
        if (this.next == null) {
            this.next = new TrieKeep$Node[256];
        }
        this.next[n] = trieKeep$Node;
    }
    
    public TrieKeep$Node vet(final byte b) {
        return this.vet(b & 0xFF);
    }
    
    public TrieKeep$Node vet(final int n) {
        TrieKeep$Node value;
        if ((value = this.get(n)) == null) {
            value = new TrieKeep$Node(this.this$0);
            this.set(n, value);
        }
        return value;
    }
}
