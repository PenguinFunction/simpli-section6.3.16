import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class FileHandler {
    private static String FILENAME = "lab3-test.txt";

    public static void main(String[] args) {
        System.out.println("================");
        System.out.println("| File Handler |");
        System.out.println("================");

        int[] vaildOptions = { 1, 2, 3, 4 };

        Scanner sc = new Scanner(System.in);
        boolean isHandling = true;
        do {
            System.out.println("\nSelect an option (Enter a number then press enter)");
            System.out.println("[1] Read a file");
            System.out.println("[2] Overwrite a file");
            System.out.println("[3] Append to a file");
            System.out.println("[4] Quit\n");

            try {
                int userOption = sc.nextInt();

                boolean isValidInput = false;
                for (int option : vaildOptions) {
                    if (userOption == option) {
                        isValidInput = true;
                        break;
                    }
                }

                if (isValidInput == false) {
                    throw new InputMismatchException();
                } else {
                    switch (userOption) {
                        case 1: {
                            readFile(FILENAME);
                            break;
                        }
                        case 2: {
                            sc.nextLine();
                            writeToFile(sc, FILENAME);
                            break;
                        }
                        case 3: {
                            sc.nextLine();
                            appendToFile(sc, FILENAME);
                            break;
                        }
                        case 4: {
                            isHandling = false;
                            break;
                        }
                        default: {
                            System.out.println("Invalid option, please try again.");
                            break;
                        }
                    }
                }
            } catch (InputMismatchException IME) {
                System.out.println("Invalid option, please try again.");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        } while (isHandling != false);

        // Close scanner
        sc.close();
    }

    /**
     * Helper function that opens a file and prints the contents
     */
    private static void readFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Close reader
            reader.close();
        } catch (FileNotFoundException FNFe) {
            System.out.println("Error: Could not find file");
            FNFe.printStackTrace();
        } catch (IOException IOe) {
            System.out.println("Error: Could not read file");
            IOe.printStackTrace();
        }
    }

    /**
     * Helper function that writes user input into a file
     * 
     * @param sc       Scanner object
     * @param filename Name of file to write to
     */
    private static void writeToFile(Scanner sc, String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

            System.out.println("Enter text to overwrite file: ");
            String newFileContent = sc.nextLine().trim();
            writer.write(newFileContent);

            // Close writer
            writer.close();
        } catch (IOException IOe) {
            System.out.println("Error writing to file");
            IOe.printStackTrace();
        } catch (NoSuchElementException NSEe) {
            System.out.println("Error writing to file");
            NSEe.printStackTrace();
        }
    }

    /**
     * Helper function that appends user input into a file
     * 
     * @param sc       Scanner object
     * @param filename Name of file to append to
     */
    private static void appendToFile(Scanner sc, String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
            System.out.print("Enter text to add to file: ");
            writer.write("\n" + sc.nextLine());

            // Close writer
            writer.close();
        } catch (IOException IOe) {
            System.out.println("Error appending to file");
            IOe.printStackTrace();
        }
    }
}