import java.io.FileReader;
import java.util.*;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

class Main{
    private static CellProcessor[] getProcessors(){
        return new CellProcessor[]{
                new NotNull(), //column 1
                new NotNull(), // column 2
        };
    }
    //check the list to see if it is type comic or type hero.
    private static int containsComics(List<Comic> list){
        int comicCount = 0;
        for(Comic e : list){
            String comicString = e.getType();
            if(Objects.equals(comicString, "comic")) {
                comicCount++;
            }
        }
        return comicCount;
    }

    private static void numberofcomics() throws Exception{
        ICsvBeanReader beanReader = null;
        try{
            beanReader = new CsvBeanReader(new FileReader("./resources/nodes.csv"),
                    CsvPreference.STANDARD_PREFERENCE);
            final String[] header = beanReader.getHeader(true);
            final CellProcessor[] processors = getProcessors();

            List<Comic> comics = new ArrayList<>();
            Comic c;
            while((c = beanReader.read(Comic.class, header, processors)) != null){
                comics.add(c);
            }
            int comicCount = containsComics(comics);
            int heroCount = comics.size() - comicCount;
            System.out.println(String.format("Number of Comics: %d  \nNumber of Heros: %d", comicCount, heroCount));
            avgbooks(heroCount);
            float CPB = (float)comicCount/heroCount;
            System.out.println(String.format("Mean Characters per book: %.2f", CPB));
            avgPartners(heroCount);
            avgPartnersnodup(heroCount);

        }finally{
            if(beanReader != null){
                beanReader.close();
            }
        }
    }
    //find the average number of partners..
    private static void avgPartners(int heroCount)throws Exception{
        ICsvBeanReader beanReader = null;
        try{
            beanReader = new CsvBeanReader(new FileReader("./resources/hero-network.csv"),
                    CsvPreference.STANDARD_PREFERENCE);
            final String[] header = beanReader.getHeader(true);
            final CellProcessor[] processors = getProcessors();

            List<Partners> partners = new ArrayList<>();
            Partners p;
            while((p= beanReader.read(Partners.class, header, processors)) != null) {
                partners.add(p);
            }
            float partnerswithdup = (float)partners.size()/heroCount;
            System.out.println(String.format("The mean number of partners per hero: %.2f", partnerswithdup));

        }finally{
            if(beanReader != null){
                beanReader.close();
            }
        }
    }
    //find the average number of partners without duplicates.  hero1/hero2 only counted once not every time
    private static void avgPartnersnodup(int heroCount)throws Exception{
        ICsvBeanReader beanReader = null;
        try{
            beanReader = new CsvBeanReader(new FileReader("./resources/hero-network.csv"),
                    CsvPreference.STANDARD_PREFERENCE);
            final String[] header = beanReader.getHeader(true);
            final CellProcessor[] processors = getProcessors();

            Set<Partners> partners = new HashSet<>();
            Partners p;
            while((p = beanReader.read(Partners.class, header, processors)) != null){
                partners.add(p);
            }
            float partnersnodup = (float)partners.size()/heroCount;
            System.out.println(String.format("The mean number of partners per hero without duplicates: %.2f", partnersnodup));

        }finally{
            if(beanReader != null){
                beanReader.close();
            }
        }
    }
    //Find the average books per hero
    private static void avgbooks(int heroCount)throws Exception{
        ICsvBeanReader beanReader = null;
        try{
            beanReader = new CsvBeanReader(new FileReader("./resources/edges.csv"),
                    CsvPreference.STANDARD_PREFERENCE);
            final String[] header = beanReader.getHeader(true);
            final CellProcessor[] processors = getProcessors();

            List<Hero> books = new ArrayList<>();
            Hero h;
            while((h= beanReader.read(Hero.class, header, processors)) != null){
                books.add(h);
            }
            float count = (float)books.size()/heroCount;
            System.out.println(String.format("Mean books per Character: %.2f", count));
        }finally{
            if(beanReader != null){
                beanReader.close();
            }
        }
    }

    public static void main(String args[]) throws Exception {
        //try to read the file else throw exception
        try {
            numberofcomics();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}