package vgame.QAgame.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;

import vgame.QAgame.App;
import vgame.QAgame.R;
import vgame.QAgame.activity.HighScoreActivity;
import vgame.QAgame.activity.PlayerActivity;
import vgame.QAgame.activity.SettingsActivity;
import vgame.QAgame.dialogs.AboutDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    private AdView mAdView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.bg_circle_rotate);
        (view.findViewById(R.id.bg_circle_anim)).setAnimation(animation);
        view.findViewById(R.id.btn_setting).setOnClickListener(this);
        view.findViewById(R.id.btn_play).setOnClickListener(this);
        view.findViewById(R.id.btn_high_score).setOnClickListener(this);
        view.findViewById(R.id.btn_about).setOnClickListener(this);


        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        adsListener();
        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                App.getMusicPlayer().playBgMusic(R.raw.bgmusic);
            }
        }, 2500);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
        App.getMusicPlayer().resumeBgMusic();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mAdView != null) {
            mAdView.pause();
        }
        App.getMusicPlayer().pauseBgMusic();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_setting:
                Intent intent = new Intent(getContext(), SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_play:
                Intent intent1 = new Intent(getContext(), PlayerActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_high_score:
                Intent intent2 = new Intent(getContext(), HighScoreActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_about:
                AboutDialog aboutDialog = new AboutDialog(getContext());
                aboutDialog.show();
                break;
            default:
                break;
        }
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
}
