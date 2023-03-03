package socialnetwork;

import socialnetwork.domain.Backlog;
import socialnetwork.domain.Board;
import socialnetwork.domain.CoarseSyncBacklog;
import socialnetwork.domain.CoarseSyncBoard;

public class Main {

  public static void main(String[] args) {
    // Implement logic here following the steps described in the specs
    Backlog backlog = new CoarseSyncBacklog();
    Board board = new CoarseSyncBoard();
    SocialNetwork socialNetwork = new SocialNetwork(backlog);
  }
}
