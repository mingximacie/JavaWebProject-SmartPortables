public class prod {
    private String name;
    private int inventory;
    prod(String name,int inventory){
        this.inventory = inventory;
        this.name=name;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getInventory() {
        return inventory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
