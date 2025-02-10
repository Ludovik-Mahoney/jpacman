package nl.tudelft.jpacman.ui;
import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import nl.tudelft.jpacman.level.Player;

public class LifePanel extends JPanel {
    /**
     * Default serialisation ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The map of players and the labels their life are on.
     */
    private final Map<Player, JLabel> lifeLabels;

    /**
     * The default way in which the life is shown.
     */
    public static final LifePanel.LifeFormater DEFAULT_LIFE_FORMATTER =
        (Player player) -> String.format("Life: %d", player.getLife());

    /**
     * The way to format the life information.
     */
    private LifePanel.LifeFormater lifeFormatter = DEFAULT_LIFE_FORMATTER;

    /**
     * Creates a new life panel with a column for each player.
     *
     * @param players
     * to display the lifes of  The players.
     */
    public LifePanel(List<Player> players) {
        super();
        assert players != null;

        setLayout(new GridLayout(2, players.size()));

        for (int i = 1; i <= players.size(); i++) {
            add(new JLabel("Player " + i, JLabel.CENTER));
        }
        lifeLabels = new LinkedHashMap<>();
        for (Player player : players) {
            JLabel lifeLabel = new JLabel("Life: " + player.getLife(), JLabel.CENTER);
            lifeLabels.put(player, lifeLabel);
            add(lifeLabel);
        }
    }


    /**
     * Refreshes the life of the players.
     */
    protected void refresh() {
        for (Map.Entry<Player, JLabel> entry : lifeLabels.entrySet()) {
            Player player = entry.getKey();
            String life = "";
            if (!player.isAlive()) {
                life = "You died. ";
            }
            life += lifeFormatter.format(player);
            entry.getValue().setText(life);
        }
    }

    /**
     * Provide means to format the life for a given player.
     */
    public interface LifeFormater {

        /**
         * Format the life of a given player.
         * @param player The player and its life
         * @return Formatted life.
         */
        String format(Player player);
    }

    /**
     * Let the life panel use a dedicated life formatter.
     * @param lifeFormater Life formatter to be used.
     */
    public void setLifeFormatter(LifePanel.LifeFormater lifeFormater) {
        assert lifeFormater != null;
        this.lifeFormatter = lifeFormater;
    }
}
