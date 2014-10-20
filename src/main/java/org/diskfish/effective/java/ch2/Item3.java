package org.diskfish.effective.java.ch2;

/**
 * Item 3: Enforce the singleton property
 */
public class Item3 {
    
    /*
     * Public final field
     */
    /*public static final Item3 INSTANCE = new Item3();
    private Item3() {
        // throw to guard against access via reflection thru AccessibleObject.setAccessible
        throw new Exception(..)
    }*/

    /*
     * Public static factory method
     * 
     * - allows you to change your mind about what happens in getInstance without changing the
     *   interface, maybe even change Item3 to not be a singleton, or single per thread etc.
     */
    private static final Item3 INSTANCE = new Item3();
    private Item3() {}
    public static Item3 getInstance() { return INSTANCE; } // most likely inlined

    /*
     * Enum type
     * 
     * - provides serialization for free
     * - guarantees against multiple instantiation (even reflection attacks)
     * - an abuse of enum (IMHO)
     */
    /*public enum Item3 {
        INSTANCE;
    }*/
}
