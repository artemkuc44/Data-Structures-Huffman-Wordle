package project20280.exercises;

import java.util.Arrays;
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
        int[] l1 = {2, 6, 20, 24,28,29};
        int[] l2 = {1, 3, 5, 8, 12, 19, 25};
        int[] mergedArray = new int[l1.length + l2.length];
        int i = 0,j = 0,k = 0;

        while(i < l1.length && j < l2.length){

            if(i < l1.length && l1[i] < l2[j]){
                mergedArray[k++] = l1[i++];
            }
            else if(l2[j] < l1[i]){
                mergedArray[k++] = l2[j++];
            }
        }

        while(i < l1.length){
            mergedArray[k++] = l1[i++];
        }
        while(j < l2.length){
            mergedArray[k++] = l2[j++];
        }

        System.out.println(Arrays.toString(mergedArray));

    }

    public static void main(String[] args){
       q9();
    }
}
