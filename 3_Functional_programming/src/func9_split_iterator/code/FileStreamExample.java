package func9_split_iterator.code;

import java.io.File;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class FileStreamExample {
    public static void main(String[] args) {
        File rootDir = new File(".");

        Spliterator<File> fileSpliterator = new FileSpliterator(rootDir);
        Stream<File> fileStream = StreamSupport.stream(fileSpliterator, true); // true для параллельного потока

        fileStream.filter(File::isFile)
                .forEach(file -> System.out.println(file.getAbsolutePath()));
    }
}