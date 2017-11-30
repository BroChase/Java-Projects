import java.util.Objects;

public class Partners {
    private String hero1;
    private String hero2;

    public Partners(){}
    public Partners(final String hero, final String partner){
        this.hero1 = hero;
        this.hero2 = partner;
    }

    public String getHero1(){return hero1;}
    public void setHero1(String h){
        this.hero1 = h;
    }

    public String getHero2(){return hero2;}
    public void setHero2(String h) {
        this.hero2 = h;
    }
    //overload the equals
    public boolean equals(Object me){
        Partners PartnersMe = (Partners)me;
        if(Objects.equals(this.hero1, PartnersMe.hero1) && Objects.equals(this.hero2, PartnersMe.hero2))
            return true;
        else
            return false;
    }
    //override the hashcode function for the class to set the hash number for Set table.  Prevent counting Dups
    public int hashCode(){
        return Objects.hashCode(this.getHero1() + this.getHero2());
    }
}
