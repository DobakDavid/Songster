package com.songster.hitsterclone.ui

class GameEngine {
    private val demoDeck = listOf(
        SongCard("1", "Levitating", "Dua Lipa", 2020),
        SongCard("2", "Take on Me", "a-ha", 1985),
        SongCard("3", "Blinding Lights", "The Weeknd", 2019),
        SongCard("4", "Smells Like Teen Spirit", "Nirvana", 1991),
        SongCard("5", "Bohemian Rhapsody", "Queen", 1975)
    )

    private var index = 0

    fun initialState(playerNames: List<String>): GameState {
        val players = playerNames.map { PlayerTimeline(name = it) }
        return GameState(
            players = players,
            activePlayer = 0,
            currentCard = demoDeck.first(),
            gameStarted = true
        )
    }

    fun nextCard(): SongCard {
        index = (index + 1) % demoDeck.size
        return demoDeck[index]
    }

    fun placeCard(state: GameState, insertPosition: Int): GameState {
        val player = state.players[state.activePlayer]
        val updatedSongs = player.songs.toMutableList().apply {
            val clamped = insertPosition.coerceIn(0, size)
            add(clamped, state.currentCard)
        }

        val updatedPlayers = state.players.toMutableList().apply {
            this[state.activePlayer] = player.copy(songs = updatedSongs)
        }

        return state.copy(
            players = updatedPlayers,
            activePlayer = (state.activePlayer + 1) % state.players.size,
            currentCard = nextCard()
        )
    }
}
