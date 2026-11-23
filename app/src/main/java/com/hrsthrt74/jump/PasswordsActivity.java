package com.hrsthrt74.jump;

import android.content.ComponentName;
import android.content.Intent;

/** 桌面图标：密码管理 */
public class PasswordsActivity extends JumpActivity {

    private static final String PKG = "com.miui.passwords";

    @Override
    protected Intent[] targets() {
        Intent fallback = getPackageManager().getLaunchIntentForPackage(PKG);
        return new Intent[]{
                // 正常入口，需要 com.miui.passwords.permission.OPEN_SETTINGS（normal 级，安装即授予）
                new Intent(PKG + ".action.OPEN_PASSWORDS_MAIN")
                        .setComponent(new ComponentName(PKG, PKG + ".PasswordMainActivity")),
                new Intent(Intent.ACTION_MAIN)
                        .setComponent(new ComponentName(PKG, PKG + ".PasswordMainActivity")),
                fallback != null ? fallback : new Intent(Intent.ACTION_MAIN).setPackage(PKG)
        };
    }

    @Override
    protected String missingHint() {
        return "没找到「密码管理」";
    }
}
