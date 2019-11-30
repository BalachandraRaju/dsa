package arcesium;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SampleTest {

    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "C:\\Users\\Bharath\\IdeaProjects\\Developement\\ModuleOne\\src\\arcesium\\input002.txt";
        File file = new File(fileName);
        Scanner scanner = new Scanner(file).useDelimiter("\\n");
        List<String> readings = new ArrayList<>();

        while (scanner.hasNext()) {
            String next = scanner.next();
            readings.add(next);
        }
        String output = readings.toString().replaceAll("\\,\\s+", "\n");
        calcMissing(readings);
    }

    public static void calcMissing(List<String> readings) {
        List<Float> nonMissingValues = new ArrayList<>();
        List<String> allValues = new ArrayList<>();

        if (Objects.isNull(readings) || readings.isEmpty()) {
            throw new IllegalArgumentException("File has no lines or is empty");
        }
        int numberOfTestCases = Integer.valueOf(readings.get(0));
        parseLines(allValues, readings);

        System.out.println("Non Missing Values: \n" + nonMissingValues.toString()
                .replaceAll(",\\s+", "\n"));

        List<Float> differences = getDifferenceOfMercuryLevels(nonMissingValues);
        Float mean = calculateMeanOfDifferences(differences);
        Float variance = calculateVarianceOfDifferences(differences, mean);
        float standardDeviation = Double.valueOf(Math.sqrt(variance)).floatValue();

        float leftRangeOfMean = mean - standardDeviation;
        float rightRangeOfMean = mean + standardDeviation;

        System.out.println("Mean " + mean);
        System.out.println("Standard Deviation " + standardDeviation);
        System.out.println(leftRangeOfMean + " " + rightRangeOfMean);

        for (String reading : allValues) {
            if (reading != null && !reading.isEmpty() && reading.startsWith("Missing")) {
                int index = readings.indexOf(reading);

                if (index - 1 >= 0) {
                    String prev = readings.get(index - 1);
                    Float prevFloat = Float.valueOf(prev);
                }
                if (index + 1 < readings.size()) {

                }
            }
        }
    }


    private static Float calculateVarianceOfDifferences(List<Float> differences, Float mean) {
        return differences.stream().map(dif -> Math.pow(dif - mean, 2)).reduce((a, b) -> a + b)
                .map(sum -> sum / differences.size()).map(Double::floatValue).orElse(0.0F);
    }

    private static Float calculateMeanOfDifferences(List<Float> differences) {
        Float total = differences.stream().reduce((a, b) -> a + b).orElse(null);
        Float mean = 0.0f;
        if (total != null) {
            mean = total / differences.size();
        }
        return mean;
    }

    private static List<Float> getDifferenceOfMercuryLevels(List<Float> nonMissingValues) {
        List<Float> differences = new ArrayList<>();
        for (int i = 1; i < nonMissingValues.size(); i++) {
            Float diff = nonMissingValues.get(i) - nonMissingValues.get(i - 1);
            differences.add(diff);
        }
        return differences;
    }

    private static void parseLines(List<String> allValues, List<String> readings) {

        List<Float> nonMissingValues = new ArrayList<>();
        Map<Month,List<Float>> monthMercuryLevelMap = new LinkedHashMap<>();
        Map<Month,String> monthToMissingMap = new HashMap<>();

        if (readings != null && !readings.isEmpty()) {
            for (String reading : readings) {
                String[] tokens = reading.split("\\t");
                if (tokens.length == 2) {
                    String date = tokens[0];
                    String mercuryLevel = tokens[1];
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
                    LocalDateTime localDateTime = LocalDateTime.parse(date, dateTimeFormatter);
                    System.out.println(localDateTime.getMonth());
                    if (mercuryLevel.startsWith("Missing")) {
                        allValues.add(mercuryLevel);
                        monthToMissingMap.put(localDateTime.getMonth(),mercuryLevel);
                    } else {
                        Float value = Float.valueOf(mercuryLevel);
                        if (monthMercuryLevelMap.get(localDateTime.getMonth()) == null) {
                            List<Float> mercuryLevelsOfAMonth = new ArrayList<>();
                            mercuryLevelsOfAMonth.add(value);
                            monthMercuryLevelMap.put(localDateTime.getMonth(), mercuryLevelsOfAMonth);
                        } else {
                            List<Float> mercuryLevelsOfAMonth = monthMercuryLevelMap.get(localDateTime.getMonth());
                            mercuryLevelsOfAMonth.add(value);
                        }
                        nonMissingValues.add(value);
                    }
                }
            }
        }
    }
}
