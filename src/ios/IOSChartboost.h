/********* IOSChartboost.h Cordova Plugin Header *******/
/* Written by: Fabio Acri
Date : 17 Oct 2017
on behalf of : Dab Gaming Limited */
/****************************************************/

#import <Cordova/CDVPlugin.h>
#import <Chartboost/Chartboost.h>

@interface IOSChartboost : CDVPlugin <ChartboostDelegate>

@property NSString *callbackId;
@property BOOL showRewardedVideo;
@property BOOL completeRewardedVideo;

+ (IOSChartboost *) chartboostPlugin;
- (void) setCustomId:(CDVInvokedUrlCommand*) command;
- (void) downloadRewardedVideo:(CDVInvokedUrlCommand*) command;
- (void) showRewardedVideo:(CDVInvokedUrlCommand*) command;

//Delegates
- (void)didCacheRewardedVideo:(CBLocation)location;
- (void)didFailToLoadRewardedVideo:(CBLocation)location
                         withError:(CBLoadError)error;
- (BOOL)shouldDisplayRewardedVideo:(CBLocation)location;
- (void)didCompleteRewardedVideo:(CBLocation)location
                      withReward:(int)reward;
- (void)didCloseRewardedVideo:(CBLocation)location;

@end
