package socialnetwork.domain;

public class LockablePosition {
  private final LockableNode prev;
  private final LockableNode curr;

  public LockablePosition(LockableNode prev, LockableNode  curr) {
    this.prev = prev;
    this.curr = curr;
  }

  public LockableNode getPrev() {
    return this.prev;
  }

  public LockableNode getCurr() {
    return this.curr;
  }
}
