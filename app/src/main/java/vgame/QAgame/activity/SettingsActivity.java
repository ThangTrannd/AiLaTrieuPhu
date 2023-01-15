package vgame.QAgame.activity;


import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.view.WindowManager;
import android.widget.ToggleButton;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;

import vgame.QAgame.App;
import vgame.QAgame.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsActivity extends Activity implements View.OnClickListener {
    private ToggleButton togMusic;
    private ToggleButton togSound;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        togMusic = ((ToggleButton)findViewById(R.id.tog_music));
        togSound = ((ToggleButton)findViewById(R.id.tog_sound));
        setBgTogMusic(App.getMusicPlayer().getStateMusic());
        setBgTogSound(App.getMusicPlayer().getStateSound());

        togMusic.setOnClickListener(this);
        togSound.setOnClickListener(this);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        adsListener();
    }

    public void setBgTogMusic(boolean state){
        if(state){
            togMusic.setBackgroundResource(R.drawable.toggle_button_on);
            togMusic.setChecked(true);
        }else{
            togMusic.setBackgroundResource(R.drawable.toggle_button_off);
            togMusic.setChecked(false);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAdView != null) {
            mAdView.pause();
        }
        App.getMusicPlayer().pauseBgMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
        App.getMusicPlayer().resumeBgMusic();

    }

    public void setBgTogSound(boolean state){
        if(state){
            togSound.setBackgroundResource(R.drawable.toggle_button_on);
            togSound.setChecked(true);
        }else{
            togSound.setBackgroundResource(R.drawable.toggle_button_off);
            togSound.setChecked(false);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tog_music:
                if(togMusic.isChecked()){
                    togMusic.setBackgroundResource(R.drawable.toggle_button_on);
                    togMusic.setChecked(true);
                    App.getMusicPlayer().setStateMusic(true);
                    App.getMusicPlayer().playBgMusic(R.raw.bgmusic);
                }else{
                    togMusic.setBackgroundResource(R.drawable.toggle_button_off);
                    togMusic.setChecked(false);
                    App.getMusicPlayer().stopBgMusic();
                }
                break;
            case R.id.tog_sound:
                setBgTogSound(togSound.isChecked());
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        App.getMusicPlayer().setting(togMusic.isChecked(), togSound.isChecked());

    }

    private void adsListener(){
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
