# JsonFileFunctionJava
java -jar JsonFileFunctionJava.jar

In this java project,
1. read the sample mixtape-data json file,
2. read changes.txt file, add a new playlist, remove a playlist, and add an existing song to an existing playlist
3. export the modified json file as output.json

added a new playlist with id "4", user_id "2", song_ids ["1", "2", "3"];
removed playlist with id "2"
added song_id "2" to playlist with id "1";

// copied from previous README
Further thinking:
mixtape-data scheme is not scalable. And if there are many mixtape-data files, many duplicate data will be saved across in different documents. I will like to split data to three metadata tables - users, playlists, songs.

For more details

*Design requirements*
1. highly available, highly reliable, low/acceptable latency, Eventual consistent is fine here.
2. can CRUD user, songs, playlists metadata info (may need object storage for songs content, which depends on the requirement).

Capacity Estimation and Constraints
1. 10 billion songs, 100 million total users, playlists could be history list, favorite list, etc. 500 million total playlists. One user has five playlist on average.
2. Storage Estimation:
each song metadata 200 bytes -> 200 bytes * 10 billion = 2 TB; each user metadata 200 bytes -> 200 bytes * 100 million = 20 GB; each playlists metadata 1 KB -> 1 KB * 500 million = 500 GB; // each song content 5 MB -> 5 MB * 10 billion = 50 PB

*System APIs*

  addPlaylist(user_id, playlist_id)

  removePlaylist(user_id, playlist_id)

  addItemToPlaylist(user_id, playlist_id, song_id)

  removeItemToPlaylist(user_id, playlist_id, song_id)


*Database Schema*

Users

| UserID (partitionKey) | int      |
|-----------------------|----------|
| UserName              | string   |
| Email                 | string   |
| CreationTimestamp     | datetime |
| LastLoginTimestamp    | datetime |
| UserLocation          | location |
| Playlists             | array    |

Songs

| SongID (partitionKey) | int      |
|-----------------------|----------|
| SongName              | string   |
| ArtistName            | string   |
| ArtistID              | int      |
| AlbumName             | string   |
| Size                  | int      |
| Length                | int      |
| Description           | string   |

Playlists

| PlaylistID (partitionKey) | int      |
|---------------------------|----------|
| PlaylistName              | string   |
| UserID                    | int      |
| CreationTimestamp         | datetime |
| LastModifiedTimestamp     | datetime |
| Song_ids                  | array      |


*High Level System Design*

read and write heavy (e.g. daily history or favorite playlist)

Would like to learn more context and discuss more about the design. Thanks.
