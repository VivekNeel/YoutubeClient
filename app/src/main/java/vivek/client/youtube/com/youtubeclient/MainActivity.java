package vivek.client.youtube.com.youtubeclient;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.github.clans.fab.FloatingActionButton;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vivek.client.youtube.com.youtubeclient.activities.AboutDeveloperActivity;
import vivek.client.youtube.com.youtubeclient.adapters.CustomActivityViewPagerAdapter;
import vivek.client.youtube.com.youtubeclient.fragments.ChannelListFragment;
import vivek.client.youtube.com.youtubeclient.fragments.LatestVideoFragment;
import vivek.client.youtube.com.youtubeclient.models.Channel;
import vivek.client.youtube.com.youtubeclient.models.Item;
import vivek.client.youtube.com.youtubeclient.utils.AdapterUtils;

public class MainActivity extends AppCompatActivity implements ChannelListFragment.IChannelSubscription {

    private static final String TAG_PLAYLIST = "playlist";
    private static final String TAG_LATEST_VIDEOS = "videos";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Playlist");
        setupDrawer();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle bundle = intent.getExtras();
    }

    private void switchFragments(String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch (tag) {
            case TAG_PLAYLIST:
                fragmentTransaction.replace(R.id.container, ChannelListFragment.newInstance(), TAG_PLAYLIST);
                break;
            case TAG_LATEST_VIDEOS:
                fragmentTransaction.replace(R.id.container, LatestVideoFragment.newInstance(), TAG_PLAYLIST);
                break;
        }

        fragmentTransaction.commit();
    }

    private void setupDrawer() {
        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem playlist = new PrimaryDrawerItem().withIdentifier(0).withName(R.string.cateogry_playlist).withIcon(R.drawable.playlist);
        PrimaryDrawerItem latestVideos = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.category_latestvideo).withIcon(R.drawable.youtube);
        PrimaryDrawerItem website = new PrimaryDrawerItem().withIdentifier(6).withName(R.string.item_website).withIcon(R.drawable.website);
        PrimaryDrawerItem photos = new PrimaryDrawerItem().withIdentifier(7).withName(R.string.item_photos).withIcon(R.drawable.photos);


        PrimaryDrawerItem facebook = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.item_facebook).withIcon(R.drawable.facebook);
        PrimaryDrawerItem twitter = new PrimaryDrawerItem().withIdentifier(3).withName(R.string.item_twitter).withIcon(R.drawable.twitter);
        PrimaryDrawerItem instagram = new PrimaryDrawerItem().withIdentifier(4).withName(R.string.item_instagram).withIcon(R.drawable.insta);
        PrimaryDrawerItem googlePlus = new PrimaryDrawerItem().withIdentifier(5).withName(R.string.item_gplus).withIcon(R.drawable.gplus);


        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.about_header)
                .build();
        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new SectionDrawerItem().withName("Youtube"),
                        playlist,
                        latestVideos,
                        new SectionDrawerItem().withName(R.string.category_website),
                        website,
                        photos,
                        new SectionDrawerItem().withName(R.string.category_social),
                        facebook,
                        twitter,
                        instagram,
                        googlePlus
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        long id = drawerItem.getIdentifier();
                        if (id == 0) {
                            toolbar.setTitle("Playlist");
                            switchFragments(TAG_PLAYLIST);
                        } else if (id == 1) {
                            toolbar.setTitle("Latest Videos");
                            switchFragments(TAG_LATEST_VIDEOS);
                        } else if (id == 2) {
                            AdapterUtils.openUrlInBrowser("YOUR URL", MainActivity.this);

                        } else if (id == 3) {
                            AdapterUtils.openUrlInBrowser("YOUR URL", MainActivity.this);

                        } else if (id == 4) {
                            AdapterUtils.openUrlInBrowser("YOUR URL", MainActivity.this);

                        } else if (id == 5) {
                            AdapterUtils.openUrlInBrowser("YOUR URL", MainActivity.this);

                        } else if (id == 6) {
                            AdapterUtils.openUrlInBrowser("YOUR WEBSITE URL", MainActivity.this);
                        } else if (id == 7) {
                            AdapterUtils.openUrlInBrowser("YOUR WEBSITE URL", MainActivity.this);
                        }
                        return false;
                    }
                })
                .build();

        result.setSelection(1);

    }

    @Override
    public void sendSubscriptionInstance() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.about) {
            Intent intent = new Intent(MainActivity.this, AboutDeveloperActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }
}
