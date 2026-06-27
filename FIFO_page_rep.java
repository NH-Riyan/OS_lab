class FIFO {
    public static void main(String[] args) {
        int[] pages = {1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5};
        int n = pages.length;
        int frames = 3;

        int[] frame = new int[frames];
        int pointer = 0;
        int faults = 0;

        for (int i = 0; i < frames; i++) {
            frame[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            int page = pages[i];
            boolean found = false;

            for (int j = 0; j < frames; j++) {
                if (frame[j] == page) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                frame[pointer] = page;
                pointer = (pointer + 1) % frames;
                faults++;
            }


            System.out.print("Page " + page + " → ");
            for (int j = 0; j < frames; j++) {
                if (frame[j] != -1)
                    System.out.print(frame[j] + " ");
                else
                    System.out.print("- ");
            }
            System.out.println();
        }

        System.out.println("\nTotal Page Faults = " + faults);
    }
}

