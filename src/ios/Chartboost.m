/********* Chartboost.m Cordova Plugin Implementation *******/
/* Written by: Fabio Acri
Date : 17 Oct 2017
on behalf of : Dab Gaming Limited */
/****************************************************/


#import "Chartboost.h"
#import <Cordova/CDVPlugin.h>

@implementation Chartboost

static Chartboost *chartBoostPlugin;

+ (Chartboost *) chartBoostPlugin {
    return chartBoostPlugin;
}

- (void) pluginInitialize {
    NSLog(@"Starting Chartboost plugin");
    chartBoostPlugin = self;
}

- (void) firstCommit:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSString *sID = [command.arguments objectAtIndex:0];
    NSString *sSignature = [command.arguments objectAtIndex:1];

    if (sID.length.count > 0) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:echo];
    }
    else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
