package space;

import java.awt.EventQueue;

import javax.swing.JFrame;

import controllers.GameController;

public class SpaceInvaders extends JFrame {

	private static final long serialVersionUID = 1L;
	private Board board;

	public SpaceInvaders() {

		initUI();
	}

	private void initUI() {
		board = new Board();
		add(board);

		setTitle("Space Invaders");
		setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}


	public static void showControllerPlaying(GameController controller, long seed) {
		EventQueue.invokeLater(() -> {

			var ex = new SpaceInvaders();
			ex.setController(controller);
			ex.setSeed(seed);
			ex.setVisible(true);
		});
	}
	
	public void setController(GameController controller) {
		board.setController(controller);
	}

	public void setSeed(long seed) {
		board.setSeed(seed);

	}
}
