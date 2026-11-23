package com.hrsthrt74.jump;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * 透明外壳 Activity：点击桌面图标后立刻把目标应用拉起来，然后自己退出。
 * 界面上看不见任何东西，表现得就像直接打开了目标应用。
 */
public abstract class JumpActivity extends Activity {

    /** 按优先级返回候选 Intent，依次尝试。 */
    protected abstract Intent[] targets();

    /** 全部候选都失败时的提示。 */
    protected abstract String missingHint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean launched = false;
        for (Intent intent : targets()) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            try {
                startActivity(intent);
                launched = true;
                break;
            } catch (Exception ignored) {
                // 换下一个候选
            }
        }

        finish();
        overridePendingTransition(0, 0);

        if (!launched) {
            Toast.makeText(getApplicationContext(), missingHint(), Toast.LENGTH_LONG).show();
        }
    }
}
