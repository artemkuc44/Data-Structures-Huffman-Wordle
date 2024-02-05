package project20280.exercises;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Wk2 {
    //6)What is the difference between a singly linked list and a circularly linked list?
    //A)in a singly linked list the head indicates the start of the list with the last node pointing to null, which indicates it is the last node.
    //in a circulary linked list the tail is the last node and it points back to the first node(head)

    //q7)In what situations would you prefer to use a linked list to an array?
    //A) linked lists are better for memory management, dynamic allocation of new list elements, and easy deletion and additon of such elements

    //q8)Describe 2 possible use-cases for a circularly linked list (2-3 sentences for each).
    //A) multiplayer game where players take turns, music playlist where last song loops back to first

    public static void q9(){

        int[] l1 = {2, 6, 20, 24};
        int[] l2 = {1, 3, 5, 8, 12, 19, 25};
        int[] mergedList = new int[l1.length + l2.length];
        int min = Integer.MAX_VALUE;



        for(int i = 0;i<l1.length + l2.length;i++){
            for(int j = 0;j<l1.length + l2.length;j++){
                //if(l1.[i])
            }
        }

    }


    public static void main(String[] args){
        q9();
    }
}
