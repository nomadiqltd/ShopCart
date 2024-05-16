# Jam Doughnut

Jam Doughnut is a Jetpack Compose app using Material 3 design elements. This Android app sample is the leading Shop app which allows the user to view products.

# Prerequisites: 

Before running the app, ensure you have the following installed on your system:

- [Android Studio](https://developer.android.com/studio) - [Minimum Android SDK Version: **24** | Target Android SDK Version: **34**]
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) - [JDK Version: **17**]

**Build System**
- Gradle
- Kotlin

Latest App version : **1.0.0**

# Running the App

Follow these steps to run the app:

1. **Clone the Repository**: -> Clone this GitHub repository to your local machine using Git:

   ```bash
   git clone git@github.com:nomadiqltd/ShopCart.git
   
2. - On Github, check out the 'master' branch.

3. - Select _Build_ -> _Rebuild Project_ and then select _Run_ after the project successfully builds.

# Architecture
The chosen architecture is MVVM (Model View ViewModel) with a Clean-ish approach. 

The main 3 layers are: 

**Presentation Layer**
- This layer displays the application data on the screen. This includes the Jetpack Compose functions, ViewModels and UI State.

**Domain Layer**
- The domain layer is the mediator between the data layer and the presentation layer.
- This app is a pretty small sample but for future scalability it will become more complex and all that logic that would have lived in the ViewModel would now reside in smaller UseCases instead (optional layer).

**Data Layer**
- The data layer contains repositories that abstract away the complexity of the data's origins. In this case, it's all remote data but it has the capability to extend to a form of local storage i.e. a Database.

**Project Dependencies**

Below is a brief summary of the main dependencies below:

# **Jetpack libraries**
Compose
- Declarative UI with composable functions as opposed to old school XML layouts
  
Arch Core
- Helper for other arch dependencies, including JUnit test rules

Hilt
- Dependency injection to allow the different components to best utilise their dependencies

Navigation
- Default navigation library

Kotlinx Coroutines
- Asynchronous programming to obtain data from the network and leverage concurrency concepts for a more performant app

Retrofit
- HTTP client that supports coroutines
  
Moshi
- JSON Parser, used to parse requests on the data layer for Entities (coupled with okhhtp interceptors)

JUnit
- This was used for unit testing the use cases, ViewModels, and the Repositories
  
Mockk / Mockito
 - Used to provide test fakes / doubles to aid in testing other components by reducing side effects of their intrinsic dependencies

Truth
 - Readable Assertions Library to aid test fidelity

Espresso
 - Used for writing Android UI tests (that require the Android system components i.e. Activities, some Composables)

Coil
 - Image loading for Android backed by Kotlin Coroutines.

# **Known Improvements:**

- Offline support - use of a snackbar to inform the user there is poor or no internet connectivity and other error scenarios.
- Persist the items to local storage so the user always has access even when offline.

# **Out of Scope**
- Offline mode
- Minor testing in obfuscated mode / Proguard mode only
