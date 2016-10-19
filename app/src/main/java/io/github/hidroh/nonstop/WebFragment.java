package io.github.hidroh.nonstop;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebFragment extends Fragment {
    private WebView webView;
    private boolean newInstance;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        newInstance = false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        newInstance = true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (newInstance) {
            webView = (WebView) inflater.inflate(R.layout.fragment_web, container, false);
            webView.setWebViewClient(new WebViewClient());
            webView.setWebChromeClient(new WebChromeClient());
            webView.getSettings().setJavaScriptEnabled(true);
        }
        return webView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (newInstance) {
            if (savedInstanceState == null) {
                webView.loadUrl("https://www.youtube.com/watch?v=O7izyt2LrfI");
            } else {
                webView.restoreState(savedInstanceState);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            webView.onResume();
        }
        webView.resumeTimers();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            webView.onPause();
        }
        webView.pauseTimers();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }
}
