package func9_split_iterator.code;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Spliterator;
import java.util.function.Consumer;

public class FileSpliterator implements Spliterator<File> {
    private final Deque<File> files = new ArrayDeque<>();

    public FileSpliterator(File rootDirectory) {
        files.add(rootDirectory);
    }

    @Override
    public boolean tryAdvance(Consumer<? super File> action) {
        if (files.isEmpty()) return false;

        File file = files.poll();
        action.accept(file);

        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) Collections.addAll(files, children);
        }

        return true;
    }

    @Override
    public Spliterator<File> trySplit() {
        int size = files.size();
        if (size <= 1) return null;

        Deque<File> splitFiles = new ArrayDeque<>();
        for (int i = 0; i < size / 2; i++) splitFiles.add(files.poll());

        return new FileSpliterator(splitFiles);
    }

    private FileSpliterator(Deque<File> files) {
        this.files.addAll(files);
    }

    @Override
    public long estimateSize() {
        return Long.MAX_VALUE; // Неизвестный размер
    }

    @Override
    public int characteristics() {
        return NONNULL | IMMUTABLE;
    }
}
