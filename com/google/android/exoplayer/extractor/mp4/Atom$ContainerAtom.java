// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor.mp4;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

final class Atom$ContainerAtom extends Atom
{
    public final List<Atom$ContainerAtom> containerChildren;
    public final long endPosition;
    public final List<Atom$LeafAtom> leafChildren;
    
    public Atom$ContainerAtom(final int n, final long endPosition) {
        super(n);
        this.endPosition = endPosition;
        this.leafChildren = new ArrayList<Atom$LeafAtom>();
        this.containerChildren = new ArrayList<Atom$ContainerAtom>();
    }
    
    public void add(final Atom$ContainerAtom atom$ContainerAtom) {
        this.containerChildren.add(atom$ContainerAtom);
    }
    
    public void add(final Atom$LeafAtom atom$LeafAtom) {
        this.leafChildren.add(atom$LeafAtom);
    }
    
    public int getChildAtomOfTypeCount(final int n) {
        final int n2 = 0;
        final int size = this.leafChildren.size();
        int i = 0;
        int n3 = 0;
        while (i < size) {
            if (this.leafChildren.get(i).type == n) {
                ++n3;
            }
            ++i;
        }
        for (int size2 = this.containerChildren.size(), j = n2; j < size2; ++j) {
            if (this.containerChildren.get(j).type == n) {
                ++n3;
            }
        }
        return n3;
    }
    
    public Atom$ContainerAtom getContainerAtomOfType(final int n) {
        for (int size = this.containerChildren.size(), i = 0; i < size; ++i) {
            final Atom$ContainerAtom atom$ContainerAtom = this.containerChildren.get(i);
            if (atom$ContainerAtom.type == n) {
                return atom$ContainerAtom;
            }
        }
        return null;
    }
    
    public Atom$LeafAtom getLeafAtomOfType(final int n) {
        for (int size = this.leafChildren.size(), i = 0; i < size; ++i) {
            final Atom$LeafAtom atom$LeafAtom = this.leafChildren.get(i);
            if (atom$LeafAtom.type == n) {
                return atom$LeafAtom;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return Atom.getAtomTypeString(this.type) + " leaves: " + Arrays.toString(this.leafChildren.toArray(new Atom$LeafAtom[0])) + " containers: " + Arrays.toString(this.containerChildren.toArray(new Atom$ContainerAtom[0]));
    }
}
