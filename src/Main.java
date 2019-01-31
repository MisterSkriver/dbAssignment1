
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Main {

    private HashMap<String, String> hMap = new HashMap<String, String>();
    private boolean isRunning = true;
    private Scanner sc = new Scanner(System.in);
    private int choice = 0;


    public static void main(String[] args) {

        Main main = new Main();

        while(main.isRunning){

            System.out.println("What is my purpose?\n1: Populate the map\n 2: Print map\n 3: Save the map\n 4: Load the map");

            main.choice = main.sc.nextInt();

            switch (main.choice){
                case 1:
                    System.out.println("How many entries should the map have?");
                    main.populateMap(main.sc.nextInt());
                    System.out.println("Map has been populated");
                    break;
                case 2:
                    System.out.println("Currently loaded map:\n"+Arrays.asList(main.hMap));
                    break;
                case 3:
                    main.saveMap();
                    break;
                case 4:
                    main.loadMap();
                    break;
                default:
                    System.out.println("Error, input has to be between 1 and 3.");
                    break;
            }


        }




    }


    public void populateMap(int amount){

        for (int i = 0; i < amount ; i++) {
            hMap.put(""+i, "the text()" );
        }

    }


    public void loadMap(){

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("data.bin"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String key : properties.stringPropertyNames()) {
            hMap.put(key, properties.get(key).toString());
        }

    }


    public void saveMap(){

        Properties properties = new Properties();

        for (Map.Entry<String,String> entry : hMap.entrySet()) {
            properties.put(entry.getKey(), entry.getValue());
        }

        try {
            properties.store(new FileOutputStream("data.bin"), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
