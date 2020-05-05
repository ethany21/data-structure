import java.util.Scanner;

public class StackProgram
{
  public static void main(String[] args){
    
    Scanner scanner = new Scanner(System.in);
    String input = "";
    String [] array ={};
    String output = "";
    boolean exit = true;
    UnorderedLinkedList<String> list = new UnorderedLinkedList<String>();
    while (exit){
      System.out.println("Please select what type of conversion you would like to do");
      System.out.println("1) Infix to postfix");
      System.out.println("2) Postfix to infix");
      System.out.println("3) Print equations");
      System.out.println("4) Exit");
      
      System.out.print("Your choice");
      int choice = scanner.nextInt();
      scanner.nextLine();
      if (choice == 1){
        input = scanner.nextLine();
        input = input.substring(0, input.length()-1);
        array = input.split("");
        output = infixToPostfix(array);
        list.insertLast(output);
      }
      else if (choice == 2){
        input = scanner.nextLine();
        input = input.substring(0, input.length()-1);
        array = input.split("");
        output = postfixToInfix(array);
        list.insertLast(output);
      }
      else if (choice == 3){
        list.print();
//        System.out.println("");
      }
      else if (choice == 4){
        exit = false;
      }
      
    }
}

  static boolean isitoperand(String a){
    switch(a){
      case "+" : 
      case "-" : 
      case "*" : 
      case "/" : 
      case "^" : 
      case "(" :
      case ")" :
        return false;
      default  : return true;
    }
  }
  static int operator_precedence(String a){
    switch(a){
      case "+" : 
      case "-" : 
        return 0;
      case "*" :
      case "/" : 
        return 1;
      case "^" : 
        return 2;
      default  : 
        throw new IllegalArgumentException(a);
    }
 }
  public static String infixToPostfix(String [] array){
    LinkedStackClass<String> stack = new LinkedStackClass<>();
    String P = "";
    int index = 0;
    while (index < array.length){
      String ch = array[index];
      if (isitoperand(ch)){
        P += ch;
      }
      else {
        if (ch.equals("(")){
          stack.push(ch);
        }
        else {
          if(ch.equals(")")){
            ch = "";
            boolean check = true;
            try{
              while(true){
                String add_up = stack.peek();
                stack.pop();
                if (add_up.equals("(") == false){
                  P += add_up;
                }
                else {
                  check = false;
                }
              }
            }catch (StackUnderflowException e){
              System.out.println(e);
            }
          }
          else {
            try{
              while (stack.isEmptyStack() != true && (stack.peek()).equals("(") != true && operator_precedence(ch) < operator_precedence(stack.peek())){
                String add_up = stack.peek();
                P += add_up;
                stack.pop();
              }
            } catch (StackUnderflowException e){
              System.out.println(e);
            }
            stack.push(ch);
          }
        }
    }
      index += 1;
}
    try {
      boolean check = true;
      while (check){
        String rest = stack.peek();
        P += rest;
        stack.pop();
      }
    }
    catch (StackUnderflowException e){
      System.out.println(e);
    }
    System.out.println(P);
    return P;
  }

  public static String postfixToInfix(String[] array){
    LinkedStackClass<String> stack = new LinkedStackClass<>();
    String I = "";
    String former_operator = "";
    int index = 0;
    int num_operator = 1;
    
    while (index < array.length){
      String ch = array[index];
      if (isitoperand(ch)){
        stack.push(ch);
      }
      else {
        if (num_operator == 1){
          String back = stack.peek();
          stack.pop();
          String front = stack.peek();
          stack.pop();
          String add_up = front+ch+back;
          stack.push(add_up);
          former_operator = ch;
          num_operator += 1;
        }
        else {
          if(operator_precedence(former_operator) < operator_precedence(ch)){
            String back = stack.peek();
            stack.pop();
            String front = stack.peek();
            stack.pop();
            if (back.length() == 1){
              front = "("+front+")";
            }
            else if (front.length() == 1){
              back = "("+back+")";
            }
            String add_up = front+ch+back;
            stack.push(add_up);
            former_operator = ch;
            num_operator += 1;
          }
          else {
            String back = stack.peek();
            stack.pop();
            String front = stack.peek();
            stack.pop();
            String add_up = front+ch+back;
            stack.push(add_up);
            former_operator = ch;
            num_operator += 1;
          }
        } 
      }
      index += 1;
    }
    try {
      boolean check = true;
      while (check){
        String rest = stack.peek();
        I += rest;
        stack.pop();
      }
    }
    catch (StackUnderflowException e){
      System.out.println(e);
    }
    System.out.println(I);
    return I;
  }
}
