package com.hrsthrt74.jump;

import android.content.ComponentName;
import android.content.Intent;

/** 桌面图标：查找设备 */
public class FindDeviceActivity extends JumpActivity {

    private static final String PKG = "com.xiaomi.finddevice";

    @Override
    protected Intent[] targets() {
        Intent fallback = getPackageManager().getLaunchIntentForPackage(PKG);
        return new Intent[]{
                new Intent(PKG + ".FINDDEVICE_MAIN")
                        .setComponent(new ComponentName(PKG, PKG + ".ui.FindMainActivity")),
                new Intent(Intent.ACTION_MAIN)
                        .setComponent(new ComponentName(PKG, PKG + ".ui.FindMyShortCutsActivity")),
                fallback != null ? fallback : new Intent(Intent.ACTION_MAIN).setPackage(PKG)
        };
    }

    @Override
    protected String missingHint() {
        return "没找到「查找设备」";
    }
}
