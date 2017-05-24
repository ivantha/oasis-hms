package com.oasis.utils;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.LowLevelKeyboardProc;
import com.sun.jna.platform.win32.WinUser.MSG;


public class KeyHook {
    private static HHOOK hhk;
    private static LowLevelKeyboardProc keyboardHook;
    private static User32 lib;

    public static void blockWindowsKey() {
        if (isWindows()) {
            new Thread(() -> {
                lib = User32.INSTANCE;
                HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);
                keyboardHook = (nCode, wParam, info) -> {
                    if (nCode >= 0) {
                        switch (info.vkCode) {
                            case 0x5B:
                            case 0x5C:
                                return new LRESULT(1);
                            default: //do nothing
                        }
                    }
                    return lib.CallNextHookEx(hhk, nCode, wParam, info.getPointer());
                };
                hhk = lib.SetWindowsHookEx(13, keyboardHook, hMod, 0);

                // This bit never returns from GetMessage
                int result;
                MSG msg = new MSG();
                while ((result = lib.GetMessage(msg, null, 0, 0)) != 0) {
                    if (result == -1) {
                        break;
                    } else {
                        lib.TranslateMessage(msg);
                        lib.DispatchMessage(msg);
                    }
                }
                lib.UnhookWindowsHookEx(hhk);
            }).start();
        }
    }

    public static void unblockWindowsKey() {
        if (isWindows() && lib != null) {
            lib.UnhookWindowsHookEx(hhk);
        }
    }

    public static boolean isWindows() {
        String os = System.getProperty("os.name").toLowerCase();
        return (os.indexOf("win") >= 0);
    }
}

