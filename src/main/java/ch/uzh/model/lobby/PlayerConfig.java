package ch.uzh.model.lobby;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerConfig {
    public static final int NR_PLAYERS = 2;
    public static final List<String> COLORS = Collections.unmodifiableList(
            new ArrayList<String>() {
                {
                    add("red");
                    add("blue");
                    add("green");
                    add("yellow");
                    add("orange");
                    add("purple");
                    add("black");
                    add("white");
                }
            });

}
