package Adapter.ui.main;

import android.content.Context;

import com.example.easychoices.FragmentHalal;
import com.example.easychoices.FragmentNonHalal;
import com.example.easychoices.R;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    String mTitle = "";
    String mUrl = "";

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm, String title, String url) {
        super(fm);
        mContext = context;

        mTitle = title;
        mUrl = url;
    }

    @Override

    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        if (position == 2)
            return FragmentNonHalal.newInstance(mTitle, mUrl);

        else
            return FragmentHalal.newInstance(mTitle, mUrl);
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}