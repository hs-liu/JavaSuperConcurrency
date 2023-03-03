package socialnetwork.domain;

public class PositionNode<M> {
  private final M prev;
  private final M curr;

  public PositionNode(M prev, M curr) {
    this.prev = prev;
    this.curr = curr;
  }

  public M getPrev() {
    return this.prev;
  }

  public M getCurr() {
    return this.curr;
  }
}
