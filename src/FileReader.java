
import java.io.File;
import java.util.Scanner;

/**
 * This class will run our project.
 *
 * @author Siddharth Hingorani (sid97)
 * @author Matthew Evans (matce93)
 * @version 2017.09.21
 *
 */
public class FileReader
{
    private Database dbase = new Database();

    /**
     * This is our default constructor
     *
     * @param fileName
     *            the name to create from.
     */
    public FileReader(String fileName)
    {
        executeLines(fileName);
    }

    /**
     * This method will run through and execute helper commands base on the
     * input file.
     * 
     * @param in
     *            the filename.
     */

    private void executeLines(String in)
    {
        Scanner scanner = null;
        File file = new File(in);
        try
        {
            scanner = new Scanner(file);
        }
        catch (Exception e)
        {
            System.out.println("Cannot find given File Name!");
        }
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            Scanner lineScan = new Scanner(line);
            String command = null;
            if (lineScan.hasNext())
            {
                command = lineScan.next();
                if (command.equals("insert"))
                {
                    try
                    {
                        String name = lineScan.next();
                        int x = Integer.parseInt(lineScan.next());
                        int y = Integer.parseInt(lineScan.next());
                        dbase.insertPoint(name, x, y);
                    }
                    catch (Exception e)
                    {
                        // intentionally left blank
                    }
                }
                else if (command.equals("remove"))
                {
                    String word = null;
                    try
                    {
                        word = lineScan.next();
                        int x = Integer.parseInt(word);
                        int y = Integer.parseInt(lineScan.next());
                        dbase.removeBydimensions(x, y);
                    }
                    catch (Exception e)
                    {
                        dbase.removeByName(word);
                    }

                }
                else if (command.equals("regionsearch"))
                {
                    try
                    {
                        int x = Integer.parseInt(lineScan.next());
                        int y = Integer.parseInt(lineScan.next());
                        int width = Integer.parseInt(lineScan.next());
                        int height = Integer.parseInt(lineScan.next());
                        dbase.regionSearch(x, y, width, height);
                    }
                    catch (Exception e)
                    {
                        // intentionally left blank
                    }
                }
                else if (command.equals("duplicates"))
                {
                    dbase.duplicates();
                }
                else if (command.equals("search"))
                {
                    try
                    {
                        String name = lineScan.next();
                        dbase.search(name);
                    }
                    catch (Exception e)
                    {
                        // intentionally left blank
                    }
                }
                else if (command.equals("dump"))
                {
                    dbase.dump();
                }
                else
                {
                    System.out.println("");
                }
            }
            lineScan.close();

        }

    }
}
