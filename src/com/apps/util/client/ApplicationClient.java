package com.apps.util.client;

import com.apps.util.SplashApp;

class ApplicationClient implements SplashApp {

    @Override
    public void start() {
        System.out.println("\nW E L C O M E   T O   T H E   T E S T   A P P L I C A T I O N\n");
    }

    /**
     * NOTE: you *must* call welcome() before you call start().
     *  If you don't call welcome(), the initial splash image will flash very quickly and then be gone.
     *  If you only want the initial splash image displayed, then simply call welcome() with no 'imageFiles' arguments.
     *  You can still provide a custom pause interval.
     * 
     * client.welcome();      // initial image only, default pause interval
     * client.welcome(3500);  // initial image only, custom pause interval
     * 
     * // two additional images, default pause interval
     * client.welcome("images/credits.jpg", "images/java.png");
     
     * // two additional images, custom pause interval
     * client.welcome(3500, "images/credits.jpg", "images/java.png");
     */
    public static void main(String[] args) {
        ApplicationClient client = new ApplicationClient();
        
        // client.welcome(5000);
        client.welcome(2000, "images/credits.jpg", "images/java.png");
        
        client.start();
    }
}