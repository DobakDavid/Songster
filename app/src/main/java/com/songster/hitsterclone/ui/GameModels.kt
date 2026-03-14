package com.songster.hitsterclone.ui

data class SongCard(
    val id: String,
    val title: String,
    val artist: String,
    val year: Int
)

data class PlayerTimeline(
    val name: String,
    val songs: List<SongCard> = emptyList()
)

data class GameState(
    val players: List<PlayerTimeline>,
    val activePlayer: Int,
    val currentCard: SongCard,
    val gameStarted: Boolean
)
