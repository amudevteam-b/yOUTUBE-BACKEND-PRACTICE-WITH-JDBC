# YouTube Backend

A Java-based backend application for a YouTube-like platform with a modern JavaFX interface.

## Features

- User Registration
- Video Viewing Interface
- Like/Share/Subscribe Functionality
- Real-time Stats Display
- Modern JavaFX UI
- SQLite Database Integration

## Prerequisites

- JDK 23 or later
- Maven 3.9.9
- JavaFX 24.0.1
- SQLite

## Installation

1. **Install JDK 23:**
   - Download from: https://www.oracle.com/java/technologies/downloads/
   - Set JAVA_HOME environment variable

2. **Install Maven:**
   - Download from: https://maven.apache.org/download.cgi
   - Extract to `C:\maven`
   - Set environment variables:
     ```
     MAVEN_HOME=C:\maven
     Path=%MAVEN_HOME%\bin;%Path%
     ```

3. **Install JavaFX:**
   - Download from: https://gluonhq.com/products/javafx/
   - Extract to `C:\Program Files\Java\javafx-sdk-24.0.1`
   - Set environment variable:
     ```
     JAVAFX_HOME=C:\Program Files\Java\javafx-sdk-24.0.1
     ```

4. **Install SQLite:**
   - Download from: https://www.sqlite.org/download.html
   - Create database at: `C:/SQLite/youtube.db`

## Project Structure

```
youtube-backend/
├── pom.xml
└── src/
    └── main/
        └── java/
            └── com/
                └── youtube/
                    ├── YoutubeBackend.java
                    └── YoutubeFX.java
```

## Running the Application

1. **Build the project:**
   ```bash
   mvn clean install
   ```

2. **Run the application:**
   ```bash
   mvn javafx:run
   ```

## Features

### Main Screen
- Clean, modern interface
- Three main buttons: Register, Watch Video, Exit
- YouTube-style red color scheme

### Register Screen
- Simple registration form
- Name input field
- Register button
- Success/error messages

### Video Screen
- Video player frame
- Stats panel (subscribers, likes, shares)
- Action buttons (like, share, subscribe)
- Real-time updates

## Database Schema

### Members Table
- no (INTEGER)
- name (TEXT)
- user_id (INTEGER)

### Video Table
- no (INTEGER)
- user_id (INTEGER)
- statusid (INTEGER)
- statusS_id (INTEGER)
- statusR_id (INTEGER)

## Troubleshooting

### Common Issues

1. **Maven not found:**
   - Verify MAVEN_HOME is set correctly
   - Check Path variable includes Maven bin directory

2. **JavaFX not found:**
   - Verify JAVAFX_HOME is set correctly
   - Check JavaFX dependencies in pom.xml

3. **Database connection error:**
   - Verify SQLite is installed
   - Check database path in code
   - Ensure database file exists

## Development

### Adding New Features
1. Create new methods in YoutubeBackend.java
2. Add UI components in YoutubeFX.java
3. Update database schema if needed

### Code Style
- Follow Java coding conventions
- Use meaningful variable names
- Add comments for complex logic
- Handle exceptions properly

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Author

Eyobw

## Acknowledgments

- JavaFX for the UI framework
- SQLite for the database
- Maven for dependency management