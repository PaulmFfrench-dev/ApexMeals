# ApexMeals

This Android app was created for Assignment Two of Mobile App Development2 . This is a charity based app. 
---------------------------------------------------------------------------------------------------------------
Introduction

The following technical report is for Mobile App Development Assignment 2. There will be a brief
discussion of the functionality of the app and all 3rd party apps used. Following this an overview of
the UX and DX approaches is included. Following this a short paragraph will follow on the git
standards followed and finally a personal statement on Mobile App Development.
---------------------------------------------------------------------------------------------------------------
App Functionality

I’ll go through the app functionality as how a user would interact with the app. When a user opens the
app, they have two sign in options, either they can create an account with an email and password or
sign in via google. Following this, the user will be brought to the report page that lists all donations
made towards the Apex Meals charity. The report has two modes, mode one is to display donations
made by the currently signed in user, the second mode lists all donations made. A button exists in the
bottom right of the report that can be used to add a donation. A donation can also be added via the nav
drawer or using the link menu in the top right. Within the addition of a donation. A select amount of
money can be chosen, ranging from 1 - 1000, as well as a payment method, the user would then click
donate. Once this has been done, you user will be brought to the report fragment, a new donation will
be listed. The user can swipe left to edit or right to delete. If the user chooses to edit, they can then
add additional information to the donation such as a note, name, address and phone number.
Next if we look at the nav drawer, there are three sections, the first of which is the profile section
which was intended to be a fragment where a user could customise their profile and then another
report fragment would be created specifically for users, listed in descending order from the highest
number of donations made. Unfortunately due to access errors encountered when implementing the
profile fragment, the report was not included. Two main issues cause the profile fragment idea to fail.
The first of which was an access error, I'm still to sure what caused this but I think it was the way I
was accessing firebase. The second being is the way the apex meal model has its data accessed and
that is via the UID. This is perfectly reasonable and a good way to access it but for the profile
fragment to work I would need to separate the user and apex meal information so that data is not
recurring, this could be done via a second model which could be linked to the apexmeal model in a
hierarchical fashion. However, because I decided to attempt to include information in the apex meal
and split it after, I wanted to read information based on data that would be unique to a user, this would
of course be the email. However this partially worked after parsing the mail as android studio/firebase
does not like when an email is being used as an identifier. Not because it’s an email but simply
because it’s a string containing special characters. Following this the access of the data became the
main issue. So instead I decided to transplant the data into the detailfragment so that the new data
fields of the model could still be included in the app in a way that made sense.
After the profile, there is a grouping composed of the report, donations, maps and about. As I've
already covered the first two above, I won't reiterate. However with the map, it displayed during the
demo that it will not only show donations made by the currently signed in user but can also be toggled
to show donations made by all users including their rough geographic location when the donation was
made. Then there is the about page, which is simply a display page containing some information about
the app. Finally there is another option in the nav drawer for signing out. I should also mention that
the profile image, users name and email are displayed in the nav drawer. The profile image can also be
changed.

Other information to note: Firebase realtime database, Firebase Authentication, Github and the
Google Maps API were utilised in this app.

---------------------------------------------------------------------------------------------------------------
UX & DX approaches

The UX that was followed according to the rubric given for this assignment was the Nav Drawer and
Swipe Support.
The DX that was followed was a minimalistic clean black & white modern colour scheme. Navigation
architecture was used through the app, both in the grouping of the nav drawer but also in
main_nagivation. The MVVM design pattern was followed. Firebase UI/database were also used to
ensure the user had an good experience when signing into the app and adding/retrieving information.

---------------------------------------------------------------------------------------------------------------
Git Standards followed

A full commit history is done for the entire assignment, including correct branches, tags for releases,
development branches were used in the early stages up to version16.0 which is a production branch
called release/v16.0.0.
To note assignment one ended at version-7.0 and assignment 2 began in version8.0.

---------------------------------------------------------------------------------------------------------------
Personal Statement

A final note from myself is that I thoroughly enjoyed this entire module, it was both challenging and
enlightening. In general it’s quite difficult to see any immediate changes when implementing new
features or code but mobile app development has allowed me to see programming in a new light both
in the design models being used which i wasn’t previously familiar with but also it feels far more
creative then when programming software. Java and Kotlin are quite challenging languages,
especially with the syntax that kotlin has, i initially found that kotlin was very shorthanded last
semester, almost to much so that it could cause bad habits to form and i might trip over myself going
back to PHP but i realised this semester that it was my perspective that skewed. Shorthanded
languages allow for far more efficient coding and can also be far more dynamic. Not only this but
another amazing thing I had noticed was how languages differ in their use, for example if I'm using
PHP for software, websites, etc. The way in which it is utilised is far different to Kotlin which would
generally be used for Mobile App Development. Of course there are the overarching principles such
as object orientated which cross the boundaries that programming languages tend to be divided by but
experiencing the syntax that kotlin has also really enlightened me in how languages can be used and
are designed to be used. A final note…. Thank you, I thoroughly enjoyed learning Kotlin and about
the standards/practices that are to be followed in the mobile app development industry.