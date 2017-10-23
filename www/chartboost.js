var IOSChartboost = {
  startWithAppId: function(appID, appSignature) {
    cordova.exec(null, null, 'IOSChartboost', 'startWithAppId', [appID, appSignature]);
  },
  setCustomId: function(customId) {
    cordova.exec(null, null, 'IOSChartboost', 'setCustomId', [customId]);
  },
  downloadRewardedVideo: function() {
    cordova.exec(function(result) {
      if (result === "didCacheRewardedVideo") {
        cordova.fireDocumentEvent('didCacheRewardedVideo');
      }
      else {
        cordova.fireDocumentEvent('didFailToLoadRewardedVideo');
      }
    }, null, 'IOSChartboost', 'downloadRewardedVideo', []);
  },
  showRewardedVideo: function() {
    cordova.exec(function(result) {
      if (result.message === "didCloseRewardedVideo") {
        if (result.didCompleteRewardedVideo) {
          cordova.fireDocumentEvent('didCompleteRewardedVideo');
        }
      }
    }, null, 'IOSChartboost', 'showRewardedVideo', []);
  },
	cacheInterstitial: function() {
    cordova.exec(function(result) {
      if (result === "didCacheInterstitial") {
        cordova.fireDocumentEvent('didCacheInterstitial');
      }
      else {
        cordova.fireDocumentEvent('didFailCacheInterstitial');
      }
    }, null, 'IOSChartboost', 'cacheInterstitial', []);
  },
	showInterstitial: function() {
    cordova.exec(function(result) {
      if (result === "didShowInterstitial") {
        cordova.fireDocumentEvent('didShowInterstitial');
      }
      else {
        cordova.fireDocumentEvent('didFailShowInterstitial');
      }
    }, null, 'IOSChartboost', 'showInterstitial', []);
  },
	destroy: function() {
    cordova.exec(function(result) {
      if (result === "destroy") {
        cordova.fireDocumentEvent('didDestroy');
      }
      else {
        cordova.fireDocumentEvent('didFailDestroy');
      }
    }, null, 'IOSChartboost', 'destroy', []);
  }
};

module.exports = IOSChartboost;
