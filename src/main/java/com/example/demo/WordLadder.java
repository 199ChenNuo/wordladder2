package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class WordLadder {
    public static void main(String[] args){
    }

    public String getLadder(String w1, String w2, Set<String> dic){
        Set<String> usedWords=new HashSet<String>();// words that were used
        Queue<Stack<String>> tree=new LinkedList<Stack<String>>();
        Stack<String> firstStack=new Stack<String>();
        Set<String> neighbors=new HashSet<String>();
        Set<String> w2Neighbors=new HashSet<String>();
        w2Neighbors = getNeighbor(w2, dic);
        firstStack.push(w1);
        tree.offer(firstStack);
        while(tree.size() != 0){
            Stack<String> topStack = tree.poll();// get and delete topStack
            String topWord = topStack.peek();// get NOT delete topWord
            neighbors = getNeighbor(topWord, dic);
            if(neighbors.contains(w2)){// two words differ by one letter
                topStack.push(w2);
                return printLadder(topStack);
            }
            for(String neigh : neighbors){// for each String in neighbors
                if(!(usedWords.contains(neigh))){// if the word has been used, then it won't lead to the shortest path
                    if(w2Neighbors.contains(neigh)){// find the ladder
                        topStack.push(neigh);
                        topStack.push(w2);
                        return printLadder(topStack);
                    }else{
                        Stack<String> newStack = (Stack<String>) topStack.clone();
                        newStack.push(neigh);
                        usedWords.add(neigh);
                        tree.offer(newStack);
                    }
                }
            }
        }
        //System.out.println("No word ladder found from " + w1 + " back to " + w2);
        return "No word ladder found from " + w1 + " back to " + w2;
    }

    // initial dic and call fun to get word1, word2
    public String getKeyWords(Set<String> dic){
        //System.out.print("Dictionary file name?\n");
        Scanner sc1 = new Scanner(System.in);
        //String dicName = sc1.nextLine();
        String dicName = ".\\src\\main\\java\\com\\example\\demo\\dictionary.txt";
        try{
            File dicFile = new File(dicName);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(dicName));
            BufferedReader br = new BufferedReader((reader));
            String line = "";
            line = br.readLine();
            while(line != null){
                line = br.readLine();
                dic.add(line);
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return dicName;
    }

    // find all valid neighbors of w, and return them in a Set<String>
    public Set<String> getNeighbor(String w, Set<String> dic){
        Set<String> neighbors = new HashSet<String>();
        int len = w.length();
        for(int i = 0; i < len; i++){
            String neighbor = w.substring(0, i) + w.substring(i+1);
            if(dic.contains(neighbor) && !(neighbor.equals(w)) && !(neighbors.contains(neighbor))){
                neighbors.add(neighbor);
            }
            for(char c='a'; c <= 'z'; c++){
                neighbor = w.substring(0, i) + c + w.substring(i);
                if(dic.contains(neighbor) && !(neighbor.equals(w)) && !(neighbors.contains(neighbor))){
                    neighbors.add(neighbor);
                }
                neighbor = w.substring(0, i) + c + w.substring(i + 1);
                if(dic.contains(neighbor) && !(neighbor.equals(w)) && !(neighbors.contains(neighbor))){
                    neighbors.add(neighbor);
                }
            }
        }
        return neighbors;
    }

    // print the ladder
    public String printLadder(Stack<String> stack){
        int len = stack.size();
        String ladder="";
        for(int i = 0; i < len - 1; i++){
            //System.out.print(stack.pop() + " -> ");
            ladder += stack.pop();
            ladder += "->";
        }
        //System.out.println( stack.pop());
        ladder += stack.pop();
        return ladder;
    }
}
