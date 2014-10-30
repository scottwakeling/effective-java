package org.diskfish.effective.java.ch2;

/**
* Item 7: Avoid finalizers
*/

/**
 * The JLS makes no guarantee about when, or even if, finalizers will be called,
 * so they should never be used to release critical resources like file
 * descriptors or large static data caches.
 * 
 * Use an explicit release/destroy method instead (e.g. .close()), and actually,
 * once you're in the habit of that, you may as well not use finalizers at all.
 * 
 * It's hard to point at much in Java and say 'that feature is more broken and
 * less robust than its equivalent in C++', but finalizers are a good example..
 */
public class Item7 {
    
    public static class ConnectionPool {
        
        /* A private instance of an anonymous class can be used to ensure
            the enclosing class's own finalize() doesn't neglect to call
            super.finalize(). If you use such a 'guardian' you wouldn't also
            implement finalize() on ConnectionPool. When (and if ever) 
            ConnectionPool became eligible for finalization, your guardian would
            too, which performs the finalization on ConnectionPool's behalf, and
            then (or before) the JVM would finalize super. It works, it ensures
            super.finalize() isn't forgotten, but I have so little nice to say 
            about it, even without the extra object per object.. it really is 
            the best I can do to leave this commented in..
        */
        private final Object finalizerGuardian = new Object() {
            @Override
            protected void finalize() throws Throwable {
                /* Finalize enclosing instance.. */
                
                /* Note how you get a compiler warning for not calling 
                    super.finalize()? Why not use a finalizerGuardi.. oh..(!) */
            }
        };
        
        private boolean connectionsExist = false;
        
        /**
         * Finalizers are, at best, unreliable audible safety nets..
         */
        @Override
        protected void finalize() throws Throwable {
            try {
                if (connectionsExist()) {
                    /* log a warning.. "You should call this yourself; you  can't
                        rely on me to do it for you..
                    */
                    close();
                }
            } finally {
                /* Finalizers are not auto-chained like C++ destructors, so you 
                    also have to remember to call super.finalize()..
                */
                super.finalize();
            }
        }
        
        /**
         * Explicit termination method. Releases critical limited resources
         * we don't want to risk waiting on GC to get back. You must remember 
         * to call this when you're done with your ConnectionPool. (/sigh)
         */
        public void close() {
            // release connections..
        }
        
        private boolean connectionsExist() {
            return connectionsExist;
        }
    }
    
    /**
     * 
     * @param args 
     */
    public static void main (String[] args) {
        
        /* Explicit destroy methods should be used with try/finally to
            ensure they are always called.
        */
        ConnectionPool pool = new ConnectionPool();
        try {
            // do work
        }
        finally {
            pool.close();
        }
    }
}
