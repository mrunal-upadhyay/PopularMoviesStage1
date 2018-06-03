# Popular Movies Stage 1
1. App is written solely in the Java Programming Language.
2. Movies are displayed in the main layout via a grid of their corresponding movie poster thumbnails.
3. UI contains an element (i.e a spinner or settings menu) to toggle the sort order of the movies by: most popular, highest rated.
4. UI contains a screen for displaying the details for a selected movie.
5. Movie details layout contains title, release date, movie poster, vote average, and plot synopsis.
6. App utilizes stable release versions of all libraries, Gradle, and Android Studio.
7. When a user changes the sort criteria (“most popular and highest rated”) the main view gets updated correctly.
8. When a movie poster thumbnail is selected, the movie details screen is launched.
9. In a background thread, app queries the /movie/popular or /movie/top_rated API for the sort criteria specified in the settings menu.
10. App conforms to common standards found in the Android Nanodegree General Project Guidelines (NOTE: For Stage 1 of the Popular Movies App, it is okay if the app does not restore the data using onSaveInstanceState/onRestoreInstanceState)
