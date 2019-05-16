# XKCD

A very original XKCD app that will evolve as I learn. If you see an improvement, file an issue or send a pull request.

## Getting up and running

### Prerequisites

In order to work on the project you need the following:

* Android Studio 3.0 or higher
* Build tools and SDK versions used in the project - refer to the `app` modules `build.gradle` file for the current version.

### Building for the emulator

In order to run this app in your emulator, perform the following steps:

* Import the project into Android Studio
* Create a device in AVD Manager (or connect a physical device with USB debugging enabled if you want to use that)
* Click build/run and choose the desired device to run the app on

## Roadmap

This app will very likely change as I go, but these are some of my plans for it

### Version one
- Fetch single comic using ok http
- Fetch image using picasso

### Version two
- Fetch a set range of images and show in recycler view
- Use retrofit for requests

### Version three
- Allow searching for comics

### Version four
- click to show explain-xkcd webview popup for each image

### Version five
- Favourite comics for offline use