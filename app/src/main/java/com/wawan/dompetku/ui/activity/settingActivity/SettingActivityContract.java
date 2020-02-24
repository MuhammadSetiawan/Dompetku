package com.wawan.dompetku.ui.activity.settingActivity;

import com.wawan.dompetku.base.BaseContract;

public class SettingActivityContract {
    public interface View extends BaseContract.View {
        public void showProgress(Boolean show);
        public void showError(String error);
    }

    public interface Presenter extends BaseContract.Presenter<View> {

    }
}