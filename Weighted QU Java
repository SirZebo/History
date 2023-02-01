import java.util.List;
import java.util.ArrayList;

public class WeightedQU 
{
    private int[] id;
    private int[] iz;

    public WeightedQU(int N)
    {
        id = new int[N];
        iz = new int[N];
        for(int i = 0; i < id.length; i++)
        {
            iz[i] = i;
            id[i] = i;
        }
    }

    public int Root(int i)
    {
        while(i != id[i])
        {
            id[i] = id[id[i]];   // this line represents "path compression"
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q)
    {
        return Root(p) == Root(q);
    }

    public void union(int p, int q)   // here iz[] is used to "weighting"
    {
        int i = Root(p);
        int j = Root(q);
        if(iz[i] < iz[j])
        {
            id[i] = j;
            iz[j] += iz[i];
        }
        else
        {
            id[j] = i;
            iz[i] += iz[j];
        }
    }
}

public class Program
{
    public static List<int[]> idList = new ArrayList<int[]>();

    static {
        idList.add(new int[] { 4, 3 });
        idList.add(new int[] { 3, 8 });
        idList.add(new int[] { 6, 5 });
        idList.add(new int[] { 9, 4 });
        idList.add(new int[] { 2, 1 });
        idList.add(new int[] { 8, 9 });
        idList.add(new int[] { 5, 0 });
        idList.add(new int[] { 7, 2 });
        idList.add(new int[] { 6, 1 });
        idList.add(new int[] { 1, 0 });
        idList.add(new int[] { 6, 7 });
    }

    public static void main(String[] args)
    {
        WeightedQU uf = new WeightedQU(idList.size());
        for (int[] unionData : idList)
        {
            int p = unionData[0];
            int q = unionData[1];
            if (!uf.connected(p, q))
            {
                uf.union(p, q);
                System.out.println(p + " " + q);
            }
        }
    }
}
