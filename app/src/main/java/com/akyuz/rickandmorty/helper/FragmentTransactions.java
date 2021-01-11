package com.akyuz.rickandmorty.helper;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.akyuz.rickandmorty.R;

public final class FragmentTransactions {

    public static void goFragment(String fragmentName, Fragment fragment, FragmentActivity activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragContainer, fragment).addToBackStack(fragmentName).commit();

        int count = activity.getSupportFragmentManager().getBackStackEntryCount();

        if (count != 0) {
            FragmentManager.BackStackEntry backStackEntry = activity.getSupportFragmentManager().getBackStackEntryAt(count - 1);
            if (backStackEntry.getName().contains(fragmentName)) {
                activity.getSupportFragmentManager().popBackStack();
            }
        }
    }
}
