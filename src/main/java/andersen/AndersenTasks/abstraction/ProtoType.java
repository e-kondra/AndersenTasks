package andersen.AndersenTasks.abstraction;

public abstract class ProtoType{
    private Long protoId;

    public Long getProtoId() {
        return protoId;
    }

    public void setProtoId(Long id) {
        this.protoId = id;
    }

    public void print(){
        System.out.println("print content in console");
    }
}
