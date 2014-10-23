package org.diskfish.effective.java.ch2;

/**
 * Item 2: Consider a Builder when faced with a many arg'd constructor
 * 
 * Telescoping constructors can be unwieldy and unreadable, while JavaBean style 
 * setter interfaces allow an object to assume an inconsistent state and
 * preclude immutability.
 * 
 * Builders provide both readable client code, and real immutability.
 * 
 * TODO: Explore invariant checking and enforcement
 * TODO: Explore a builder's progress being concrete (immutable) as it builds
 */
public class Item2 {
    private final int required;
    private final int optional0;
    private final int optional1;
    static final int DEFAULT_OPTIONAL = 42;
    
    public static class Builder {
        private final int required;
        private int optional0 = DEFAULT_OPTIONAL;
        private int optional1 = DEFAULT_OPTIONAL;
        
        public Builder(int required) {
            this.required = required;
        }
        
        public Builder optional0(int optional0) {
            this.optional0 = optional0;
            return this;
        }
        
        public Builder optional1(int optional1) {
            this.optional1 = optional1;
            return this;
        }
        
        public Item2 build() {
            return new Item2(this);
        }
    }
    
    private Item2(Builder builder) {
        required = builder.required;
        optional0 = builder.optional0;
        optional1 = builder.optional1;
    }
    
    public int getRequired() {
        return required;
    }
    
    public int getOptional0() {
        return optional0;
    }
    
    public int getOptional1() {
        return optional1;
    }
    
    /* etc. */
}
