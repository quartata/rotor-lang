//Copyright © 2015 quartata

//This program is free software: you can redistribute it and/or modify
//it under the terms of the GNU General Public License as published by
//the Free Software Foundation, either version 3 of the License, or
//(at your option) any later version.

//This program is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.
//You should have received a copy of the GNU General Public License
//along with this program.  If not, see <http://www.gnu.org/licenses/>.


import groovy.transform.Field;
import java.util.Arrays;

@Field static String[][][] wheels = [
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
    ["J", "x.push(x.pop << x.pop())"],
    ["K", "x.push(x.pop >> x.pop())"],
    ["L", "x.push(x.pop() <= x.pop());"],
    ["M", ""],
    ["N", "x.push(Math.log(x.pop()));"],
    ["O", ""],
    ["P", "x.push(Math.pow(x.pop(),x.pop());)"],
    ["Q", "x.push((Math.sqrt(5)+1).div(2));"],
    ["R", ""],
    ["S", "x.push(Math.sqrt(x.pop()));"],
    ["T", "x.push(Math.atan(x.pop()));"],
    ["U", ""],
    ["V", "x.push(Math.pow(e,x.pop()))"],
    ["W", "x.push(Math.sqrt(2));"],
    ["X", "x.push(x.pop() ^ x.pop());"],
    ["Y", ""],
    ["Z", ""],
    ["a", "x.push(Math.abs(x.pop());"],
    ["b", ""],
    ["c", "x.push(Math.cos(x.pop());"],
    ["d", "x.push(Math.sqrt((x.pop()-x.pop())^2+(x.pop()-x.pop())^2);"],
    ["e", "x.push(Math.E);"],
    ["f", "x.push(Math.floor(x.pop()));"],
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
    ["s", "x.push(Math.pow(x.pop(),2));"],
    ["t", "x.push(Math.tan(x.pop()));"],
    ["u", ""],
    ["v", "x.push(Math.pow(2,x.pop())"],
    ["w", "x.push(Math.ceil(x.pop()));"],
    ["x", "x.push(1.div(x.pop()));"],
    ["y", ""],
    ["z", "x.push(Math.round(x.pop()));"]
  ],
  [
    ["N", "x.push(Rotor.newline);"]
  ]
];
@Field static char newline = '\n'
@Field static def stack = [];
@Field static def reg;
@Field static int wheelPointer = 0;

class Block {
  String code;
  RotorComparer comp = new RotorComparer();
  int codePointer = 0;
  def stack;
  def wheels;
  public Block(String c) { code = c; stack = Rotor.stack; wheels = Rotor.wheels;}

  void parse() {
    for(;codePointer < code.length();codePointer++) {
      char instruction = code[codePointer];
      if(instruction == '<') {
        if(Rotor.wheelPointer == 0) Rotor.wheelPointer = wheels.length-1;
        else Rotor.wheelPointer--;
      } else if(instruction == '>') {
        if(Rotor.wheelPointer == wheels.length-1) Rotor.wheelPointer = 0;
        else Rotor.wheelPointer++;
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
            literal += Eval.me(stringChar + code[++codePointer]);
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
      } else if(instruction == '#') {
        stack.pop().parse();
      } else if(instruction == ')') {
        def temp = stack[-1];
        stack[-1] = stack[-2];
        stack[-2] = temp;
      } else if(instruction == ':') {
        redefine(stack.pop(),stack.pop());
      } else if(instruction == '^') {
        def i = stack.pop();
        if(i >= 0 && i < wheels.length) Rotor.wheelPointer = i;
      } else if(instruction == '@') {
        stack.push(stack[-3]);
      } else if(instruction == '+') {
        stack.push(stack.pop() + stack.pop());
      } else if(instruction == '/') {
        stack.push(stack.pop().div(stack.pop()));
      } else if(instruction == '*') {
        stack.push(stack.pop() * stack.pop());
      } else if(instruction == '-') {
        stack.push(stack.pop() - stack.pop());
      } else if(instruction == '%') {
        def i = stack.pop();
        stack.push(stack.pop() % i);
      } else if(instruction == '=') {
        stack.push(stack.pop().equals(stack.pop()));
      } else if(instruction == '$') {
        stack.push(stack.pop().equals(stack[-1]));
      } else if(instruction == '(') {
        stack = stack.reverse();
      } else if(instruction == '.') {
	     stack.push(stack[-2][stack.pop()]);
      } else if(instruction == ',') {
        stack[-2][stack.pop()] = stack.pop();
      } else if(instruction == '[') {
        for(Object o : stack.pop()) stack.push(o);
      } else if(instruction == ']') {
        def a = [];
        for(Object o : stack) a.push(o);
        stack.clear();
        stack.push(a);
      } else if(instruction == '\\') {
        def b = stack.pop();
        def r = stack.pop()..stack.pop();
        for(int i : r) {
          Rotor.reg = i;
          stack.push(i);
          b.parse();
        }
      } else if(instruction == '|') {
        def b = stack.pop();
        while(stack[-1]) b.parse();
      } else if(instruction == ';') {
        stack.pop();
      } else if(instruction == '&') {
        Rotor.reg = stack[-1];
      } else if(instruction == '~') {
        stack.push(Rotor.reg);
      } else if(instruction == '{') {
        String literal = "";
        for(codePointer++; codePointer < code.length(); codePointer++) {
          char i = code[codePointer];
          if(i == '}') break;
          else literal += i;
        }
        stack.push(new Block(literal));
      } else if(instruction >= '\u0001' && instruction <= '\u0006'){
        int i = instruction as int;
        stack.push(9+i);
      } else if(instruction >= '\u000E' && instruction <= '\u001A') {
        int i = instruction as int;
        stack.push(2+i);
      } else if(instruction == '\u001C' || instruction == '\u001D') {
        int i = instruction as int;
        stack.push(1+i);
      } else if(instruction == '\u007F') {
        stack.push(100);
      } else if(instruction == '\u001B') {
        println stack[-1];
      } else {
        parseInstruction(instruction);  
      }
    }
    codePointer = 0;
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
    } else if(instruction == '{') { 
      while(codePointer < code.length()-1 && code[++codePointer] != '}'); 
      codePointer++; 
    } else {}
  }

  void redefine(char c, int n) {
    int index1 = Arrays.binarySearch(wheels[Rotor.wheelPointer],(String[])[c as String],comp);
    int index2 = Arrays.binarySearch(wheels[n],(String[])[c as String],comp);
    if(index1 >= 0 && index2 >= 0) {
      wheels[Rotor.wheelPointer][index1][1] = wheels[n][index2][1];
    }
  }

  void parseInstruction(char c) {
    int index = Arrays.binarySearch(wheels[Rotor.wheelPointer],(String[])[c as String],comp);
    if(index >= 0) Eval.xy(stack,Rotor.reg,wheels[Rotor.wheelPointer][index][1]);
  }

}

class RotorComparer implements Comparator<String[]> {
  @Override
  int compare(String[] x, String[] y) {
    return x[0][0].compareTo(y[0][0]);
  }
}

void stdin() {
  String text = System.in.text;
  if(text.length() == 0 || text == "\n") { 
    wheelPointer = 1;
    return; 
  }
  def result;
  try {
    result = Eval.me(text);
  } catch(Exception e) {
    result = text;
  }
  if(result instanceof String) {
    wheelPointer = 1;
  } else {
    wheelPointer = 0;
  }
  stack.push(result);
}

code=new File(args[0]).text;
stdin();
new Block(code).parse();
for(Object o : stack) print o;
