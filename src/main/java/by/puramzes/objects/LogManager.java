package by.puramzes.objects;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pure
 */
public class LogManager {

    private List<Log> listLog = new ArrayList<>();

    public void getFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        scanFile(new File(classLoader.getResource(fileName).getFile()));
    }

    public void getAllLogs(String param) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("/" + param);
        if (url != null) {
            try {
                final File apps = new File(url.toURI());
                for (File app : apps.listFiles()) {
                    System.out.println(app);
                    scanFile(app);
                }
            } catch (URISyntaxException ex) {
            }
        }
    }

    private void scanFile(File file) {
        StringBuilder result = new StringBuilder("");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("--");
            }
            String[] arr = result.toString().split("--");
            listLog.add(new Log(arr[0], stringToDate(arr[1]), arr[2]));
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Date stringToDate(String param) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return df.parse(param);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Log> getListLog() {
        return listLog;
    }

}
