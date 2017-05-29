package com.example.user.klimr;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;

public class Utils {
    public static void tintMenuItemIcons(Menu menu, int color, int alpha) {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            Drawable icon = item.getIcon();
            if (icon != null) {
                icon.mutate();
                icon.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
                // icon.setAlpha(alpha);
                item.setIcon(icon);
            }
        }
    }
}
