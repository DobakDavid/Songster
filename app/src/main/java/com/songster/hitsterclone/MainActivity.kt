package com.songster.hitsterclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.songster.hitsterclone.ui.GameEngine

class MainActivity : ComponentActivity() {
    private val engine = GameEngine()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    var state by mutableStateOf(engine.initialState(listOf("Player 1", "Player 2")))
                    val active = state.players[state.activePlayer]

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "Songster (Hitster-like prototype)",
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Text(text = "Active player: ${active.name}")

                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text("Current song card", style = MaterialTheme.typography.titleMedium)
                                Text("${state.currentCard.title} — ${state.currentCard.artist}")
                                Text("Year: ${state.currentCard.year}")
                            }
                        }

                        Text("${active.name} timeline")
                        LazyColumn(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(active.songs) { song ->
                                Card(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = "${song.year}: ${song.title} (${song.artist})",
                                        modifier = Modifier.padding(10.dp)
                                    )
                                }
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = { state = engine.placeCard(state, 0) },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Place at start")
                            }
                            Button(
                                onClick = { state = engine.placeCard(state, active.songs.size) },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Place at end")
                            }
                        }
                    }
                }
            }
        }
    }
}
