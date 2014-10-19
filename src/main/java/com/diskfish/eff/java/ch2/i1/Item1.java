package com.diskfish.eff.java.ch2.i1;

import com.diskfish.eff.java.ch2.i1.render.RenderService;
import com.diskfish.eff.java.ch2.i1.render.RenderServices;

/**
 * Item 1: Consider static factory methods instead of constructors
 * 
 * Static factory methods:
 * 
 * - have names and so lead to more readable client code than where constructors 
 *   with many args are used
 * 
 * - avoid the hassle of messing around with constructor arg combinations while
 *   attempting to get the range of construction options you want
 * 
 * - do not *have* to construct objects (pooling, pre-constructed instances etc)
 * 
 * - can return derived types, allowing you to keep some classes private and 
 *   expose only a very minimal API (e.g. JoglRenderService)
 * 
 */
public class Item1 {
    public static void main(String[] args) {
        /* - can return derived types, allowing some to stay private.. */
        RenderService renderer = RenderServices.newInstance();
        renderer.describe();
    }
}
