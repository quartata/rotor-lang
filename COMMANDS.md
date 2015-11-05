#Core Commands

Core commands are always available regardless of what wheel you are in. They can be easily identified in code since they are never letters.

`>` Shifts the wheel pointer forward by one (wraps around if you hit the end).
`<` Shifts the wheel pointer back by one (wraps around if you hit the beginning).
`\`` Pushes the following multi-digit number onto the stack. In Rotor, a program like `442` actually pushes a `4`, a `4` and a `2` to the stack rather than the whole number. Using `\`` like this: `\`442` will push the number `442` to the stack.
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
`\\` For loop (not yet implemented).
`|` While loop (not yet implemented).
`;` Discards the top value of the stack.
`&` Copies the top value of the stack into the register.
`~` Pushes the current value of the register onto the stack.

#Wheels

##Math Wheel

Coming soon.

##String Wheel

Coming soon.

##Array Wheel

Coming soon.

##Time Wheel

Coming soon.

##Network Wheel

Coming soon.
