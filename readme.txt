# 🎥 YouTubeBackend – Java + SQLite Mini Clone

> A retro-styled terminal simulation of YouTube’s basic features – register, watch, like, share, subscribe – all running on Java with an SQLite backend. Because why just watch YouTube when you can *code* one?

## 🚀 Features

- 👤 User registration
- 📺 Watch a placeholder video (with ASCII art because vibes)
- 👍 Like, 🔁 Share, and ✅ Subscribe functionalities
- 📊 Real-time counts of likes, shares, and subscribers
- 💾 SQLite database storage

## 🛠️ Technologies Used

- **Java**
- **SQLite**
- JDBC (Java Database Connectivity)
- Terminal I/O (Scanner class)

## 🧠 How It Works

When you run the program:
1. You’re prompted to register, watch a video, or exit.
2. If you watch the video, a simulated screen displays with subscriber/like/share counters.
3. You can only interact (like, share, sub) *after* registering.
4. All interactions are logged to the `youtube.db` SQLite database.

## 📂 Database Schema

### Table: `members`
| Column   | Type    | Description         |
|----------|---------|---------------------|
| no       | INT     | Auto-increment ID   |
| user_id  | INT     | Unique user ID      |
| name     | TEXT    | Username            |

### Table: `video`
| Column       | Type | Description                            |
|--------------|------|----------------------------------------|
| no           | INT  | Auto-increment video interaction ID    |
| user_id      | INT  | ID of the user who interacted          |
| statusid     | INT  | 1 for like, 2 for dislike              |
| statusS_id   | INT  | 1 if subscribed                        |
| statusR_id   | INT  | 1 if shared                            |

> ⚠️ Note: All data gets appended—no user login system or duplicate checks implemented. This is just a backend simulation, not a secure system.

## 🧪 How to Run

1. Make sure you have Java installed.
2. Download and install [SQLite](https://www.sqlite.org/download.html).
3. Create a database file at `C:/SQLite/youtube.db`.
4. Create the required tables manually in the DB (see schema above).
5. Compile and run the Java file:

```bash
javac YoutubeBackend.java
java YoutubeBackend
📝 Notes
This is a terminal-based simulation meant for educational purposes.

The video section is just ASCII art – but you could extend this to actually play media or link to a GUI.

There’s no user authentication, session handling, or error-proofing – that’s your next challenge 😉

✨ Future Upgrades
 Add login and user authentication

 Improve DB structure with foreign keys

 Add GUI (JavaFX or Swing maybe?)

 Implement comment and search features


📣 Author
Built by Yobil – Coding, dreaming, and directing The~~AI 😉

