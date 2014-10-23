package org.diskfish.effective.java.ch2;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class Item2Test {
    
    @Test
    public void testMaterialBuilderBuild() {
        /* Build an immutable Item2 with one required and one optional param */
        Item2 theImmutableChewie = new Item2.Builder(1).optional0(2).build();
        assertTrue(theImmutableChewie.getRequired() == 1);
        assertTrue(theImmutableChewie.getOptional0() == 2);
        
        /* Check the un-set(built) optional param is the expected default */
        assertTrue(theImmutableChewie.getOptional1() == Item2.DEFAULT_OPTIONAL);
    }
    
}
