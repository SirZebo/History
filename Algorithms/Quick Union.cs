//http://www.crystaltenn.com/2019/04/algorithms-union-find-in-c.html
using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Reflection.Metadata;
using System.Runtime.Intrinsics.X86;

public class UF1 //N^2
{
    private int[] id;
    public UF1(int N) // Creates an array for id, input id size
    {
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    public bool Connected(int p, int q)
    {
        return id[p] == id[q];
    }

    public void Union(int p, int q)
    {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.Length; i++) //if found any id connected to id[p]
        {
            if (id[i] == pid)
                id[i] = qid; //change current id[i] (all) to id[q]
        }
    }
}
public class UF2 
{
    private int[] id;
    public UF2(int N) // Creates an array for id, input id size
    {
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }
    private int Root(int i)
    {
        while (i != id[i]) // While not at root (not pointing to itself).
            i = id[i]; // See whats inside (contains reference to the parent) and move to the parent 
        return i;
    }
    public bool Connected(int p, int q)
    {
        return id[p] == id[q];
    }
    public void Union(int p, int q)
    {
        int i = Root(p);
        int j = Root(q);
        id[i] = j; //Change the root container of p to the root of q
        
    }
} // only while loops to the root (UF2) instead of for looping the entire array to check (UF1)
public class UF3 //lg N (only works when group is more than double). So at worst case scenario (each group is weighted equal), only works at 1,2,4,8,16,32,2^6.
{
    private int[] id;
    private int[] sz;
    public UF3(int N) // Creates an array for id, input id size
    {
        sz = new int[N];
        id = new int[N];
        for (int i = 0; i < N; i++)
        { 
            id[i] = i;
            sz[i] = 1;
        }        
    }
    private int Root(int i)
    {
        while (i != id[i]) // While not at root (not pointing to itself).
        {
            // Halves path length. Keeps tree almost completely flat.
            id[i] = id[id[i]]; //  See whats inside(contains reference to the parent and then see inside of parent) and move to the parent.
            i = id[i]; // See whats inside (contains reference to the parent) and move to the parent 
        }
        return i;
    }
    public bool Connected(int p, int q)
    {
        return id[p] == id[q];
    }
    public void Union(int p, int q)
    {
        int i = Root(p);
        int j = Root(q);
        if (i == j) // same root, why bother
            return;
        if (sz[i] < sz[j])
        {
            id[i] = j; //  Change the root container of p to the root of q
            sz[j] += sz[i]; //  add the count of the smaller array to the count of the larger array
        }
        else // if j is smaller than i
        {
            id[j] = i;
            sz[i] += sz[j];
        }
    }
}
// So at worst case scenario (each group is weighted equal), only works at 1,2,4,16,65536, 2^65536


public class Program
{
    public static List<int[]> idList = new List<int[]> { new int[] { 4, 3 }, new int[] { 3, 8 }, new int[] { 6, 5 }, new int[] { 9, 4 }, new int[] { 2, 1 }, new int[] { 8, 9 }, new int[] { 5, 0 }, new int[] { 7, 2 }, new int[] { 6, 1 }, new int[] { 1, 0 }, new int[] { 6, 7 } };
    public static void Main()
    {
        UF3 uf = new UF3(idList.Count());
        foreach (int[] unionData in idList)
        {
            int p = unionData[0];
            int q = unionData[1];
            if (!uf.Connected(p, q))
            {
                uf.Union(p, q);
                Console.WriteLine(p + " " + q);
            }
        }
    }
}
