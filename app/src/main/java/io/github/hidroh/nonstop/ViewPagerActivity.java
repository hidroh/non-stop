package io.github.hidroh.nonstop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        Toast.makeText(this, R.string.swipe_hint, Toast.LENGTH_SHORT).show();
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return Fragment.instantiate(ViewPagerActivity.this,
                            WebFragment.class.getName());
                } else {
                    return Fragment.instantiate(ViewPagerActivity.this,
                            NestedParentFragment.class.getName());
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }
}
