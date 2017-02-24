# Design Document

*This is the template for your design document. The parts in italics are concise explanations of what should go in the corresponding sections and should not appear in the final document.*

**Author:** Jongho Jung (jjung327)
**Team:** Adric Cain (acain33), Di Wang (dwang383), Ed Redmond (eredmond6)

## 1 Design Considerations

*The subsections below describe the issues that need to be addressed or resolved prior to or while completing the design, as well as issues that may influence the design process.*

The Tourney App has two modes, tournament manager and tournament player, so the app should have different behaviors and UI for each mode.

This app does not have back-end server, so everything will be saved on local database.

### 1.1 Assumptions

*Describe any assumption, background, or dependencies of the software, its use, the operational environment, or significant project issues.*

It is assumed that this app will mostly be used on mobile phones since that is the largest number of Android devices. Due to the fact that there is no back-end server, this app is meant to be used locally (i.e. manager and all the player should use the same app installed on a same device). This project is assumed to be under small budget and considering there is no dedicated designer in our team, there won't be much emphasis on visual design.

### 1.2 Constraints

*Describe any constraints on the system that have a significant impact on the design of the system.*



### 1.3 System Environment

*Describe the hardware and software that the system must operate in and interact with.*

The Tourney Manager app will be an Android OS app. The app is designed to run on Android phones and tablets. Minimum platform version this app can support will be 16, which can support 98% of the Android devices. The target platform version will be 23, which has the largest distribution at the time of this writing (February 2017).

## 2 Architectural Design

*The architecture provides the high-level design view of a system and provides a basis for more detailed design work. These subsections describe the top-level components of the system you are building and their relationships.*

### 2.1 Component Diagram

*This section should provide and describe a diagram that shows the various components and how they are connected. This diagram shows the logical/functional components of the system, where each component represents a cluster of related functionality. In the case of simple systems, where there is a single component, this diagram may be unnecessary; in these cases, simply state so and concisely state why.*

![componentdiagram](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/component-diagram.png)

### 2.2 Deployment Diagram

*This section should describe how the different components will be deployed on actual hardware devices. Similar to the previous subsection, this diagram may be unnecessary for simple systems; in these cases, simply state so and concisely state why.*

For this app, there is no external back-end server. Therefore, entire components will be deployed on Android mobile devices and all the components of the app will be used locally.

## 3 Low-Level Design

*Describe the low-level design for each of the system components identified in the previous section. For each component, you should provide details in the following UML diagrams to show its internal structure.*

### 3.1 Class Diagram

*In the case of an OO design, the internal structure of a software component would typically be expressed as a UML class diagram that represents the static class structure for the component and their relationships.*

![designteam](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/design-team.png)

### 3.2 Other Diagrams

*<u>Optionally</u>, you can decide to describe some dynamic aspects of your system using one or more behavioral diagrams, such as sequence and state diagrams.*

## 4 User Interface Design
*For GUI-based systems, this section should provide the specific format/layout of the user interface of the system (e.g., in the form of graphical mockups).*

![home](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/manager-home.png)

![mode](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/mode-selection.png)

![playerlist](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/manager-playerlist.png)

![playerindividual](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/manager-playerindividual.png)

![tournamentinfo](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/manager-tournamentinfo.png)

![tournamentlist](https://github.gatech.edu/gt-omscs-se-2017spring/6300Spring17Team75/blob/master/GroupProject/Docs/manager-tournamentlist.png)
