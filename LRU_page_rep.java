class LRU
{
    public static void main(String[] args)
    {
        int[] pages = {1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5};
        int n = pages.length;
        int frames = 3;

        int[] frame = new int[frames];
        int[] lastused = new int[frames];
        int time=0;
        int faults=0;

        for (int i = 0; i <frames ; i++) {
            frame[i]=-1;
            lastused[i]=-1;
        }

        for (int i = 0; i < n ; i++) {
            int page=pages[i];
            boolean found = false;

            for (int j = 0; j <frames ; j++) {
                if(page==frame[j])
                {
                    found=true;
                    lastused[j]=time;
                    break;
                }
            }

            if(!found)
            {
                int lruidx=0;

                for (int j = 1; j < frames ; j++) {
                    if(lastused[lruidx]> lastused[j])
                    {
                        lruidx=j;
                    }
                }
                frame[lruidx]=page;
                lastused[lruidx]=time;
                faults++;

            }

            time++;

            System.out.print(page+"-> ");
            for (int j = 0; j <frames ; j++) {
                if(frame[j]==-1)
                    System.out.print("_ ");
                else
                    System.out.print(frame[j]+" ");
            }
            System.out.println();
        }

        System.out.println("total : " + faults);
    }
}