package socialnetwork.domain;

public class MsgNode<M> {
  private M value;
  private int id;
  private MsgNode nxtNode;

  public MsgNode(M value) {
    this(value, value.hashCode(), null);
  }

  public MsgNode(M value, int id) {
    this(value, id, null);
  }

  public MsgNode(M value, MsgNode nxtNode) {
    this(value, value.hashCode(), nxtNode);
  }

  public MsgNode(M value, int id, MsgNode nxtNode) {
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

  public void setNxtNode(MsgNode nxtNode) {
    this.nxtNode = nxtNode;
  }

  public MsgNode<M> getNxtNode() {
    return this.nxtNode;
  }
}
