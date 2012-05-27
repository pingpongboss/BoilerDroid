## Overview
BoilerDroid lets Android developers use jQuery's programming style, providing these main benefits:
* Notion of **class and id** for layout Views
* **jQuery-like selectors** to work with multiple elements at a time
* Concise **syntax and method chaining**
* **Boilerplate removal** for various use cases

Examples:
```
// Selectors, method chaining, and convenience methods
$(".status").text("username unavailable").textColor("red").show();

// Switch Activities easily
$(MyActivity.class).data("auth_token", "xxxxxx").start();
```

## Installation
### Step 1: Setup the BoilerDroid library
Download the BoilerDroid repository as a [zip](https://github.com/pingpongboss/BoilerDroid/zipball/master), [tar](https://github.com/pingpongboss/BoilerDroid/tarball/master), or [clone](https://github.com/pingpongboss/BoilerDroid).

Import `/library` into Eclipse as an Android Library Project. ([?](http://stackoverflow.com/a/5450375/450396))

Link to BoilerDroid as an Android Library in your own Project. ([?](http://developer.android.com/guide/developing/projects/projects-eclipse.html#ReferencingLibraryProject))

### Step 2: Start using BoilerDroid in your project
Modify all Activities in your project to extend `BoilerActivity`.

Use the `$( ... )` function to start making cool things.

## Documentation
Coming soon...

## Created By
@pingpongboss

@jmwong

@oldgreg