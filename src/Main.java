
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Main {

    private HashMap<String, String> hMap = new HashMap<>();
    private boolean isRunning = true;
    private Scanner sc = new Scanner(System.in);
    private int choice = 0;

    public static void main(String[] args) {

        Main main = new Main();

        while(main.isRunning){

            System.out.println("What is my purpose?");
            System.out.println("1: Populate the map.");
            System.out.println("2: Print current map.");
            System.out.println("3: Save the map into a binary file.");
            System.out.println("4: Load the map from the binary file.");
            System.out.println("5: Exit the program.");

            main.choice = main.sc.nextInt();

            switch (main.choice){
                case 1:
                    System.out.println("How many entries should the map have?");
                    main.populateMap(main.sc.nextInt());
                    System.out.println("Map has been populated\n");
                    break;
                case 2:
                    System.out.println("Currently loaded map:");
                    System.out.println(Arrays.asList(main.hMap) + "\n");
                    break;
                case 3:
                    System.out.println("Map saved into a binary file.\n");
                    main.saveMapIntoFile();
                    break;
                case 4:
                    System.out.println("Map loaded from a binary file.\n");
                    main.loadMapFromFile();
                    break;
                case 5:
                    main.isRunning = false;
                    break;
                default:
                    System.out.println("Error, input has to be between 1 and 5.");
                    break;
            }
        }
    }

    private void populateMap(int amountOfEntities){

        for (int i = 0; i < amountOfEntities ; i++) {
            hMap.put(""+i, "The data." );
        }
    }

    private void loadMapFromFile(){

        Properties pr = new Properties();
        try {
            pr.load(new FileInputStream("savedMap.bin"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String key : pr.stringPropertyNames()) {
            hMap.put(key, pr.get(key).toString());
        }
    }

    private void saveMapIntoFile(){

        Properties pr = new Properties();

        for (Map.Entry<String,String> entry : hMap.entrySet()) {
            pr.put(entry.getKey(), entry.getValue());
        }

        try {
            pr.store(new FileOutputStream("savedMap.bin"), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}