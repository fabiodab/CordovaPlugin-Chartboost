
module.exports = {
	 initialize(id, signature, successCallback, failureCallback) {
     var _successCallback = successCallback || (function(){});
     var _failureCallback = failureCallback || (function(){});
     return cordova.exec(
        _successCallback,
        _failureCallback,
        "ChartboostPlugin",
        "initialize",
        [id, signature]);
   },
	 cacheInterstitial(location, successCallback, failureCallback) {
			var _successCallback = successCallback || (function(){});
			var _failureCallback = failureCallback || (function(){});
			return cordova.exec(
				 _successCallback,
				 _failureCallback,
				 "ChartboostPlugin",
				 "cacheInterstitial",
				 [location]);
		},
		showInterstitial(location, successCallback, failureCallback) {
			var _successCallback = successCallback || (function(){});
			var _failureCallback = failureCallback || (function(){});
			return cordova.exec(
				 _successCallback,
				 _failureCallback,
				 "ChartboostPlugin",
				 "showInterstitial",
				 [location]);
		},
		cacheRewardedVideo(location, successCallback, failureCallback) {
			var _successCallback = successCallback || (function(){});
			var _failureCallback = failureCallback || (function(){});
			return cordova.exec(
				 _successCallback,
				 _failureCallback,
				 "ChartboostPlugin",
				 "cacheRewardedVideo",
				 [location]);
		},
		showRewardedVideo(location, successCallback, failureCallback) {
			var _successCallback = successCallback || (function(){});
			var _failureCallback = failureCallback || (function(){});
			return cordova.exec(
				 _successCallback,
				 _failureCallback,
				 "ChartboostPlugin",
				 "showRewardedVideo",
				 [location]);
		},
		destroy(location, successCallback, failureCallback) {
			var _successCallback = successCallback || (function(){});
			var _failureCallback = failureCallback || (function(){});
			return cordova.exec(
				 _successCallback,
				 _failureCallback,
				 "ChartboostPlugin",
				 "destroy",
				 []);
		},

};
