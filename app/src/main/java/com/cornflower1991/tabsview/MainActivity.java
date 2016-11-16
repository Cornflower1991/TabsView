package com.cornflower1991.tabsview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.cornflower1991.tabsView_lib.FontDrawable;
import com.cornflower1991.tabsView_lib.TabsView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        final TabsView tabsView = (TabsView) findViewById(R.id.tabs_mainActivity);
//        tabsView.addFontTab(getString(com.cornflower1991.tabsview.lib.R.string.tab_recommend), FontDrawable.TAB_HOME_1004, FontDrawable.TAB_HOME_SELECTED_1005);
//        tabsView.addFontTab(getString(com.cornflower1991.tabsview.lib.R.string.tab_game), FontDrawable.TAB_GAME_1006, FontDrawable.TAB_GAME_SELECTED_1007);
//        tabsView.addFontTab(getString(com.cornflower1991.tabsview.lib.R.string.tab_software), FontDrawable.TAB_SOFTWARE_1008, FontDrawable.TAB_SOFTWARE_SELECTED_1009);
//        tabsView.selectedTab(0);
        tabsView.addImagePngTab(getString(com.cornflower1991.tabsview.lib.R.string.tab_recommend),R.drawable.shouye,R.drawable.shouye_select);
        tabsView.addImagePngTab(getString(com.cornflower1991.tabsview.lib.R.string.tab_game),R.drawable.tabhome,R.drawable.tabhome_select);
        tabsView.addImagePngTab(getString(com.cornflower1991.tabsview.lib.R.string.tab_software),R.drawable.gerenzhongxin,R.drawable.gerenzhongxin_select);
        tabsView.selectedTab(0);
        tabsView.setOnCheckedChangedListener(new TabsView.OnCheckedChangedListener() {
            @Override
            public void onChecked(int position) {
                Toast.makeText(MainActivity.this,position+"",Toast.LENGTH_SHORT).show();
            }
        });

    }
}