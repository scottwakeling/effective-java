package org.diskfish.effective.java.ch2;

import java.lang.IllegalStateException;

/**
 * Item 4: Enforce non-instantiability with a private constructor
 */
public class Item4 {
    private Item4() {
    	throw new IllegalStateException("Direct instantiation of Item4 not allowed.");
    }
}
