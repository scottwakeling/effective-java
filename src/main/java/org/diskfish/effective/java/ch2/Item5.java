package org.diskfish.effective.java.ch2;

/**
 * Item 5: Avoid creating unnecessary objects
 *
 * Init blocks and final methods are two alternatives:
 * 
 * - Init blocks {} (like static but without the keyword) - The Java compiler copies 
 *   init blocks into every constructor
 * 
 * - Or, protected final varType initializeInstanceVariable() { // init code goes here }
 * 
 * - especially useful if subclasses might want to reuse the init method. The method 
 *   is final because calling non-final methods during instance initialization can cause 
 *   problems
 */
public class Item5 {
    public static void main(String[] args) {
        
        /* Prefer primitives to boxed primitives, and watch out for unintentional autoboxing */
        {
            long start = System.currentTimeMillis();
            Long sum = 0L;
            for (long i = 0; i < Integer.MAX_VALUE; i++) {
                sum += i;
            }
            System.out.println("Autoboxing version: " + sum + " - took " + (System.currentTimeMillis() - start) + "ms");
        } // approx. 7.4 seconds
        
        {
            long start = System.currentTimeMillis();
            long sum = 0L; // avoid autoboxing
            for (int i = 0; i < Integer.MAX_VALUE; i++) { // and i as int is faster again
                sum += i;
            }
            System.out.println("Primitives version: " + sum + " - took " + (System.currentTimeMillis() - start) + "ms");
        } // approx. 1.2 seconds (0.9 with add/mov)
    }
}

