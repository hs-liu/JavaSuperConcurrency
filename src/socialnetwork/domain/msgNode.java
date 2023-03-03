package socialnetwork.domain;

public class msgNode<M> {
  private M value;
  private int id;
  private msgNode nxtNode;

  public msgNode (M value) {
    this(value, value.hashCode(), null);
  }

  public msgNode (M value, int id) {
    this(value, id, null);
  }

  public msgNode (M value, msgNode nxtNode) {
    this(value, value.hashCode(), nxtNode);
  }

  public msgNode (M value, int id, msgNode nxtNode) {
    this.value = value;
    this.id = id;
    this.nxtNode = nxtNode;
  }

  public void setValue(M value) {
    this.value = value;
    this.id = value.hashCode();
  }

  public M getValue() {
    return this.value;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return this.id;
  }

  public void setNxtNode(msgNode nxtNode) {
    this.nxtNode = nxtNode;
  }

  public msgNode<M> getNxtNode() {
    return this.nxtNode;
  }

}
