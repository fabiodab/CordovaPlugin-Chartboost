/********* IOSChartboost.m Cordova Plugin Implementation *******/
/* Written by: Fabio Acri
Date : 17 Oct 2017
on behalf of : Dab Gaming Limited */
/****************************************************/


#import <Cordova/CDVPlugin.h>
#import "IOSChartboost.h"

@implementation IOSChartboost

@synthesize callbackId;
@synthesize showRewardedVideo;
@synthesize completeRewardedVideo;

static IOSChartboost *chartboostPlugin;

+ (IOSChartboost *) chartboostPlugin {
    return chartboostPlugin;
}

- (void) pluginInitialize {
    NSLog(@"Starting IOSChartboost plugin");
    chartboostPlugin = self;
}

- (void) setCustomId:(CDVInvokedUrlCommand*)command{
    [Chartboost setCustomId:[command.arguments objectAtIndex:0]];
}

- (void) downloadRewardedVideo:(CDVInvokedUrlCommand*) command{
    self.callbackId = command.callbackId;
    if([Chartboost hasRewardedVideo:CBLocationMainMenu]) {
        CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK
                                                          messageAsString:@"didCacheRewardedVideo"];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:self.callbackId];
    }
    else {
        [Chartboost cacheRewardedVideo:CBLocationMainMenu];
    }
}

- (void) showRewardedVideo:(CDVInvokedUrlCommand*) command{
    self.callbackId = command.callbackId;
    self.showRewardedVideo = true;
    [Chartboost showRewardedVideo:CBLocationMainMenu];
}


- (void)didCacheRewardedVideo:(CBLocation)location{
    if (!self.showRewardedVideo){
        CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK
                                                          messageAsString:@"didCacheRewardedVideo"];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:self.callbackId];
    };
}

- (void)didFailToLoadRewardedVideo:(CBLocation)location
                         withError:(CBLoadError)error{
    if (!self.showRewardedVideo){
        CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK
                                                          messageAsString:@"didFailToLoadRewardedVideo"];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:self.callbackId];
    };
}

- (BOOL)shouldDisplayRewardedVideo:(CBLocation)location{
    self.completeRewardedVideo = false;
    return true;
}

- (void)didCompleteRewardedVideo:(CBLocation)location
                      withReward:(int)reward{
    self.completeRewardedVideo = true;
}

- (void)didCloseRewardedVideo:(CBLocation)location{
    self.showRewardedVideo = false;
    NSDictionary *dictionary = @{@"message" : @"didCloseRewardedVideo",
                                 @"didCompleteRewardedVideo"  : @(self.completeRewardedVideo)};
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK
                                                  messageAsDictionary:(dictionary)];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:self.callbackId];
}

@end
