package com.wawan.dompetku.di.module;

import androidx.fragment.app.Fragment;


import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {
    private Fragment fragment;

    public FragmentModule(Fragment fragment){
        this.fragment = fragment;
    }

    @Provides
    public Fragment provideFragment() {
        return fragment;
    }
    // add more
}
