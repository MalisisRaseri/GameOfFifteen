package MainPack;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class GameGenerator implements Iterable<Integer> {
    public final static int BLANK_MARKING = 0;
    private final int gameSize;
    private static Random random = new Random();
    private final int[] marks;
    private long modCount = 0;

    public GameGenerator(final int gameSize) {
        this.gameSize = gameSize;
        this.marks = new int[gameSize * gameSize];
        generateMarks();
    }

    public void generateMarks() {

        for (int i = 1; i < this.marks.length; i++) {
            this.marks[i - 1] = i;
        }
        int blankpos = this.marks.length - 1;
        this.marks[blankpos] = BLANK_MARKING;

        for (int i = 0; i < 5; i++) {
            final int randpos = randomNeighbour(blankpos);
            swap(this.marks, blankpos, randpos);
            blankpos = randpos;
        }
        this.modCount++;
    }
    private int randomNeighbour(final int i) {
        final int[] neighbours = new int[4];
        int n = 0;
        if (i % this.gameSize != 0) {
            neighbours[n++] = i - 1;
        }
        if (i >= this.gameSize) {
            neighbours[n++] = i - this.gameSize;
        }
        if ((i + 1) % this.gameSize != 0) {
            neighbours[n++] = i + 1;
        }
        if (i < this.gameSize * (this.gameSize - 1)) {
            neighbours[n++] = i + this.gameSize;
        }
        return neighbours[random.nextInt(n)];
    }

    private static void swap(final int[] a, final int i, final int j) {
        final int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new GameIterator();
    }

    private class GameIterator implements Iterator<Integer> {
        private int index = 0;
        private final long expectedModCount = GameGenerator.this.modCount;

        @Override
        public boolean hasNext() {
            if (GameGenerator.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
            return this.index < GameGenerator.this.marks.length;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return GameGenerator.this.marks[this.index++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
