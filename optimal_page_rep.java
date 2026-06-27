class optimal
{
    public static void main(String args[])
    {
        int[] pages = {1, 2, 3, 4, 2, 1, 5, 6, 2, 1, 2, 3};
        int frames = 3;
        int n = pages.length;

        int[] frame = new int[frames];
        int faults = 0;

        for (int i = 0; i < frames; i++)
        {
            frame[i] = -1;
        }

        for (int i = 0; i <n ; i++)
        {
            int page=pages[i];
            boolean found = false;

            for (int j = 0; j <frames ; j++)
            {
                if(frame[j]==page)
                {
                    found=true;
                    break;
                }
            }
            
            if(!found)
            {
                int idx=-1;

                for (int j = 0; j <frames ; j++)
                {

                    if(frame[j]==-1)
                    {
                        idx=j;
                        break;
                    }
                }

                if(idx==-1)
                {
                    int far=-1;
                    for (int j = 0; j <frames ; j++)
                    {

                        int k;
                        for (k = i + 1; k < n; k++) {
                            if (frame[j] == pages[k]) {
                                break;
                            }
                        }

                        if(k==n)
                        {
                            idx=j;
                            break;
                        }

                        if(k>far)
                        {
                            far=k;
                            idx=j;
                        }
                    }
                }
                frame[idx] = page;
                faults++;
            }
            System.out.print(page + " -> ");
            for (int j = 0; j < frames; j++) {
                if (frame[j] == -1)
                    System.out.print("_ ");
                else
                    System.out.print(frame[j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nTotal Page Faults = " + faults);
    }

}