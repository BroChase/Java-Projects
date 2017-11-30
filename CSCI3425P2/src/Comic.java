public class Comic {
    private String node;
    private String type;

    public Comic(){}
    public Comic(final String node, final String type){
        this.node = node;
        this.type = type;
    }

    public String getNode(){return node;}
    public void setNode(String n){this.node = n;}
    public String getType(){return type;}
    public void setType(String s){this.type = s;}
}
