package ServerSide;

import java.util.Random;
import java.util.Objects;

public class Tile {

    public final char letter;
    public final int score; // ניקוד עבור אות

    private Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }


    public char getLetter() {
        return letter;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tile)) return false;
        Tile tile = (Tile) o;
        return getLetter() == tile.getLetter() && getScore() == tile.getScore();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLetter(), getScore());
    }

    public static class Bag {

        int totalLetters = 98;
        public Tile[] arrTile = new Tile[26];
        public int[] letterCount = {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8,
                2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
        public int[] score = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3,
                10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
        public char[] letters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O',
                'P','Q','R','S','T','U','V','W','X','Y','Z'};

        private Bag() {
            for (int i = 0; i < 26; i++)
                arrTile[i] = new Tile(letters[i], score[i]);
        }

        public Tile getRand() {
            int randomNum;
            if (totalLetters == 0)
                return null;
            else {
                randomNum = (int) ((Math.random() * 26) + 0);
                if (letterCount[randomNum] == 0) {
                    while (letterCount[randomNum] == 0) {
                        randomNum = (randomNum + 1) % 26;
                    }
                    letterCount[randomNum]--;
                    totalLetters--;
                    return arrTile[randomNum];
                }
                else {
                    letterCount[randomNum]--;
                    totalLetters--;
                    return arrTile[randomNum];
                }
            }
        }

        public Tile getTile(char letter){
            for(int i=0 ; i < 26 ; i++)
            {
                if(arrTile[i].letter == letter)
                {
                    if (letterCount[i] > 0)
                    {
                        letterCount[i]--;
                        totalLetters--;
                        return arrTile[i];
                    }
                }
            }
            return null;

        }

        public void put(Tile tile){
            if(totalLetters < 98)
            {
                for(int i=0; i <26 ;i++)
                {
                    if(arrTile[i].letter == tile.letter)
                    {
                        if(letterCount[i]<totalLetters)
                        {
                            letterCount[i]++;
                            totalLetters++;
                        }
                    }
                }
            }
        }

        public int size(){
            return totalLetters;
        }

        public int[] getQuantities(){
            int[] copy=new int[26];
            System.arraycopy(letterCount,0,copy,0,letterCount.length);
            return copy;
        }

        private static Bag bag= null;

        public static Bag getBag(){
            if(bag == null)
            {
                bag = new Bag();
            }
            return bag;
        }

    }
}


