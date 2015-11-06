#Core Commands

Core commands are always available regardless of what wheel you are in. They can be easily identified in code since they are never letters.

`>` Shifts the wheel pointer forward by one (wraps around if you hit the end).

`<` Shifts the wheel pointer back by one (wraps around if you hit the beginning).

`` ` `` Pushes the following multi-digit number onto the stack. In Rotor, a program like `442` actually pushes a `4`, a `4` and a `2` to the stack rather than the whole number. Using `` ` `` like this: `` `442`` will push the number `442` to the stack.

`""` Pushes this string literal onto the stack.

`'` Pushes the following char onto the stack. Note that there is no closing quote for this.

`0-9` Pushes this number onto the stack.

`?` Executes the next instruction if the top value of the stack is truthy (true/1).

`!` Executes the next instruction if the top value of the stack is falsy (false/0)

`_` Duplicates the top value of the stack.

`)` Swaps the two top values of the stack.

`:` Pops a char representing an instruction and a number representing a wheel and changes the meaning of the instruction in the current wheel to its meaning in the other wheel.

`^` Pops a number off the stack and jumps to that wheel.

`@` Rotates the top three values of the stack (not yet implemented).

`+, -, *, /` Add, subtract, multiply, divide.

`=` Pops two values off the stack and pushes true if they are equal and false if they are not.

`,` Reverses the stack.

`\` For loop (not yet implemented).

`|` While loop (not yet implemented).

`;` Discards the top value of the stack.

`&` Copies the top value of the stack into the register.

`~` Pushes the current value of the register onto the stack.

#Wheels

##Math Wheel

`a` Pops a number off the stack and pushes the absolute value of it.

`b` Base conversion. (not implemented)

`c` Pops a number off the stack and pushes the cosine of it. (uses radians)

`d` Distance formula. Pops four numbers (y2, y1, x2, x1) off the stack and pushes sqrt((x2-x1)^2+(y2-y1)^2).

`e` Pushes Euler's number onto the stack.

`f` Floor and ceiling. By default, this pops off a number and pushes the floor of it -- however, if there is also truthy/falsy value on top of the stack it willpop another number off the stack and either ceiling it if this value is truthy or floor it if this value is falsy.

`g` Pops two objects (not necessarily numbers) off the stack and pushes first > second.

`h` Pops two numbers off the stack and pushes sqrt(first^2+second^2).

`i` Pops a number off the stack and pushes the sine of it. (uses radians)

`j` Pops a number off the stack and pushes the factorial of it. (not implemented)

`k` Number of permutations -- Pops two numbers off the stack and pushes first P second. (not implemented)

`l` Pops two objects off the stack and pushes first < second.

`m` Pops two objects off the stack and pushes the bitwise and of them.

`n` Pops a boolean off the stack and pushes its inverse.

`o` Pops two objects off the stack and pushes the bitwise or of them.

`p` Pushes pi onto the stack.

`q` Number of combinations -- Pops two numbers off the stack and pushes first C second. (not implemented)

`r` Pops a number off the stack and pushes a random integer between 0 and the number.

`s` Pops a number off the stack and pushes the square of it.

`t` Pops a number off the stack and pushes the tangent of it. (uses radians)

`u` Pops two numbers off the stack and pushes GCD(first,second). (not implemented)

`v` Nothing yet.

`w` Nothing yet.

`x` Pops a number off the stack and pushes the reciprocal of it.

`y` Pops a number off the stack and pushes true if it is prime and false if it is not. (not implemented)

`z` Nothing yet.

`A` Pops two numbers off the stack and pushes atan2(first,second). (uses radians)

`B` Binary conversion. (not implemented)

`C` Pops a number off the stack and pushes the inverse cosine of it. (uses radians)

`D` Degree to radian conversion. (not implemented)

`E` Pops a number off the stack and pushes 10^x.

`F` Pops a number off the stack and pushes the nth Fibonacci number. (not implemented)

`G` Pops two objects off the stack and pushes first >= second.

`H` Hex conversion. (not implemented)

`I` Pops a number off the stack and pushes the inverse sine of it. (uses radians)

`J` Nothing yet.

`K` Nothing yet.

`L` Pops two objects off the stack and pushes first <= second.

`M` Pops everything off the stack, multiplies them together and pushes the result. (not implemented)

`N` Pops a number off the stack and pushes the natural logarithm of it.

`O` Nothing yet.

`P` Pops two numbers off the stack and pushes first^second.

`Q` Pushes the golden ratio onto the stack.

`R` Pops two numbers off the stack and pushes the first root of the second. (not implemented).

`S` Pops a number off the stack and pushes the square root of it.

`T` Pops a number off the stack and pushes the inverse tangent of it. (uses radians)

`U` Pops two numbers off the stack and pushes LCM(first, second). (not implemented)

`V` Pops a number off the stack and pushes e^x.

`W` Pushes the square root of 2 onto the stack.

`X` Pops two numbers off the stack and pushes the XOR of them.

`Y` Pops a number off the stack and pushes the nth prime. (not implemented)

`Z` Pops everything off the stack, adds them together, and pushes the result. (not implemented)

##String Wheel

Coming soon.

##Array Wheel

Coming soon.

##Time Wheel

Coming soon.

##Network Wheel

Coming soon.
