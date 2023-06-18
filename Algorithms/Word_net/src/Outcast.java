import java.util.Objects;

public class Outcast {

    WordNet wordnet;

    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        String outcast = "";
        int max = -1;
        for (String noun : nouns) {
            int sum = distanceSum(noun, nouns);
            if (sum > max) {
                outcast = noun;
            }
        }
        return outcast;
    }

    private int distanceSum(String x, String[] nouns) {
        int distanceSum = 0;
        for (String noun : nouns) {
            distanceSum += wordnet.distance(x, noun);
        }
        return distanceSum;
    }

    // see test client below
    public static void main(String[] args) {
        String synsets = "https://coursera.cs.princeton.edu/algs4/assignments/wordnet/files/synsets.txt";
        String hypernyms = "https://coursera.cs.princeton.edu/algs4/assignments/wordnet/files/hypernyms.txt";
        String[] strings = {"horse", "zebra", "cat", "bear", "table"};
        WordNet wordnet = new WordNet(synsets, hypernyms);
        Outcast outcast = new Outcast(wordnet);
        System.out.println(outcast.outcast(strings));
        assert Objects.equals(outcast.outcast(strings), "table");
    }
}
