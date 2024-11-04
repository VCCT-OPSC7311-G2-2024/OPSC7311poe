mindXcape is a mental health companion mobile application designed to help users manage their mental well-being through various features such as guided meditations, mood tracking, journaling, and sleep aids. The application aims to provide users with tools and resources to enhance their mental health and overall well-being.

Team Members:
Cameron Daniel Colley - ST10037966 
Dylan Lee Padayachee - ST10177615 
Kurt Joshau Siebritz - ST10208082 


Features:
•	User Registration and Authentication: Users can create an account and log in securely using Firebase Authentication.
•	Guided Meditations: A curated selection of meditation sessions focused on different aspects like stress relief, focus, and sleep enhancement.
•	Mood Tracking: Users can log their emotions daily and observe patterns and trends over time.
•	Journaling: A private space for users to write about their thoughts and reflections.
•	Sleep Aids: Includes sleep stories, relaxing sounds, and guided sleep meditations to improve sleep quality.
•	Daily Quotes: Inspirational quotes displayed daily to encourage positive thinking.
•	Offline Mode: Access essential features offline, with data syncing once reconnected to the internet.
•	Biometric Authentication: Secure login options using fingerprint or face recognition.


Getting Started

Prerequisites:
•	Android Studio
•	Kotlin 1.5 or higher
•	Firebase project for authentication and database services


Installation
1. Clone the repository:
   bash
   git clone https://github.com/YOUR_USERNAME/mindXcape.git
   cd mindXcape
2.	Open the project in Android Studio.
3.	Add your Firebase configuration:
o	Go to the Firebase Console.
o	Create a new project (if you have not already).
o	Add your Android app and download the google-services.json file.
o	Place the google-services.json file in the app/ directory.
4.	Sync the project with Gradle files.



Running the App:
1.	Connect your Android device or start an emulator.
2.	Click the "Run" button in Android Studio to build and run the application.



Project Structure:

 
mindXcape/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── example/
│   │   │   │           └── opsc7312poe/
│   │   │   │               ├── Register.kt
│   │   │   │               ├── Login.kt
│   │   │   │               ├── MainActivity.kt
│   │   │   │               └── other_activity_files.kt
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   ├── drawable/
│   │   │   │   └── values/
│   │   └── test/
│   │       ├── java/
│   │       └── androidTest/
│   ├── build.gradle
│   └── proguard-rules.pro
├── build.gradle
└── settings.gradle

 



Testing:
This project includes unit and UI tests using JUnit and Espresso. To run tests locally:
1.	Open the test class you want to run in Android Studio.
2.	Right-click on the test class or method and select "Run".


CI/CD with GitHub Actions:
The project is configured with GitHub Actions for automated testing and building. On each push to the main branch, the tests are automatically executed, and the build is validated.


Contributing:
Contributions are welcome! Please feel free to submit a pull request or open an issue for any enhancements or bug fixes.


License:
This project is licensed under the MIT License - see the LICENSE file for details.


Acknowledgments:
•	Firebase for providing backend services.
•	Android Developers for the resources and documentation.



Notes on the README:
•	GitHub Username: Replace `YOUR_USERNAME` with your actual GitHub username in the clone command.
•	Firebase Configuration: Ensure users know how to set up Firebase correctly, as this is crucial for the app’s functionality.
•	Structure Overview: The directory structure provided is a high-level overview, so you may want to adjust the names of the activity files according to your actual project.
•	Contribution Guidelines: You can expand this section based on how you want to manage contributions.

Release Notes:
•	The login page has been updated to allow the user to register and log in to the app using single sign-on (SSO) and biometric authentication (fingerprint or facial recognition).
•	Real-time notifications have been implemented and provide important messages and updates to the user.
•	The user can change and save their settings in the account and edit profile pages.
•	Multi-language support has been implemented, which includes the languages: English, Zulu and Xhosa.
•	Offline mode with Sync has been implemented, which regards to saving moods.
•	The navigation bar and all its components are working properly and the user can navigate and back and forth between pages.
•	The mood track page now works correctly and displays moods in a pie chart and bar graph.
•	Journal entries can now be saved and viewed. 
•	The audio`s in the audio pages now work as well.
•	All in the tabs in the account page work, such as the privacy, terms and conditions and help & support tabs and contain valuable information.
•	A Quote REST API provides daily quotes to the user.


Youtube video link:

https://youtu.be/hnjixCZmLkY 
