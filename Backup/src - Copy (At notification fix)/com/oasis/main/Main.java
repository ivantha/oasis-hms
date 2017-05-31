package com.oasis.main;

import com.sun.javafx.application.LauncherImpl;
import preloader.OasisPreloader;

public class Main {
    public static void main(String args[]){
//        LauncherImpl.launchApplication(OasisApplication.class, OasisPreloader.class, args);
        LauncherImpl.launchApplication(OasisApplication.class, args);
    }
}
