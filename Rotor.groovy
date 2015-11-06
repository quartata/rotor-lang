import groovy.transform.Field;
import java.util.Arrays;

@Field String[][][] wheels = [
  [
    ["A", "x.push(Math.atan2(x.pop(),x.pop()));"],
    ["B", ""],
    ["C", "x.push(Math.acos(x.pop()));"],
    ["D", ""],
    ["E", "x.push(10^(x.pop()));"],
    ["F", ""],
    ["G", "x.push(x.pop() >= x.pop());"],
    ["H", ""],
    ["I", "x.push(Math.asin(x.pop()));"],
    ["J", ""],
    ["K", ""],
    ["L", "x.push(x.pop() <= x.pop());"],
    ["M", ""],
    ["N", "x.push(Math.log(x.pop()));"],
    ["O", ""],
    ["P", "x.push(Math.pow(x.pop(),x.pop());)"],
    ["Q", ""],
    ["R", ""],
    ["S", "x.push(Math.sqrt(x.pop()));"],
    ["T", "x.push(Math.atan(x.pop()));"],
    ["U", ""],
    ["V", ""],
    ["W", ""],
    ["X", "x.push(x.pop() ^ x.pop());"],
    ["Y", ""],
    ["Z", ""],
    ["a", "x.push(Math.abs(x.pop());"],
    ["b", ""],
    ["c", "x.push(Math.cos(x.pop());"],
    ["d", "x.push(Math.sqrt((x.pop()-x.pop())^2+(x.pop()-x.pop())^2);"],
    ["e", "x.push(Math.E);"],
    ["f", "def i = x.pop();if(i==0)x.push(Math.floor(x.pop()));if(x==1)x.push(Math.ceil(x.pop()) else x.push(Math.floor(i));"],
    ["g", "x.push(x.pop() > x.pop());"],
    ["h", "x.push(Math.hypot(x.pop(),x.pop()));"],
    ["i", "x.push(Math.sin(x.pop()));"],
    ["j", ""],
    ["k", ""],
    ["l", "x.push(x.pop() < x.pop());"],
    ["m", "x.push(x.pop() & x.pop());"],
    ["n", "x.push(!x.pop());"], 
    ["o", "x.push(x.pop() | x.pop());"],
    ["p", "x.push(Math.PI);"],
    ["q", ""],
    ["r", "new Random().nextInt(x.pop());"],
    ["s", "x.push(Math.pow(x.pop(),2) as int);"],
    ["t", "x.push(Math.tan(x.pop()));"],
    ["u", ""],
    ["v", ""],
    ["w", ""],
    ["x", "x.push(1.div(x.pop()));"],
    ["y", ""],
    ["z", ""]
  ]
];
@Field def stack = [];
@Field def reg;
@Field String code;
@Field RotorComparer comp = new RotorComparer();
@Field int codePointer = 0, wheelPointer = 0;

void parse() {
  for(;codePointer < code.length();codePointer++) {
    char instruction = code[codePointer];
    if(instruction == '<') {
      if(wheelPointer == 0) wheelPointer = wheels.length-1;
      else wheelPointer--;
    } else if(instruction == '>') {
      if(wheelPointer == wheels.length-1) wheelPointer = 0;
      else wheelPointer++;
    } else if(instruction == '`') {
      String literal = "";
      for(codePointer++;codePointer < code.length();codePointer++) {
        char stringChar = code[codePointer];
        if(stringChar >= '0' && stringChar <= '9') {
          literal += stringChar;
        } else {
          break;
        }
      }
      stack.push(literal as int);
    } else if(instruction == '"') {
      String literal = "";
      for(codePointer++;codePointer < code.length();codePointer++) {
        char stringChar = code[codePointer];
        if(stringChar == '\\' && codePointer != code.length()-1) {
          literal += stringChar + code[++codePointer];
        } else if(stringChar == '"') {
          break;
        } else {
          literal += stringChar;
        }
      }
      stack.push(literal);
    } else if(instruction == '\'') {
      stack.push(code[++codePointer]);
    } else if(instruction >= '0' && instruction <= '9') {
      stack.push((instruction as String) as int);
    } else if(instruction == '?') {
      if(!stack.pop()) {
        skip();
      }
    } else if(instruction == '!') {
      if(stack.pop()) {
        skip();
      }
    } else if(instruction == '_') { 
      stack.push(stack[-1]); 
    } else if(instruction == ')') {
      def temp = stack[-1];
      stack[-1] = stack[-2];
      stack[-2] = temp;
    } else if(instruction == ':') {
      redefine(stack.pop(),stack.pop());
    } else if(instruction == '^') {
      def i = stack.pop();
      if(i >= 0 && i < wheels.length) wheelPointer = i;
    } else if(instruction == '@') {
      //Rotate
    } else if(instruction == '+') {
      stack.push(stack.pop() + stack.pop());
    } else if(instruction == '/') {
      stack.push(stack.pop().div(stack.pop()));
    } else if(instruction == '*') {
      stack.push(stack.pop() * stack.pop());
    } else if(instruction == '-') {
      stack.push(stack.pop() - stack.pop());
    } else if(instruction == '=') {
      stack.push(stack.pop().equals(stack.pop()));
    } else if(instruction == ',') {
      stack = stack.reverse();
    } else if(instruction == '\\') {
      //For
    } else if(instruction == '|') {
      //While
    } else if(instruction == ';') {
      stack.pop();
    } else if(instruction == '&') {
      reg = stack[-1];
    } else if(instruction == '~') {
      stack.push(reg);
    } else {
      parseInstruction(instruction);  
    }
  }  
}

void skip() {
  char instruction = code[++codePointer];
  if(instruction == '"') {
    while(codePointer < code.length()-1 && code[++codePointer] != '"');
  } else if(instruction == '\'') {
    ++codePointer;
  } else if(instruction == '`') {
    while(codePointer < code.length()-1 && code[++codePointer] >= 0 && code[codePointer] <= 9);
  } else if(instruction == '?' || instruction == '!') {
    skip();
  }
}

void stdin() {
  String text = System.in.text;
  if(text.length() == 0 || text == "\n") return;
  try {
    stack.push(Eval.me(text));
  } catch(Exception e) {
    stack.push(text);
  }
}

void redefine(char c, int n) {
  int index1 = Arrays.binarySearch(wheels[wheelPointer],(String[])[c as String],comp);
  int index2 = Arrays.binarySearch(wheels[n],(String[])[c as String],comp);
  if(index1 >= 0 && index2 >= 0) {
    wheels[wheelPointer][index1][1] = wheels[n][index2][1];
  }
}

void parseInstruction(char c) {
  int index = Arrays.binarySearch(wheels[wheelPointer],(String[])[c as String],comp);
  if(index >= 0) Eval.xy(stack,reg,wheels[wheelPointer][index][1]);
}

class RotorComparer implements Comparator<String[]> {
  @Override
  int compare(String[] x, String[] y) {
    return x[0][0].compareTo(y[0][0]);
  }
}

code=new File(args[0]).text;
stdin();
parse();
for(Object o : stack) print o;
