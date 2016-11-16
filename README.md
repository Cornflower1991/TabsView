动态添加底部Tab , 可以添加的方式有

addFontTab  矢量图片

addImageTab 网络url  网络图片这里为了方便采用的Glide加载.可根据自己需求修改

addImagePngTab 本地png图片

###使用方法

//矢量图片

        tabsView.addFontTab(getString(R.string.tab_recommend), FontDrawable.TAB_HOME_1004, FontDrawable.TAB_HOME_SELECTED_1005);
        tabsView.addFontTab(getString(R.string.tab_game), FontDrawable.TAB_GAME_1006, FontDrawable.TAB_GAME_SELECTED_1007);
        tabsView.addFontTab(getString(R.string.tab_software), FontDrawable.TAB_SOFTWARE_1008, FontDrawable.TAB_SOFTWARE_SELECTED_1009);

 //本地图片

        tabsView.addImagePngTab(getString(R.string.tab_recommend),R.drawable.shouye,R.drawable.shouye_select);
        tabsView.addImagePngTab(getString(R.string.tab_game),R.drawable.tabhome,R.drawable.tabhome_select);
        tabsView.addImagePngTab(getString(R.string.tab_software),R.drawable.gerenzhongxin,R.drawable.gerenzhongxin_select);



添加单击 双击 监听

     tabsView.setOnCheckedChangedListener(new TabsView.OnCheckedChangedListener() {
            @Override
            public void onChecked(int position) {

            }
        });
        tabsView.setOnDoubleClickTabListener(new TabsView.OnDoubleClickTabListener() {
            @Override
            public void onDoubleClickTab(int position) {

            }
        });