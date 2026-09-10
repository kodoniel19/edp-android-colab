# Implementation Plan - Lab Activity 9: MySocial with Room and DataStore

Build a social media app "MySocial" with persistent posts (Room) and theme settings (DataStore).

## Proposed Changes

### Configuration

#### [MODIFY] [libs.versions.toml](file:///C:/Users/kodon/StudioProjects/edp-android-colab/gradle/libs.versions.toml)
- Add Room, DataStore, and KSP versions and library definitions.

#### [MODIFY] [build.gradle.kts (Project)](file:///C:/Users/kodon/StudioProjects/edp-android-colab/build.gradle.kts)
- Apply the KSP plugin.

#### [MODIFY] [build.gradle.kts (Module: app)](file:///C:/Users/kodon/StudioProjects/edp-android-colab/app/build.gradle.kts)
- Apply the KSP plugin.
- Add Room, DataStore, and Lifecycle dependencies.

### Data Layer

#### [NEW] [Post.kt](file:///C:/Users/kodon/StudioProjects/edp-android-colab/app/src/main/java/com/liceo/mysocial/data/Post.kt)
- Define the `Post` entity with Room annotations.

#### [NEW] [PostDao.kt](file:///C:/Users/kodon/StudioProjects/edp-android-colab/app/src/main/java/com/liceo/mysocial/data/PostDao.kt)
- Define the `PostDao` interface for Room operations (CRUD).

#### [NEW] [AppDatabase.kt](file:///C:/Users/kodon/StudioProjects/edp-android-colab/app/src/main/java/com/liceo/mysocial/data/AppDatabase.kt)
- Create the Room database class.

#### [NEW] [PostRepository.kt](file:///C:/Users/kodon/StudioProjects/edp-android-colab/app/src/main/java/com/liceo/mysocial/data/PostRepository.kt)
- Create a repository to manage post data.

#### [NEW] [ThemeRepository.kt](file:///C:/Users/kodon/StudioProjects/edp-android-colab/app/src/main/java/com/liceo/mysocial/data/ThemeRepository.kt)
- Create a repository to manage theme settings using DataStore.

### UI Layer

#### [NEW] [PostsViewModel.kt](file:///C:/Users/kodon/StudioProjects/edp-android-colab/app/src/main/java/com/liceo/mysocial/ui/PostsViewModel.kt)
- Implement ViewModel for post-related logic.

#### [NEW] [ThemeViewModel.kt](file:///C:/Users/kodon/StudioProjects/edp-android-colab/app/src/main/java/com/liceo/mysocial/ui/ThemeViewModel.kt)
- Implement ViewModel for theme-related logic.

#### [NEW] [AppViewModelFactory.kt](file:///C:/Users/kodon/StudioProjects/edp-android-colab/app/src/main/java/com/liceo/mysocial/ui/AppViewModelFactory.kt)
- Factory to provide ViewModels with their dependencies.

#### [NEW] [PostsScreen.kt](file:///C:/Users/kodon/StudioProjects/edp-android-colab/app/src/main/java/com/liceo/mysocial/ui/PostsScreen.kt)
- Create the Posts screen UI, including `PostCard` and `PostEditorDialog`.

#### [NEW] [ProfileScreen.kt](file:///C:/Users/kodon/StudioProjects/edp-android-colab/app/src/main/java/com/liceo/mysocial/ui/ProfileScreen.kt)
- Create the Profile screen UI with theme toggle.

#### [MODIFY] [MainActivity.kt](file:///C:/Users/kodon/StudioProjects/edp-android-colab/app/src/main/java/com/liceo/mysocial/MainActivity.kt)
- Wire everything together and apply the theme.

## Verification Plan

### Automated Tests
- Build the project to ensure KSP and Room code generation work.

### Manual Verification
- Add, edit, and delete posts; verify they persist after app restart.
- Toggle dark theme; verify it persists after app restart.
- Check post count on the profile page.
- Verify "Delete this post?" confirmation dialog (Bonus).
- Verify post count in the top bar of the Posts page (Bonus).
