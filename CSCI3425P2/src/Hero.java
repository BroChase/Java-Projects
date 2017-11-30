public class Hero {
    private String hero;
    private String comic;

    public Hero(){}
    public Hero(final String hero, final String comic){
        this.hero = hero;
        this.comic = comic;
    }

    public String getHero(){return hero;}
    public void setHero(String h){
        this.hero = h;
    }
    public String getComic(){return comic;}
    public void setComic(String c){
        this.comic = c;
    }
}
