# Music Management System

## Overview:
This Music Management System is an application that simulates a music streaming platform. Users can register as listeners or artists, play songs, manage playlists, and handle subscription payments. 
Artists can upload and manage their songs, while regular users can explore and interact with music content.

---

## Design
  - `model` package – holds the core data structures (User, Artist, Song, etc.)
  - `service` package – contains business logic and file persistence (UserService, SongService, etc.)
  - `utility` – user input helper functions (`MusicUtility.java`)
  - `main` class – `MusicMain.java` is the program's entry point

##  Core Classes

| Class         | Responsibility |
|---------------|----------------|
| `User`        | Stores user account info |
| `Artist`      | Stores artist profile info |
| `Song`        | Represents a song |
| `Playlist`    | Represents a user-created playlist |
| `Payment`     | Tracks user subscription payments |
| `UserSong`    | Tracks which songs a user played |

##  Service Classes

| Service            | Functions |
|--------------------|-----------|
| `UserService`      | User account management and interaction |
| `ArtistService`    | Artist account and song management |
| `SongService`      | Song CRUD operations |
| `PlaylistService`  | Playlist creation/edit/delete |
| `PaymentService`   | Payment and subscription management |
| `UserSongService`  | Tracks song play history |

##  Data Storage

All information is stored in CSV files located in `/src/data/` folder.

---

## Installation Instructions

1. Clone or Download the Repository
   ```bash
   git clone https://github.com/yourusername/music-management-system.git

---
   ## Usage

When launching the program:

You will be promted with a menu asking if you have an account.

You can create a new user or artist account.

Based on your account type, you can:

User:

Search songs

Play songs

Create and manage playlists

View and manage subscription payments

Artist:

Add, edit, or delete your own songs



![MusicApp_UML_Diagram](https://github.com/user-attachments/assets/3c9f6559-3078-4ed6-9ac7-e27de4d133af)
