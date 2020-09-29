# JState
JState is a Java library for simple Finite State Machines. I originally wrote this library under the name [libKontrol](https://github.com/frc5024/lib5k/tree/master/libKontrol) for [@frc5024](https://github.com/frc5024). This new library is part of an effort to save my work at Raider Robotics as a collection of Java libraries that do not depend on any of the team's internal infrastructure.

The goal of JState is to provide a very simple way to quickly implement Finite State Machines in Java code without any inner classes. The library was designed and implemented in 2019 as a way to speed up development times. JState has been used on-robot at Raider Robotics to power many mission-critical systems autonomously and precisely.

## Installation

Using this library with your program is quite simple. Here is a basic example for Gradle:


**Step 1.** Declare my maven server the end of your repositories block in *build.gradle*

```groovy
repositories {
    ...
    maven { url 'https://ultralight.retrylife.ca:/maven' }
}
```

**Step 2.** Add this artifact as a dependency

```groovy
dependencies {
    implementation 'ca.retrylife:jstate:<LATEST_VERSION>'
}
```

See [ultralight.retrylife.ca](https://ultralight.retrylife.ca/?a=jstate&g=ca.retrylife) for up-to-date examples in all major buildsystems.

## Usage

An example program that toggles a boolean every two iterations can be found [here](https://github.com/Ewpratten/jstate/blob/master/src/test/java/ca/retrylife/jstate/UsageTest.java).

## How to push a release

Pushing a release is simple. Clone this repo, go to master, and run:

```sh
git tag -a <version> -m "<message>"
git push origin <version>
```