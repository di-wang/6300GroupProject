# Design Document

**Author**: Jongho Jung (jjung327)

**Team**: Adric Cain (acain33), Di Wang (dwang383), Ed Redmond (eredmond6)

## 1 Design Considerations

### 1.1 Assumptions

It is assumed that this app will be used on Android mobile phones and tablets. Since phones have significantly larger market share than tablets, more emphasis should be placed on optimizing the app for using it on mobile phones.

This app does not have a back-end server, so it is assumed that this app is only meant to be used locally without network capability (i.e. manager and all the player would use the same app installed on a same device). However, the app should be designed with possible future addition of a back-end server. To do so, parts that can be replaced by back-end server and parts that would remain as mobile app should be separated as much as possible.

### 1.2 Constraints

The Tourney App has two modes: tournament manager and tournament player. This means the app needs to have different behaviors and user interfaces for each mode. To achieve this with the minimal amount of effort, the app should be designed as modular as possible so that overlapping components for both modes can be reused.

This project is assumed to be under small budget and considering there is no dedicated designer in our team, there won't be much emphasis on visual design. The app should have a simple interface that is still user-friendly and responsive with the least amount of effort.

### 1.3 System Environment

The Tourney Manager app will be an Android OS app. The app is designed to run on Android phones and tablets. Minimum platform version this app can support will be 16, which can support 98% of the Android devices. The target platform version will be 23, which has the largest distribution at the time of this writing (February 2017).

The app would be used on many different types of mobile devices with various screen sizes and resolutions. Therefore, its user interfaces should be at least responsive and user-friendly across the most popular devices.

## 2 Architectural Design

The following sections describe the architectural diagrams for the application.

### 2.1 Component Diagram

![componentdiagram](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/component-diagram.png)

### 2.2 Deployment Diagram

For this app, there is no external back-end server. Therefore, all the components will be deployed on Android mobile devices and all the components of the app will be used locally on the phone. Due to the fact that all the components will be deployed to the same device, deployment diagram won't be necessary.

## 3 Low-Level Design

The following sections describe the low-level diagrams for the application.

### 3.1 Class Diagram

![designteam](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/design-team.png)

### 3.2 Other Diagrams

This app is a fairly simple application. Therefore, no other diagrams are needed at this point.

## 4 User Interface Design

The following images show the initial user interface design for the application.

* Home Screen

![home](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/manager-home.png)

* Mode Switch Screen

![mode](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/mode-seleciton.png)

* List of Players and Prizes Screen

![playerlist](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/manager-playerlist.png)

* List of Individual Player Winnings

![playerindividual](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/manager-playerindividual.png)

* Tournament Information Screen

![tournamentinfo](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/manager-tournamentinfo.png)

* List of Tournaments and Status

![tournamentlist](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/manager-tournamentlist.png)
