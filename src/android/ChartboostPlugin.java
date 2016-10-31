package com.dabgaming;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.chartboost.sdk.CBLocation;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.ChartboostDelegate;
import com.chartboost.sdk.InPlay.CBInPlay;
import com.chartboost.sdk.Libraries.CBLogging.Level;
import com.chartboost.sdk.Model.CBError;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;

public class ChartboostPlugin extends CordovaPlugin
{

		@Override
		public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		    super.initialize(cordova, webView);
		}

		@Override
		public void onPause(boolean multitasking) {
			super.onPause(multitasking);
			Chartboost.onPause(cordova.getActivity());
		}

		@Override
		public void onResume(boolean multitasking) {
			super.onResume(multitasking);
			Chartboost.onResume(cordova.getActivity());
		}

		@Override
		public void onDestroy() {
			super.onDestroy();
			Chartboost.onDestroy(cordova.getActivity());
		}

	@Override
	public boolean  execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

		Log.d("ChartboostPlugin", "Plugin Called: " + action);

		if (action.equals("initialize")) {
			initialize(action, args, callbackContext);

			return true;
		} else if (action.equals("cacheInterstitial")) {
			cacheInterstitial(action, args, callbackContext);

			return true;
		} else if (action.equals("showInterstitial")) {
			showInterstitial(action, args, callbackContext);

			return true;
		} else if (action.equals("destroy")) {
			destroy(action, args, callbackContext);

			return true;
		}

		return false;

	}

	private void initialize(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
			final String appId = args.getString(0);
			final String appSignature = args.getString(1);

			Log.d("ChartboostPlugin init" ,appId + ' ' + appSignature);

			cordova.getActivity().runOnUiThread(new Runnable(){
				@Override
				public void run() {
					Chartboost.startWithAppId(cordova.getActivity(), appId, appSignature);
					//Chartboost.setActivityCallbacks(false);
					Chartboost.setDelegate(delegate);
					Chartboost.setLoggingLevel(Level.ALL);
					Chartboost.onCreate(cordova.getActivity());
					Chartboost.onStart(cordova.getActivity());
					//callbackContext.success();
				}
			});
	}

	private void destroy(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

			cordova.getActivity().runOnUiThread(new Runnable(){
				@Override
				public void run() {
					Chartboost.onDestroy(cordova.getActivity());
				}
			});
	}

	private void cacheInterstitial(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
			final String location = args.getString(0);

			Log.d("ChartboostPlugin cacheInterstitial " , location);

			cordova.getActivity().runOnUiThread(new Runnable(){
				@Override
				public void run() {
					Chartboost.cacheInterstitial(location);
				}
			});
	}

	private void showInterstitial(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
			final String location = args.getString(0);

			Log.d("ChartboostPlugin showInterstitial " , location);

			cordova.getActivity().runOnUiThread(new Runnable(){
				@Override
				public void run() {
					if (Chartboost.hasInterstitial(location)) {
						Chartboost.showInterstitial(location);
						callbackContext.success();
					} else {
						callbackContext.error("no interstitial");
					}
				}
			});
	}

	public ChartboostDelegate delegate = new ChartboostDelegate() {

        @Override
        public boolean shouldRequestInterstitial(String location) {
            Log.d("ChartboostPlugin delegate ","Should request interstitial at " + location + "?");
            return true;
        }

        @Override
        public boolean shouldDisplayInterstitial(String location) {
            Log.d("ChartboostPlugin delegate ","Should display interstitial at " + location + "?");
            return true;
        }

        @Override
        public void didCacheInterstitial(String location) {
            Log.d("ChartboostPlugin delegate ","Interstitial cached at " + location);
        }

        @Override
        public void didFailToLoadInterstitial(String location, CBError.CBImpressionError error) {
            Log.d("ChartboostPlugin delegate ","Interstitial failed to load at " + location + " with error: " + error.name());
        }

        @Override
        public void didDismissInterstitial(String location) {
            Log.d("ChartboostPlugin delegate ","Interstitial dismissed at " + location);
        }

        @Override
        public void didCloseInterstitial(String location) {
            Log.d("ChartboostPlugin delegate ","Interstitial closed at " + location);
        }

        @Override
        public void didClickInterstitial(String location) {
            Log.d("ChartboostPlugin delegate ","Interstitial clicked at " + location );
        }

        @Override
        public void didDisplayInterstitial(String location) {
            Log.d("ChartboostPlugin delegate ","Interstitial displayed at " + location);
        }

        @Override
        public boolean shouldRequestMoreApps(String location) {
            Log.d("ChartboostPlugin delegate ","Should request More Apps at " + location + "?");
            return true;
        }

        @Override
        public boolean shouldDisplayMoreApps(String location) {
            Log.d("ChartboostPlugin delegate ","Should display More Apps at " + location + "?");
            return true;
        }

        @Override
        public void didFailToLoadMoreApps(String location, CBError.CBImpressionError error) {
            Log.d("ChartboostPlugin delegate ","More Apps failed to load at " + location + " with error: " + error.name());
        }

        @Override
        public void didCacheMoreApps(String location) {
            Log.d("ChartboostPlugin delegate ","More Apps cached at " + location);
        }

        @Override
        public void didDismissMoreApps(String location) {
            Log.d("ChartboostPlugin delegate ","More Apps dismissed at " + location);
        }

        @Override
        public void didCloseMoreApps(String location) {
            Log.d("ChartboostPlugin delegate ","More Apps closed at " + location);
        }

        @Override
        public void didClickMoreApps(String location) {
            Log.d("ChartboostPlugin delegate ","More Apps clicked at " + location);
        }

        @Override
        public void didDisplayMoreApps(String location) {
            Log.d("ChartboostPlugin delegate ","More Apps displayed at " + location);
        }

        @Override
        public void didFailToRecordClick(String uri, CBError.CBClickError error) {
            Log.d("ChartboostPlugin delegate ","Failed to record click " + (uri != null ? uri : "null") + ", error: " + error.name());
        }

        @Override
        public boolean shouldDisplayRewardedVideo(String location) {
            Log.d("ChartboostPlugin delegate ","Should display rewarded video at " + location + "?");
            return true;
        }

        @Override
        public void didCacheRewardedVideo(String location) {
            Log.d("ChartboostPlugin delegate ","Did cache rewarded video " + location);
        }

        @Override
        public void didFailToLoadRewardedVideo(String location,
                                               CBError.CBImpressionError error) {
            Log.d("ChartboostPlugin delegate ","Rewarded Video failed to load at " + location + " with error: " + error.name());
        }

        @Override
        public void didDismissRewardedVideo(String location) {
            Log.d("ChartboostPlugin delegate ","Rewarded video dismissed at " + location);
        }

        @Override
        public void didCloseRewardedVideo(String location) {
            Log.d("ChartboostPlugin delegate ","Rewarded video closed at " + location);
        }

        @Override
        public void didClickRewardedVideo(String location) {
            Log.d("ChartboostPlugin delegate ","Rewarded video clicked at " + location);
        }

        @Override
        public void didCompleteRewardedVideo(String location, int reward) {
            Log.d("ChartboostPlugin delegate ","Rewarded video completed at " + location + "for reward: " + reward);
        }

        @Override
        public void didDisplayRewardedVideo(String location) {
            Log.d("ChartboostPlugin delegate ","Rewarded video displayed at " + location);
        }

        @Override
        public void willDisplayVideo(String location) {
            Log.d("ChartboostPlugin delegate ","Will display rewarded video at " + location);
        }

        @Override
        public void didCacheInPlay(String location) {
            Log.d("ChartboostPlugin delegate ","In Play loaded at " + location);
        }

        @Override
        public void didFailToLoadInPlay(String location, CBError.CBImpressionError error) {
            Log.d("ChartboostPlugin delegate ","In play failed to load at " + location + ", with error: " + error);
        }

        @Override
        public void didInitialize() {
            Log.d("ChartboostPlugin delegate ","Chartboost SDK is initialized and ready!");
        }

    };


}
