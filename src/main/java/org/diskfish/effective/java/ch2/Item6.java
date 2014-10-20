package org.diskfish.effective.java.ch2;

import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.WeakHashMap;

/**
* Item 6: Eliminate obsolete object references
*/

/*
* 'Obsolete references' are refs that will never be dereferenced again
* If a class manages its own memory( e.g. a 'size' variable that determines
* the active portion of an array), you should null out any object refs you
* know are no longer needed, if they could otherwise hang around..
* 
*  noLongerNeeded = null;
*/

/*
* Strong and Weak References
* 
* if an object is reachable via a chain of strong references (strongly reachable), 
*  it is *not* eligible for garbage collection.
* 
* A weak reference, simply put, is a reference that isn't strong enough to 
*  force an object to remain in memory.
* 
*  e.g. WeakReference<Widget> weakWidget = new WeakReference<Widget>(widget);
*/
public class Item6 {
    
    public static void main(String[] args) {
        // weak keys; entries are made viable for garbage collection once the keys 
        //  are no longer referenced
        // *not* synchronized; see Collections.synchronizedMap for a sync'd version
        // See also WeakReferences..
        // Useful for caches, if it's still cached, great, otherwise just go to the
        //  main store..
        
        // -- Fill a weak hash map with one entry
        WeakHashMap<Date, String> map = new WeakHashMap<>();
        Date todayKey = new Date();
        map.put(todayKey, todayKey.toString());
        System.out.println("map contains today's date ? " + map.containsKey(todayKey));

        // -- now make today's date elligible for garbage collection by nulling
        //  our reference to the key object in map
        todayKey = null;
        int i = 0;
        
        // run until garbage collection reclaims the entry in map
        //  (could force instead of looping with a System.gc())
        do {
            if (!map.isEmpty()) {
                System.out.println("At iteration " + i + " the map still holds the reference on someDataObject");
            } else {
                System.out.println("somDataObject has finally been garbage collected at iteration " + i + ", hence the map is now empty");
                break;
            }
            i++;
        } while(true);
        
        // e.g. weak references (which is how the keys are stored in a WeakHashMap)
        WeakReference<Date> today = new WeakReference<>(new Date());
        assert(today.get() != null);
        System.out.println("today = " + today.get().toString());
        System.gc();
        assert(today.get() == null);
        System.out.println("Today's Date has been garbage collected");
        
        // See also SoftReference (like weak, but the GC decides when it needs
        //  their memory back. And, 'As long as the referent of a soft reference 
        //  is strongly reachable, that is, is actually in use, the soft reference 
        //  will not be cleared. Thus a sophisticated cache can, for example, 
        //  prevent its most recently used entries from being discarded by keeping 
        //  strong referents to those entries, leaving the remaining entries to 
        //  be discarded at the discretion of the garbage collector.
    }
}
