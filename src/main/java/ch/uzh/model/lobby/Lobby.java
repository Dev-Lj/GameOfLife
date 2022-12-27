package ch.uzh.model.lobby;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Lobby {
    public static int DEFAULT_NR_PLAYERS = 2;

    private LobbyPlayer currentPlayer;
    private final List<LobbyPlayer> players;
    private int playerCounter = 1;
    private Optional<LobbyPlayer> winner;

    /**
     * 
     * @param players
     * @pre players != null && players.length >= 2 && players are valid
     */
    public Lobby(LobbyPlayer... players) {
        assert players != null && players.length >= 2;
        this.players = Arrays.asList(players);
        this.winner = Optional.empty();
        nextPlayer();
        validate();
    }

    public LobbyPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public void nextPlayer() {
        int nextI = (playerCounter + 1) % players.size();
        this.playerCounter = nextI;
        this.currentPlayer = players.get(this.playerCounter);
    }

    public void checkForWinner() {
        List<LobbyPlayer> possibleWinners = this.players.stream().filter(p->p.getAmountOfCells()!=0).collect(Collectors.toList());
        if (possibleWinners.size() == 1) {
            this.winner = Optional.of(possibleWinners.get(0));
        }
    }

    public boolean hasWinner() {
        return winner.isPresent();
    }

    /**
     * 
     * @pre this.hasWinner()
     */
    public LobbyPlayer getWinner() {
        assert this.hasWinner();
        return winner.get();
    }

    private void validate() throws IllegalArgumentException{
        int uniqueNames = players.stream().map(p -> p.getName()).distinct().toArray().length;
        int uniqueColors = players.stream().map(p -> p.getColor()).distinct().toArray().length;

        // For more precision, an if combining those two possible exceptions could be added
        // In a similar way, we could also check which LobbyPlayers are not unique, but since currently the playerConfigField do not get highlighted, I do not see the purpose for this.
        if (uniqueNames != players.size()) {
            throw new IllegalArgumentException("Duplicate names");
        }

        if (uniqueColors != players.size()) {
            throw new IllegalArgumentException("Duplicate colors");
        }

        // for (int i = 0; i < players.size(); i++) {
        //     for (int j = i + 1; j < players.size(); j++) {
        //         if (players.get(i).getColor().equals(players.get(j).getColor())) {
        //             throw new IllegalArgumentException(
        //                     "Color of player " + (i + 1) + " and player " + (j + 1)
        //                             + " are the same, please choose different colors");
        //         } else if (players.get(i).getName().equals(players.get(j).getName())) {
        //             throw new IllegalArgumentException(
        //                     "Name of player " + (i + 1) + " and player " + (j + 1)
        //                             + " are the same, please choose different names");
        //         }
        //     }
        // }
    }

    // TODO there must be a more elegant solution
    public List<LobbyPlayer> getPlayers() {
        return players;
    }

}
